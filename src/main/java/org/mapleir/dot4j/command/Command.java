package org.mapleir.dot4j.command;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Uuids;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public abstract class Command {

    private String name, description;
    private List<String> aliases;
    public MinecraftClient mc = MinecraftClient.getInstance();

    public Command(String name, String description, String...aliases) {
        this.name = name;
        this.description = description;
        this.aliases = Arrays.asList(aliases);
    }

    public abstract void onCmd(String message, String[] args);

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

    public List<String> getAliases() {
        return aliases;
    }

    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }

}