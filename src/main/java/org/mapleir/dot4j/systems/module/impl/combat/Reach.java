package org.mapleir.dot4j.systems.module.impl.combat;

import org.mapleir.dot4j.gui.setting.NumberSetting;
import org.mapleir.dot4j.gui.setting.Setting;
import org.mapleir.dot4j.systems.module.core.Category;
import org.mapleir.dot4j.systems.module.core.Module;

public class Reach extends Module {
    public final NumberSetting size = new NumberSetting("Reach", 1, 6, 4, 0.1); // this is called size cuz i messed smth up in the settings n stuff :skull emoji x7:

    public Reach() {
        super("Reach", "Extends your reach", Category.COMBAT);
        addSettings(size);
    }

    // handled in ClientPlayerInteractionManagerMixin
}
