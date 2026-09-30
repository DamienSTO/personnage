package com.iim.personnage.controller;

import com.iim.personnage.service.CombatService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/combat")
public class CombatController {

    private final CombatService combatService;

    public CombatController(CombatService combatService){
        this.combatService = combatService;
    }

    @GetMapping("/demarrer")
    public String demarrer(){
        return combatService.demarrer();
    }

    @PostMapping("/attaquer")
    public String attaquer(@RequestParam int choix){
        return combatService.attaquer(choix);
    }

    @PostMapping("/objet")
    public String objet(@RequestParam int choix){
        return combatService.utiliserObjet(choix);
    }

    @PostMapping("/fuir")
    public String fuir(){
        return combatService.fuir();
    }

    @GetMapping("/etat")
    public String etat(){
        return combatService.etat();
    }
}
