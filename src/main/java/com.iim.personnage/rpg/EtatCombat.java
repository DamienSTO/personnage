package com.iim.personnage.rpg;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class EtatCombat {


    @Id
    private long id = 1L;

    private boolean combatEnCours;

    public long getId() { return id;}
    public boolean isCombatEnCours(){ return combatEnCours;}
    public void setCombatEnCours(boolean combatEnCours) { this.combatEnCours = combatEnCours; }
}
