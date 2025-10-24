package dev.russojacopo.warofraces.service;

import java.util.List;

import dev.russojacopo.warofraces.models.Creature;

public interface ICreature {

    List<Creature> getCreatures();

    Creature getCreature(Long id);

    boolean createCreature(Creature creatura);

    boolean updateCreature(Creature creatura);

    boolean deleteCreature(Long id);

    String fight(Long creature1Id, Long creature2Id);
}
