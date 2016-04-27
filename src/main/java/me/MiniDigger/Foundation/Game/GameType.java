package me.MiniDigger.Foundation.Game;

import lombok.Getter;
import org.apache.commons.lang.Validate;

public enum GameType {

    DEBUG("DEBUG", 1, 10);

    @Getter
    private int minPlayers;
    @Getter
    private int maxPlayer;
    @Getter
    private String name;

    GameType(String name, int minPlayers, int maxPlayer) {
        Validate.notEmpty(name);
        Validate.isTrue(minPlayers > 0);
        Validate.isTrue(minPlayers < maxPlayer);

        this.minPlayers = minPlayers;
        this.maxPlayer = maxPlayer;
        this.name = name;
    }
}
