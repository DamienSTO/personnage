package com.iim.personnage.controller;

import com.iim.personnage.rpg.Joueur;
import com.iim.personnage.service.JoueurService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/joueur")
public class JoueurController {

    private final JoueurService joueurService;

    public JoueurController(JoueurService joueurService){
        this.joueurService = joueurService;
    }

    @PostMapping
    public Joueur create(@RequestParam String nom, @RequestParam int pv, @RequestParam int attaque){
        return joueurService.creer(nom, pv, attaque);
    }

    @GetMapping
    public Joueur get(){
        return joueurService.getJoueurActuel();
    }
}