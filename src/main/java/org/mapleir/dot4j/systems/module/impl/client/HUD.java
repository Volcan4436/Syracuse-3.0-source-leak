package org.mapleir.dot4j.systems.module.impl.client;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralTextContent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
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
        mc.textRenderer.drawWithShadow(matrices, "HP: " + mc.player.getHealth(), 2,  6 + mc.textRenderer.fontHeight * 2, 0x48FF38);
        mc.player.getBlockPos();
        mc.textRenderer.drawWithShadow(matrices, "XYZ: " + mc.player.getBlockPos().getX() + " " + mc.player.getBlockPos().getY() + " " + mc.player.getBlockPos().getZ(), 2, 8 + mc.textRenderer.fontHeight * 3, 0xFFFFFF);
        mc.player.getDisplayName();
        mc.textRenderer.drawWithShadow(matrices, "Your an EPIC Haxxer: " + mc.player.getDisplayName().getString(), 2, 10 + mc.textRenderer.fontHeight * 4, 0xFF6A54);
        mc.textRenderer.drawWithShadow(matrices, "Syracause On Top!!!", 2, 430 + mc.textRenderer.fontHeight * 6,0x6FA8DC);
    }
}
