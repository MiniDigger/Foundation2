package me.MiniDigger.Foundation;


import com.torchmind.minecraft.annotation.Plugin;
import me.MiniDigger.Foundation.Handler.HandlerHandler;
import org.bukkit.plugin.java.JavaPlugin;

@Plugin(name = "Foundation", description = "Minigames Framework", author = "MiniDigger", version = "2.0-SNAPSHOT")
public class Foundation extends JavaPlugin {

    private static Foundation INSTANCE;
    private static boolean isUnitTesting = false;

    @Override
    public void onLoad() {
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        HandlerHandler.getInstance().load();
    }

    @Override
    public void onDisable() {
        HandlerHandler.getInstance().disable();
    }

    public static Foundation getInstance() {
        return INSTANCE;
    }

    public static boolean isIsUnitTesting() {
        return isUnitTesting;
    }

    public static void setIsUnitTesting(boolean isUnitTesting) {
        Foundation.isUnitTesting = isUnitTesting;
    }
}
