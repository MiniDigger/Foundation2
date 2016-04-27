package me.MiniDigger.Foundation.Module;

import me.MiniDigger.Foundation.Foundation;

import java.io.File;

public class Module {

    private ModuleDescription description;

    public boolean onLoad() {
        return true;
    }

    public boolean onEnable() {
        return true;
    }

    public boolean onDisable() {
        return true;
    }

    public ModuleDescription getDescription() {
        return description;
    }

    public void setDescription(final ModuleDescription description) {
        this.description = description;
    }

    public File getDataFolder() {
        return new File(Foundation.getInstance().getDataFolder(), description.name());
    }
}