package org.mapleir.dot4j.helper.utils;

import org.mapleir.dot4j.ClientMain;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class ChatUtils {

    private MinecraftClient mc = MinecraftClient.getInstance();

    // unicode for §
    private final String paragraph = "\u00A7";

    public void sendMsg(String text) {
        if(mc.player != null) mc.player.sendMessage(Text.of(translate(text)));
    }

    public void sendMsg(Text text) {
        if(mc.player != null) mc.player.sendMessage(text);
    }

    public String translate(String text) {
        return text.replace("&", paragraph);
    }
}
