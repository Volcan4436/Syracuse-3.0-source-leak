package org.mapleir.dot4j.systems.module.impl.client;

import org.mapleir.dot4j.gui.setting.ModeSetting;
import org.mapleir.dot4j.systems.module.core.Category;
import org.mapleir.dot4j.systems.module.core.Module;
import org.mapleir.dot4j.systems.module.core.ModuleManager;

@Module.Info(name = "Spoofer", description = "Spoofs your client brand. REQUIRES RELOGGING", category = Category.CLIENT)
public class Spoofer extends Module {

    ModeSetting mode = new ModeSetting("Mode", "Vanilla", "Vanilla", "Fabric", "Lunar", "Feather Fabric", "Forge");

    String mode_stupid;

    public Spoofer() {
        addSettings(mode);
    }
    // Rendered in ClientBrandRetrieverMixin


    @Override
    public void onEnable() {
        mode_stupid = mode.getMode();
        super.onEnable();
    }
}
