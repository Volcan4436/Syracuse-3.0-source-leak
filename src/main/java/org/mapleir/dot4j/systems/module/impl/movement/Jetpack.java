package org.mapleir.dot4j.systems.module.impl.movement;

import org.mapleir.dot4j.systems.module.core.Category;
import org.mapleir.dot4j.systems.module.core.Module;

@Module.Info(name = "Jetpack", description = "Fly as if you have a jetpack", category = Category.MOVEMENT)
public class Jetpack extends Module {

    @Override
    public void onTick() {
        if(mc.options.jumpKey.isPressed()){
            mc.player.jump();
        }
        super.onTick();
    }
}
