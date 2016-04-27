package me.MiniDigger.Foundation.Phase;

import lombok.Getter;
import me.MiniDigger.Foundation.Feature.Feature;
import org.apache.commons.lang.Validate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Phase {
    @Getter
    private UUID uuid;
    @Getter
    private List<Feature> features;
    @Getter
    private Phase nextPhase;
    @Getter
    private String name;

    public Phase(UUID uuid,String name, Phase nextPhase){
        Validate.notNull(uuid);
        Validate.notEmpty(name);
        Validate.notNull(nextPhase);

        this.uuid = uuid;
        this.name = name;
        this.nextPhase = nextPhase;

        features = new ArrayList<>();
    }
}
