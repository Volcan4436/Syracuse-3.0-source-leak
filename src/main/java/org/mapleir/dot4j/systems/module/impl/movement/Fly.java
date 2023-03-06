package org.mapleir.dot4j.systems.module.impl.movement;

import org.mapleir.dot4j.event.EventTarget;
import org.mapleir.dot4j.event.impl.EventUpdate;
import org.mapleir.dot4j.systems.module.core.Category;
import org.mapleir.dot4j.systems.module.core.Module;
import org.mapleir.dot4j.gui.setting.BooleanSetting;
import org.mapleir.dot4j.gui.setting.ModeSetting;
import org.mapleir.dot4j.gui.setting.NumberSetting;

public class Fly extends Module {

    ModeSetting modeSetting = new ModeSetting("Mode", "Vanilla", "Vanilla", "Hypixel", "Vulcan");
    NumberSetting speed = new NumberSetting("Speed", 0.01, 1, 0.2, 0.01);
    BooleanSetting warn = new BooleanSetting("Warn", true);

    public Fly() {
        super("Fly", "Makes you fly!", Category.MOVEMENT);
        addSettings(modeSetting, speed, warn);
    }

    @EventTarget
    public void onUpdate(EventUpdate e) {
        if(mc.player == null) return;
        mc.player.getAbilities().flying = true;
        mc.player.getAbilities().setFlySpeed(speed.getFloatValue());
    }

    @Override
    public void onEnable() {
        super.onEnable();
        if(warn.isEnabled()) {
            sendMsg("&7Warning: You can still take fall damage!");
        }
        if(mc.player == null) return;
        mc.player.getAbilities().allowFlying = true;
    }

    @Override
    public void onDisable() {
        super.onDisable();

        if(mc.player == null) return;
        if(!mc.player.getAbilities().creativeMode) {
            mc.player.getAbilities().allowFlying = false;
            mc.player.getAbilities().flying = false;
        }
        mc.player.getAbilities().setFlySpeed(0.1f);
    }
}
