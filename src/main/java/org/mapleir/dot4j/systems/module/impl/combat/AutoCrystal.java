package org.mapleir.dot4j.systems.module.impl.combat;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.mob.MagmaCubeEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import org.mapleir.dot4j.systems.module.core.Category;
import org.mapleir.dot4j.systems.module.core.Module;

import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Items;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Module.Info(name = "AutoCrystal", description = "haxer tester", category = Category.COMBAT)
public class AutoCrystal extends Module {
    private int tickCounter = 0;
    private Robot robot;
    private ScheduledExecutorService executorService;

    public AutoCrystal() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        executorService = Executors.newSingleThreadScheduledExecutor();

    }

    public void leftClick() {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        executorService.schedule(() -> robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK), 100, TimeUnit.MILLISECONDS);
    }

    public void rightClick() {
        robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
        executorService.schedule(() -> robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK), 100, TimeUnit.MILLISECONDS);
    }

    @Override
    public void onTick() {
        if (mc.currentScreen != null) return;

        if (mc.player == null || mc.world == null || mc.crosshairTarget == null) {
            return;
        }

        if (mc.player.getMainHandStack().getItem() == Items.END_CRYSTAL) {
            boolean shouldActivate = false;


            if (mc.crosshairTarget.getType() == HitResult.Type.BLOCK) {
                BlockHitResult blockHitResult = (BlockHitResult) mc.crosshairTarget;
                BlockPos pos = blockHitResult.getBlockPos();
                BlockState state = mc.world.getBlockState(pos);

                if (state.getBlock() == Blocks.OBSIDIAN || state.getBlock() == Blocks.BEDROCK) {
                    shouldActivate = true;
                }
            } else if (mc.crosshairTarget.getType() == HitResult.Type.ENTITY) {
                EntityHitResult entityHitResult = (EntityHitResult) mc.crosshairTarget;
                Entity targetEntity = entityHitResult.getEntity();
                if (targetEntity instanceof EndCrystalEntity || targetEntity instanceof MagmaCubeEntity) {
                    shouldActivate = true;
                }
            }


            if (shouldActivate) {
                if (tickCounter == 0) {
                    rightClick();
                } else if (tickCounter == 1) {
                    leftClick();
                }
            }
        }
        if (tickCounter == 1) {
            tickCounter = 0;
        } else {
            tickCounter++;
        }
    }
}