package org.mapleir.dot4j.gui;

import org.mapleir.dot4j.systems.module.core.Module;
import org.mapleir.dot4j.systems.module.core.ModuleManager;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class HUDConfigScreen extends Screen {

    // unused, i might use this later on ykyk!!

    public static final List<DraggableComponent> components = new ArrayList<>();

    public HUDConfigScreen() {
        super(Text.of("cfg screen for hud ong"));
    }

    @Override
    protected void init() {
        components.clear();
        for(Module module : ModuleManager.INSTANCE.getModules()) {
            if(module.isEnabled() && module instanceof HUDModule hm) components.add(new DraggableComponent(hm));
        }
        super.init();
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        components.forEach(drag -> {
            if(drag.getModule().isEnabled())
                drag.render(matrices, mouseX, mouseY);
        });
        super.render(matrices, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        components.forEach(drag -> drag.mouseClicked(mouseX, mouseY, button));
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        components.forEach(drag -> drag.mouseReleased(mouseX, mouseY, button));
        return super.mouseReleased(mouseX, mouseY, button);
    }
}
