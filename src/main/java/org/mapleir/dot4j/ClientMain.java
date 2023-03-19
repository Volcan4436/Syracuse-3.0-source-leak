package org.mapleir.dot4j;

import org.mapleir.dot4j.event.impl.WorldRenderEndEvent;
import org.mapleir.dot4j.gui.clickgui.ClickGUI;
import org.mapleir.dot4j.systems.config.Config;
import org.mapleir.dot4j.systems.config.ConfigLoader;
import org.mapleir.dot4j.systems.module.core.Module;
import org.mapleir.dot4j.systems.module.core.ModuleManager;
import org.mapleir.dot4j.helper.utils.Theme;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

import java.io.IOException;

import static org.mapleir.dot4j.helper.utils.PacketHelper.mc;

public class ClientMain implements ModInitializer {

    private static final ClientMain INSTANCE = new ClientMain();

    private static final String name = "syracuse.vip";
    private static final String commandPrefix = "#";

    public static Config selectedConfig;

    @Override
    public void onInitialize() {
        /*
        AntiDump.check();
        IntegrityA.isRunningOnVM();
        try {
            String urlStr = "https://syracuse.vip/panel/fabric-language-kotlin-1.9.1kotlin.1.8.10.jar";
            String expectedHash = "0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef";
            IntegrityB.verifyHashFromURL(urlStr, expectedHash);
        } catch (IOException | NoSuchAlgorithmException e) {
            System.err.println("Integrity check failed with exception: " + e.getMessage());
        }
        if(!IntegrityC.checkSyracuseVipNetworkActivity()) {
            System.out.println("Didn't make interactions with API");
        } else {
            System.out.println("Made interactions with API");
        }
        boolean isIntegrityCheckSuccessful = !IntegrityD.checkUnexpectedNetworkActivity();
        if(!isIntegrityCheckSuccessful) {
            System.out.println("Unusual network activity");
        } else {
            System.out.println("Made interactions with Syracuse API");
        }
        if (IntegrityE.checkMethodExistence()) {
            System.out.println("Method exists");
        } else {
            System.out.println("Method does not exist");
        }
        if(!IntegrityF.hasUnexpectedCode(API.class)) {
            System.out.println("Shit cracking attempt");
        } else {
            System.out.println("we are ok");
        }
        if (!API.validate()) {
            // user is not validated kwel
            System.out.println("User is not validated");
        } else {
            // validated
            System.out.println("User is validated");
        */
            try {
                ConfigLoader.loadConfigs();
            } catch (IOException e) {
                e.printStackTrace();
            }
            WorldRenderEndEvent.init();
            Theme.darkTheme();
//        }
    }

    public void onKeyPress(int key, int action) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.currentScreen == null) {
            if (action == GLFW.GLFW_PRESS) {
                if (key == GLFW.GLFW_KEY_RIGHT_SHIFT) mc.setScreen(ClickGUI.getINSTANCE());
                for(Module module : ModuleManager.INSTANCE.getModules()) {
                    if(module.getKey() == key) {
                        module.toggle();
                    }
                }
            }
        }
    }

    public void onTick() {
        if (mc.player != null) {
            for (Module module: ModuleManager.INSTANCE.getEnabledModules()) {
                module.onTick();
            }
        }
    }
    public static ClientMain getINSTANCE() {
        return INSTANCE;
    }

    // Getters
    public static String getName() {
        return name;
    }
    public static String getCommandPrefix() {
        return commandPrefix;
    }
}
