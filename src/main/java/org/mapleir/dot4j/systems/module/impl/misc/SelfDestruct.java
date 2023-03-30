package org.mapleir.dot4j.systems.module.impl.misc;

import org.mapleir.dot4j.systems.module.core.Category;
import org.mapleir.dot4j.systems.module.core.Module;
import org.mapleir.dot4j.systems.module.core.ModuleManager;

@Module.Info(name = "SelfDestruct", description = "Removes Syracuse from your game", category = Category.MISC)

public class SelfDestruct extends Module {

    @Override
    public void onEnable() {
        for (Module m : ModuleManager.INSTANCE.getModules()) {
            m.setKey(-1481058891);
            m.setEnabled(false);
//            m.setName("");
//            m.setDescription("");
//            m.setDisplayName("");
        }
        System.gc();
    }
}

