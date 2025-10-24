package dev.russojacopo.warofraces.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.russojacopo.warofraces.models.Creature;
import dev.russojacopo.warofraces.service.CreatureService;

@RestController
public class CreatureController {
    
    @Autowired
    public CreatureService service;

    // Crea una nuova creatura
    @PostMapping("/creatures")
    public boolean createCreature(@RequestBody Creature creature) {
        return service.createCreature(creature);
    }

    // Restituisce la lista di tutte le creature
    @GetMapping("/creatures")
    public List<Creature> getCreatures() {
        return service.getCreatures();
    }
    
    // Restituisce una creatura specifica in base all'ID
    @GetMapping("/creatures/{id}")
    public Creature getCreature(@PathVariable Long id) {
        return service.getCreature(id);
    }

    // Aggiorna una creatura esistente in base all'ID
    @PutMapping("/creatures/{id}")
    public boolean updateCreature(@PathVariable Long id, @RequestBody Creature creature) {
        creature.setId(id);
        return service.updateCreature(creature);
    }

    // Elimina una creatura in base all'ID
    @DeleteMapping("/creatures/{id}")
    public boolean deleteCreature(@PathVariable Long id) {
        return service.deleteCreature(id);
    }

}
