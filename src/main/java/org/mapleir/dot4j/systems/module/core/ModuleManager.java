package org.mapleir.dot4j.systems.module.core;

import org.mapleir.dot4j.event.interfaces.Subscriptions;
import org.mapleir.dot4j.event.interfaces.impl.ISubscription;
import org.mapleir.dot4j.systems.module.impl.client.Arraylist;
import org.mapleir.dot4j.systems.module.impl.combat.*;
import org.mapleir.dot4j.systems.module.impl.misc.Plugins;
import org.mapleir.dot4j.systems.module.impl.movement.Fly;
import org.mapleir.dot4j.systems.module.impl.movement.VulcanHopTest;
import org.mapleir.dot4j.systems.module.impl.render.ESP;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {

    private List<Module> modules = new ArrayList<>();

    public static final ModuleManager INSTANCE = new ModuleManager();

    public ModuleManager() {
        init();
    }

    private void init() {
        add(new Arraylist());
        add(new InfiniteReach());
        add(new AimAssist());
        add(new Plugins());
        add(new AutoStun());
        add(new Hitboxes());
        add(new VulcanHopTest());
        add(new Fly());
        add(new Triggerbot());
        add(new Reach());
        add(new ESP());
    }

    public void add(Module m) {
        modules.add(m);
        if(m instanceof ISubscription) {
            Subscriptions.addSub((ISubscription) m);
        }
    }

    public void remove(Module m) {
        modules.remove(m);
    }

    public List<Module> getModules() {
        return modules;
    }

    public Module getModuleByName(String name){

        for(Module module : modules) {
            if(module.getName().equals(name)) return module;
        }

        return null;
    }

    public ArrayList<Module> getModulesByCategory(Category category) {
        ArrayList<Module> modules = new ArrayList<>();
        for(Module m : this.modules){
            if(m.getCategory().equals(category)){
                modules.add(m);
            }
        }
        return modules;
    }

    public Module getModuleByClass(Class<? extends Module> cls) {
        for (Module m : modules) {
            if (m.getClass() != cls) {
                continue;
            }
            return m;
        }
        return null;
    }

    public List<Module> getEnabledModules() {
        List<Module> enabled = new ArrayList<>();
        for(Module m : getModules()) {
            if(m.isEnabled()) {
                enabled.add(m);
            }
        }

        return enabled;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }
}
