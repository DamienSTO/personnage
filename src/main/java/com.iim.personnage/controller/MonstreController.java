package com.iim.personnage.controller;

import com.iim.personnage.rpg.Monstre;
import com.iim.personnage.service.MonstreService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/monstre")
public class MonstreController {

    private final MonstreService monstreService;

    public MonstreController(MonstreService monstreService){
        this.monstreService = monstreService;
    }

    @PostMapping
    public Monstre create(@RequestParam String nom, @RequestParam int pv, @RequestParam int attaque){
        return monstreService.creer(nom, pv, attaque);
    }

    @GetMapping
    public Monstre get(){
        return monstreService.getMonstreActuel();
    }
}