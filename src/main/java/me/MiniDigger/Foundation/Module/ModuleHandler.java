package me.MiniDigger.Foundation.Module;

import java.io.File;
import java.net.MalformedURLException;
import java.util.*;

import me.MiniDigger.Foundation.Handler.Handler;
import me.MiniDigger.Foundation.Lang.Lang;
import org.bukkit.Bukkit;

public class ModuleHandler extends Handler {

    private static ModuleHandler INSTANCE;

    private final List<Module> modules = new ArrayList<>();

    private File moduleFolder;

    private final Map<String, Class<?>> classes = new HashMap<String, Class<?>>();
    private final Map<String, ModuleLoader> loaders = new LinkedHashMap<String, ModuleLoader>();

    @Override
    public boolean onLoad() {
        if (moduleFolder == null) {
            try {
                moduleFolder = new File(Bukkit.getWorldContainer(), "modules");
            } catch (NullPointerException e) {
            }
        }
        loadModules();
        return true;
    }

    @Override
    public boolean onEnable() {
        enableModules();
        return true;
    }

    @Override
    public boolean onDisable() {
        disableModules();
        return true;
    }

    public void enableModules() {
        for (final Module m : modules) {
            m.onEnable();
        }
    }

    public void disableModules() {
        for (final Module m : modules) {
            disable(m.getDescription().name(), false);
        }
        modules.clear();
    }

    public void loadModules() {
        if (moduleFolder == null) {
            return;
        }

        if (!moduleFolder.exists()) {
            moduleFolder.mkdirs();
        }

        for (final File f : moduleFolder.listFiles((dir, name) -> {
            return name.endsWith(".jar");
        })) {
            enable(f);
        }
    }

    public void enable(final String name) {
        enable(new File(moduleFolder, name + ".jar"));
    }

    public void enable(final File f) {
        try {
            final ModuleLoader loader = new ModuleLoader(getClass().getClassLoader(), f.toURI().toURL());
            loaders.put(loader.getDescription().name(), loader);
            modules.add(loader.getModule());
            loader.getModule().setDescription(loader.getDescription());
            loader.getModule().onLoad();
        } catch (MalformedURLException | InvalidModuleException | NoSuchFieldException | SecurityException | IllegalArgumentException
                | IllegalAccessException e) {
            Lang.error(e);
        }
    }

    public void disable(final String name, final boolean remove) {
        final Module m = getModule(name);
        if (m != null) {
            m.onDisable();
            final ModuleLoader l = loaders.remove(name);
            l.getClasses().clear();
            if (remove) {
                modules.remove(m);
            }
        }
    }

    public Module getModule(final String name) {
        for (final Module m : modules) {
            if (m.getDescription().name().equals(name)) {
                return m;
            }
        }
        return null;
    }

    public Class<?> getClassByName(final String name) {
        Class<?> cachedClass = classes.get(name);

        if (cachedClass != null) {
            System.out.println(name + " was cached");
            return cachedClass;
        } else {
            for (final String current : loaders.keySet()) {
                final ModuleLoader loader = loaders.get(current);

                try {
                    cachedClass = loader.findClass(name, false);
                } catch (final ClassNotFoundException e) {
                }
                if (cachedClass != null) {
                    System.out.println(name + " was cached in " + current);
                    return cachedClass;
                }
            }
        }
        return null;
    }

    public void setClass(final String name, final Class<?> clazz) {
        if (!classes.containsKey(name)) {
            classes.put(name, clazz);
        }
    }

    public void removeClass(final String name) {
        @SuppressWarnings("unused")
        Class<?> clazz = classes.remove(name);
        clazz = null; // Bye!
    }

    public static ModuleHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ModuleHandler();
        }
        return INSTANCE;
    }

    public void setModuleFolder(File file) {
        moduleFolder = file;
    }
}