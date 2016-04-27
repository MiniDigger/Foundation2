package me.MiniDigger.Foundation.Game;

import lombok.Getter;
import lombok.Setter;
import me.MiniDigger.Foundation.Phase.Phase;
import org.apache.commons.lang.Validate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Game {

    @Getter
    private UUID uuid;
    @Getter
    private int maxPlayers;
    @Getter
    private int minPlayers;
    @Getter
    private List<UUID> players;
    @Getter
    private List<UUID> spectators;
    @Getter
    private GameType gameType;
    @Getter
    @Setter
    private Phase currentPhase;

    public Game(UUID uuid, GameType type) {
        Validate.notNull(uuid);
        Validate.notNull(type);

        this.uuid = uuid;
        this.gameType = type;

        maxPlayers = type.getMaxPlayer();
        minPlayers = type.getMinPlayers();

        players = new ArrayList<>();
        spectators = new ArrayList<>();
    }

    public abstract void start();

    public abstract void end();
}
