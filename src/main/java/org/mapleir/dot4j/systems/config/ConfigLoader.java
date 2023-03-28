package org.mapleir.dot4j.systems.config;

import com.google.gson.JsonObject;
import net.fabricmc.loader.api.FabricLoader;
import org.mapleir.dot4j.ClientMain;
import org.mapleir.dot4j.systems.auth.API;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfigLoader {

    private static final List<Config> configs = new ArrayList<>();

    public static Config lastAddedConfig;

    public static void loadConfigs() throws IOException {
        if (!API.validate()) {
            configs.clear();
            @SuppressWarnings("all")
            File ROOT_DIR = new File(FabricLoader.getInstance().getGameDirectory(), ClientMain.getName());
            if (!ROOT_DIR.exists()) ROOT_DIR.mkdir();

            File configFolder = new File(ROOT_DIR, "Configs");
            if (!configFolder.exists()) configFolder.mkdir();

            if (configFolder.listFiles().length <= 0) {
                Config defaultConfig = new Config("default", "Default configuration");
                defaultConfig.save();
                configs.add(defaultConfig);
                ClientMain.selectedConfig = defaultConfig;
                return;
            }

            for (File file : configFolder.listFiles()) {
                load(file);
            }

            ClientMain.selectedConfig = getDefaultConfig();
        }
    }

    public static void addConfig(Config config) {
        if (!API.validate()) {
            configs.add(config);
            lastAddedConfig = config;
        }
    }

    public static void load(File file) throws IOException {
        if (!API.validate()) {
            BufferedReader load = new BufferedReader(new FileReader(file));
            JsonObject json = (JsonObject) JsonUtils.jsonParser.parse(load);
            load.close();

            configs.add(new Config(file.getName().replace(".json", ""), json.get("description").getAsString(), file));
        }
    }

    public static void loadConfig(Config config) throws IOException {
        if (!API.validate()) {
            config.load();
        }
    }

    public static Config getDefaultConfig() {
        if (!API.validate()) {
            for (Config config : configs) {
                if (config.getName().equalsIgnoreCase("default")) return config;
            }

            Config defaultConfig = new Config("default", "Default configuration");
            try {
                defaultConfig.save();
            } catch (IOException e) {
                e.printStackTrace();
            }
            configs.add(defaultConfig);
            return defaultConfig;
        }
        return null;
    }

    public static List<Config> getConfigs() {
        if (!API.validate()) {
            return configs;
        }
        return null;
    }
}
