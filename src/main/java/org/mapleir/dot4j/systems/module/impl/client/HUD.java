package org.mapleir.dot4j.systems.module.impl.client;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralTextContent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.mapleir.dot4j.systems.module.core.Category;
import org.mapleir.dot4j.systems.module.core.Module;

@Module.Info(name = "Watermark", description = "Shows your on Syracuse", category = Category.CLIENT)
public class HUD extends Module {
    @Override
    public void draw(MatrixStack matrices) {
        mc.textRenderer.drawWithShadow(matrices, "S", 2, 2, 0x6FA8DC);
        mc.textRenderer.drawWithShadow(matrices, "yracuse", 2 + mc.textRenderer.getWidth("S"), 2, 0xFFFFFF);
    }
}
