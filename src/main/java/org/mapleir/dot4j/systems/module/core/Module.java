package org.mapleir.dot4j.systems.module.core;

import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.minecraft.client.util.math.MatrixStack;
import org.mapleir.dot4j.event.EventManager;
import org.mapleir.dot4j.gui.setting.BooleanSetting;
import org.mapleir.dot4j.gui.setting.ModeSetting;
import org.mapleir.dot4j.gui.setting.NumberSetting;
import org.mapleir.dot4j.gui.setting.Setting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import org.mapleir.dot4j.systems.module.core.Category;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Module {
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface Info {
        String name();
        String description();
        Category category();
    }
    private String name, description, displayName;
    private int key;
    private boolean enabled;
    private Category category;

    private boolean expanded;

    protected static MinecraftClient mc = MinecraftClient.getInstance();

    private final List<Setting> settings = new ArrayList<>();

    public Module() {
        Info info = getClass().getAnnotation(Info.class);
        if (info != null) {
            this.name = info.name();
            this.description = info.description();
            this.category = info.category();
            this.displayName = name;
        }
    }
    public void toggle() {
       this.enabled = !this.enabled;

       if(enabled) {
           onEnable();
       }
       else {
           onDisable();
       }
    }


    protected BooleanSetting registerBoolean(String name, boolean defaultValue) {
        BooleanSetting setting = new BooleanSetting(name, defaultValue);
        settings.add(setting);
        return setting;
    }

    protected NumberSetting registerNumber(String name, int defaultValue, int minValue, int maxValue, int increment) {
        NumberSetting setting = new NumberSetting(name, minValue, maxValue, defaultValue, increment);
        settings.add(setting);
        return setting;
    }

    protected ModeSetting registerMode(String name, List<String> modes, String defaultValue) {
        ModeSetting setting = new ModeSetting(name, defaultValue, String.valueOf(modes));
        settings.add(setting);
        return setting;
    }

    public List<Setting> getSettings() {
        return settings;
    }

    public void addSettings(Setting...settingz) {
        settings.addAll(Arrays.asList(settingz));
    }

    public void onEnable() {EventManager.register(this);}

    public void draw(MatrixStack matrices) {}

    public void onDisable() {EventManager.unregister(this);}
    public void onTick() {}

    public void onWorldRender(MatrixStack matrices) {}

    public void setEnabled(boolean toggled) {
        this.enabled = toggled;
        if(enabled) {
            onEnable();
        }
        else {
            onDisable();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    protected void sendMsg(String message) {
        if(mc.player == null) return;
        mc.player.sendMessage(Text.of(message.replace("&", "§")));
    }
}
