package org.mapleir.dot4j.systems.module.impl.movement;

import net.minecraft.entity.player.PlayerEntity;
import org.mapleir.dot4j.event.EventTarget;
import org.mapleir.dot4j.event.impl.EventUpdate;
import org.mapleir.dot4j.event.impl.MovementUtils;
import org.mapleir.dot4j.gui.setting.ModeSetting;
import org.mapleir.dot4j.systems.module.core.Category;
import org.mapleir.dot4j.systems.module.core.Module;
@Module.Info(name = "Speed", description = "Walk, but faster", category = Category.MOVEMENT)

public class Speed extends Module {

    ModeSetting modeSetting = new ModeSetting("Mode", "Vanilla", "Vanilla", "Vulcan");
    public Speed() {
        addSettings(modeSetting);
    }

    private int jumpTicks = 0;
    private int ticks = 0;

    public void onEnable() {
        super.onEnable();
        jumpTicks = 0;
        ticks = 0;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        jumpTicks = 0;
        ticks = 0;
    }
    @EventTarget
    public void onUpdate(EventUpdate e) {

        if (modeSetting.isMode("Vanilla")) {

            jumpTicks += 1;

            if (isMoving(mc.player)) {
                if (mc.player.isOnGround()) {
                    mc.player.jump();

                    jumpTicks = 0;
                } else {
                    if (jumpTicks > 3)
                        mc.player.setVelocity(mc.player.getVelocity().x, (mc.player.getVelocity().y - 0.08) * 0.98, mc.player.getVelocity().z);

                    strafe(mc.player, getSpeed() * (1.01f - ((float) Math.random() / 500)));
                }
            }
        }
    }

    @Override
    public void onTick() {
        if (modeSetting.isMode("Vulcan")) {

            mc.options.jumpKey.setPressed(false);

            if (mc.player.isOnGround() && MovementUtils.isMoving()) {
                ticks = 0;
                mc.player.jump();

                MovementUtils.strafe();
                if (MovementUtils.getSpeed() < 0.5f) {
                    MovementUtils.strafe(0.484f);
                }
            }

            if (!mc.player.isOnGround()) {
                ticks++;
            }

            if (ticks == 4) {
                mc.player.setVelocity(mc.player.getVelocity().getX(), mc.player.getVelocity().getY() - 0.17, mc.player.getVelocity().getZ());
            }

            if (ticks == 1) {
                MovementUtils.strafe(0.33f);
            }
        }
    }

    private boolean isMoving(PlayerEntity player) {
        return player.forwardSpeed != 0 || player.sidewaysSpeed != 0;
    }

    private float getSpeed() {
        return mc.player.getMovementSpeed() * 0.225f;
    }

    private void strafe(PlayerEntity player, float speed) {
        if (player.forwardSpeed != 0.0f || player.sidewaysSpeed != 0.0f) {
            player.setVelocity((float) (Math.cos(Math.toRadians(player.getYaw() + 90.0f)) * player.forwardSpeed + Math.cos(Math.toRadians(player.getYaw())) * player.sidewaysSpeed) * speed, player.getVelocity().y, (float) (Math.sin(Math.toRadians(player.getYaw() + 90.0f)) * player.forwardSpeed + Math.sin(Math.toRadians(player.getYaw())) * player.sidewaysSpeed) * speed);
        }
    }
}