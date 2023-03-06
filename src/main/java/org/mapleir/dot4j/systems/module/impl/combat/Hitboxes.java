package org.mapleir.dot4j.systems.module.impl.combat;

import org.mapleir.dot4j.gui.setting.NumberSetting;
import org.mapleir.dot4j.systems.module.core.Category;
import org.mapleir.dot4j.systems.module.core.Module;

public class Hitboxes extends Module {

    public NumberSetting size = new NumberSetting("Size", 0.1, 2, 0.3, 0.1);

    public Hitboxes() {
        super("Hitboxes", "Expands hitboxes", Category.COMBAT);
        addSettings(size);
    }


    // handled in EntityMixin
}