package dev.russojacopo.warofraces.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Creature {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private RaceEnum race;
    private int strength;
    private int defense;
    private int health;
    private int level;

    // Contruttore di default
    public Creature() {
    }

    // Construttore
    public Creature(String name, int strength, int defense, int health, int level) {
        this.name = name;
        this.strength = strength;
        this.defense = defense;
        this.health = health;
        this.level = level;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RaceEnum getRace() {
        return race;
    }
    public void setRace(RaceEnum race) {
        this.race = race;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    // Override Metodo ToString
    @Override
    public String toString() {
        return "Creature{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", race='" + race + '\'' +
                ", strength=" + strength +
                ", defense=" + defense +
                ", health=" + health +
                ", level=" + level +
                '}';
    }

}