package org.mapleir.dot4j.systems.module.impl.misc;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import org.lwjgl.glfw.GLFW;
import org.mapleir.dot4j.systems.module.core.Category;
import org.mapleir.dot4j.systems.module.core.Module;
@Module.Info(name = "SelfDestruct", description = "Removes Syracuse from your game", category = Category.MISC)

public class SelfDestruct extends Module {

    @Override
    public void onEnable() {
        // TODO: add self destruct
    }
}

