package me.MiniDigger.Foundation.Game;

import lombok.Getter;
import me.MiniDigger.Foundation.Handler.Handler;

import java.util.List;

public class GameHandler extends Handler {
    private static GameHandler INSTANCE;

    @Getter
    private List<Game> games;

    public static GameHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GameHandler();
        }
        return INSTANCE;
    }

    @Override
    public boolean onLoad() {
        return true;
    }

    @Override
    public boolean onEnable() {
        return false;
    }

    @Override
    public boolean onDisable() {
        INSTANCE = null;
        return true;
    }
}
