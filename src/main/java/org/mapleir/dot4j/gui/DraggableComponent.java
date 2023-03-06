package org.mapleir.dot4j.gui;

import org.mapleir.dot4j.helper.utils.RenderUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;

public class DraggableComponent {

    private final HUDModule module;
    private final MinecraftClient mc;

    private boolean dragging;
    private double dragX, dragY;

    public DraggableComponent(HUDModule module) {
        this.module = module;
        this.mc = MinecraftClient.getInstance();
        dragging = false;
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY) {
        if(dragging) {
            int newX = (int) (mouseX - dragX);
            int newY = (int) (mouseY - dragY);

            if(newX >= 0 && newX + module.getWidth() <= mc.getWindow().getScaledWidth()) {
                module.setX(newX);
            }

            if (newY >= 0  && newY + module.getHeight() <= mc.getWindow().getScaledHeight()) {
                module.setY(newY);
            }

            HUDConfigScreen.components.forEach(c -> {
                if (c != this) c.setDragging(false);
            });
        }

        RenderUtils.drawHollowRect(matrices, module.getX(), module.getY(), module.getWidth(), module.getHeight(), new Color(0, 255, 255).getRGB(), 1);
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        if(isInside(mouseX, mouseY, module.getX(), module.getY(), module.getX() + module.getWidth(), module.getY() + module.getHeight()) && button == 0) {
            dragging = true;
            dragX = (int) (mouseX - module.getX());
            dragY = (int) (mouseY - module.getY());
        }
    }

    public void mouseReleased(double mouseX, double mouseY, int button) {
        if(button == 0) dragging = false;
    }

    public HUDModule getModule() {
        return module;
    }

    private boolean isInside(double mouseX, double mouseY, double x, double y, double x2, double y2) {
        return (mouseX > x && mouseX < x2) && (mouseY > y && mouseY < y2);
    }

    public void setDragging(boolean dragging) {
        this.dragging = dragging;
    }
}
