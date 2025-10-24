package dev.russojacopo.warofraces.repository;

import org.springframework.data.repository.CrudRepository;

import dev.russojacopo.warofraces.models.Creature;

public interface CreatureRepository extends CrudRepository<Creature, Long> {

}
