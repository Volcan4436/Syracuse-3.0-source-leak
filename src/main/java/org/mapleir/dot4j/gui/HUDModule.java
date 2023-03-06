package org.mapleir.dot4j.gui;

import net.minecraft.client.util.math.MatrixStack;
import org.mapleir.dot4j.systems.module.core.Category;
import org.mapleir.dot4j.systems.module.core.Module;

public abstract class HUDModule extends Module {

    private int x, y, width, height;
    public HUDModule(String name, String description, Category category, int x, int y, int width, int height) {
        super(name, description, category);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }



    public abstract void draw(MatrixStack matrices);

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
