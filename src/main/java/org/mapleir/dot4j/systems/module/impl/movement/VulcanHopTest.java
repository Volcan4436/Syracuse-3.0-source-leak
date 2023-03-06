package org.mapleir.dot4j.systems.module.impl.movement;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import org.mapleir.dot4j.systems.module.core.Category;
import org.mapleir.dot4j.systems.module.core.Module;
@Module.Info(name = "VulcanHopTest", description = "vulcan hop omg", category = Category.MOVEMENT)

public class VulcanHopTest extends Module {

    private int jumpTicks = 0;

    public void onTick() {
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
