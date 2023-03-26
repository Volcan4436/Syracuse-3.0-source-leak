package org.mapleir.dot4j.systems.module.impl.movement;


import org.mapleir.dot4j.systems.module.core.Category;
import org.mapleir.dot4j.systems.module.core.Module;

@Module.Info(name = "FastFall", description = "Increases Fall Speed", category = Category.MOVEMENT)
public class FastFall extends Module {


    @Override
    public void onTick() {
        if (mc.player.fallDistance > 2) {
            mc.player.setVelocity(mc.player.getVelocity().x, -1, mc.player.getVelocity().z);
        }
    }
}
