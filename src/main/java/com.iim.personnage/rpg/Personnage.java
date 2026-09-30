package com.iim.personnage.rpg;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Personnage {

    @JsonProperty("nom")
    protected String nom;
    @JsonProperty("pv")
    protected int pv;
    @JsonProperty("attaque")
    protected int attaque;
    @JsonProperty("bonusatt")
    protected int bonusatt = 0;


    public Personnage() {
    }

    public Personnage(String nom, int pv, int attaque) {
        this.nom = nom;
        this.pv = pv;
        this.attaque = attaque;
    }

    public String attaquer(Personnage cible) {
        int degats = calculerDegats() + bonusatt;
        bonusatt = 0;
        cible.pv -= degats;
        return nom + " inflige " + degats + " point(s) de dégâts à " + cible.nom;
    }

    public boolean estVivant() {
        return pv > 0;
    }

    protected int calculerDegats() {
        int degats = (attaque - 5) + (int) (Math.random() * ((attaque + 5) - (attaque - 5) + 1));
        return Math.max(1, degats);
    }

    public String getNom() { return nom; }
    public int getPv() { return pv; }
    public int getAttaque() { return attaque; }
    public int getBonusatt() { return bonusatt; }
}