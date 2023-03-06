package org.mapleir.dot4j.systems.module.impl.combat;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import org.lwjgl.glfw.GLFW;
import org.mapleir.dot4j.systems.module.core.Category;
import org.mapleir.dot4j.systems.module.core.Module;

import java.awt.*;
import java.awt.event.InputEvent;

public class Triggerbot extends Module {
    public Triggerbot() {
        super("Triggerbot", "description", Category.COMBAT);
    }

    private Entity target;
    @Override
    public void onTick() {
        target = null;
//        Robot robot = null;
//        try {
//            robot = new Robot();
//        } catch (AWTException e) {
//            throw new RuntimeException(e);
//        }

        if(mc.player.isBlocking()) return;
        if(mc.player.isUsingItem()) return;
        if(mc.currentScreen instanceof HandledScreen) return;
        if (!mc.player.isOnGround()) {
            if (mc.player.getHealth() <= 0.0f || mc.player.getAttackCooldownProgress(0.5f) < 0.95f) return;
            if (!(mc.targetedEntity instanceof LivingEntity)) return;
            if (((LivingEntity) mc.targetedEntity).getHealth() <= 0.0f) return;
            target = mc.targetedEntity;

            attack(target);
        } else {
            if (mc.player.getHealth() <= 0.0f || mc.player.getAttackCooldownProgress(0.5f) < 0.92f) return;
            if (!(mc.targetedEntity instanceof LivingEntity)) return;
            if (((LivingEntity) mc.targetedEntity).getHealth() <= 0.0f) return;
            target = mc.targetedEntity;

            attack(target);
        }
    }

    private void attack(Entity entity) {
        mc.interactionManager.attackEntity(mc.player, entity);
        mc.player.swingHand(Hand.MAIN_HAND);
    }
}
