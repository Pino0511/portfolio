package dev.russojacopo.warofraces.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.russojacopo.warofraces.service.FightService;

@RestController
@RequestMapping("/fight")
public class FightController {

    @Autowired
    public FightService fightService;

    // Esegui il combattimento tra due creature
    @GetMapping
    public String fight(@RequestParam Long id1, @RequestParam Long id2){
        return fightService.fight(id1, id2);
    }
}
