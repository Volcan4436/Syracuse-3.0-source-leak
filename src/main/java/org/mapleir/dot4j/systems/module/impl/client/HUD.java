package org.mapleir.dot4j.systems.module.impl.client;

import net.minecraft.client.util.math.MatrixStack;
import org.mapleir.dot4j.systems.module.core.Category;
import org.mapleir.dot4j.systems.module.core.Module;

@Module.Info(name = "HUD", description = "HUD Elements", category = Category.CLIENT)
public class HUD extends Module {
    @Override
    public void draw(MatrixStack matrices) {
        mc.textRenderer.drawWithShadow(matrices, "S", 2, 2, 0x6FA8DC);
        mc.textRenderer.drawWithShadow(matrices, "yracuse", 2 + mc.textRenderer.getWidth("S"), 2, 0xFFFFFF);
        mc.getCurrentFps();
        mc.textRenderer.drawWithShadow(matrices, "FPS: " + mc.getCurrentFps(), 2, 4 + mc.textRenderer.fontHeight, 0xFFFFFF);
        mc.player.getHealth();
        mc.textRenderer.drawWithShadow(matrices, "HP: " + mc.player.getHealth(), 2, 6 + mc.textRenderer.fontHeight * 2, 0xFFFFFF);
        mc.player.getBlockPos();
        mc.textRenderer.drawWithShadow(matrices, "XYZ: " + mc.player.getBlockPos().getX() + " " + mc.player.getBlockPos().getY() + " " + mc.player.getBlockPos().getZ(), 2, 8 + mc.textRenderer.fontHeight * 3, 0xFFFFFF);
    }
}
