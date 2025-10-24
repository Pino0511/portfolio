package dev.russojacopo.warofraces.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.russojacopo.warofraces.models.Creature;
import dev.russojacopo.warofraces.repository.CreatureRepository;

@Service
public class CreatureService {

    @Autowired
    public CreatureRepository repository;

    // Metodo per creare una nuova creatura
    public boolean createCreature(Creature creature) {
        if (creature != null) {
            repository.save(creature);
            return true;
        }
        return false;
    }

    // Metodo per ottenere tutte le creature
    public List<Creature> getCreatures() {
        return (List<Creature>) repository.findAll();
    }

    // Metodo per ottenere una creatura specifica in base all'ID
    public Creature getCreature(Long id) {
        return repository.findById(id).orElse(null);
    }

    // Metodo per aggiornare una creatura esistente
    public boolean updateCreature(Creature creature) {
        if (creature != null) {
            repository.save(creature);
            return true;
        }
        return false;
    }

    // Metodo per eliminare una creatura in base all'ID
    public boolean deleteCreature(Long id) {
        if (id == null) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }

}
