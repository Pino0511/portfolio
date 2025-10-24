package dev.russojacopo.warofraces.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.russojacopo.warofraces.models.Creature;
@Service
public class FightService {
    @Autowired
    public CreatureService creatureService;

    public String fight(Long creature1Id, Long creature2Id) {
        // Recupera le creature dai loro ID
        Creature creature1 = creatureService.getCreature(creature1Id);
        Creature creature2 = creatureService.getCreature(creature2Id);

        //Calcola i danni inflitti a ciascuna creatura
        int damageToCreature2 = Math.max(0, creature1.getStrength() - creature2.getDefense());
        int damageToCreature1 = Math.max(0, creature2.getStrength() - creature1.getDefense());

        // Aggiorna la salute delle creature
        creature1.setHealth(creature1.getHealth() - damageToCreature2);
        creature2.setHealth(creature2.getHealth() - damageToCreature1);

        // Controlla il risultato del combattimento
        if (creature1.getHealth() <= 0 && creature2.getHealth() <= 0) {
            return "Pareggio";
        } else if (creature1.getHealth() <= 0) {
            return creature2.getName() + " ha vinto";
        } else if (creature2.getHealth() <= 0) {
            return creature1.getName() + " ha vinto";
        } else {
            return "Nessuno ha ancora vinto";
        }
    }
}
