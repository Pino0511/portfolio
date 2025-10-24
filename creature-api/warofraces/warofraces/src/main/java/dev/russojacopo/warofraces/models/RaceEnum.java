package dev.russojacopo.warofraces.models;

public enum RaceEnum {

    // rappresentazione tipo di corsa per creatura
    HUMAN("Human"),
    VAMPIRE("Vampire"),
    ZOMBIE("Zombie"),
    DEMON("Demon");

    private final String race;

    RaceEnum(String race) {
        this.race = race;
    }

    // Getter
    public String getRace() {
        return race;
    }

}
