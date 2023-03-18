package org.mapleir.dot4j.command.impl;

import org.lwjgl.glfw.GLFW;
import org.mapleir.dot4j.command.Command;
import org.mapleir.dot4j.helper.utils.ChatUtils;
import org.mapleir.dot4j.helper.utils.KeyUtils;
import org.mapleir.dot4j.systems.module.core.Module;
import org.mapleir.dot4j.systems.module.core.ModuleManager;

import java.util.ArrayList;
import java.util.List;

public class Bind extends Command {
    public Bind() {
        super("bind", "Binds a specified module", "");
    }

    @Override
    public void onCmd(String message, String[] args) {
        if (args.length < 3) {
            ChatUtils.addChatMessage("Not enough arguments.");
            return;
        }

        String mod = args[1];
        String key = args[2];

        Module module = ModuleManager.INSTANCE.getModuleByName(mod);
        if (module == null) {
            ChatUtils.addChatMessage("Invalid module name.");
            return;
        }

        try {
            int keyInt = Integer.parseInt(key);
            int boundKey = Integer.parseInt(KeyUtils.getKey(keyInt));
            Module.setKey(boundKey);
            ChatUtils.addChatMessage("Bound " + mod + " to " + boundKey);
        } catch (NumberFormatException e) {
            ChatUtils.addChatMessage("Invalid key format.");
        }
    }

}
