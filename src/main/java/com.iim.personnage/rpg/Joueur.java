package com.iim.personnage.rpg;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "joueurs")
public class Joueur extends Personnage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public Joueur() {
        super();
    }

    public Joueur(String nom, int pv, int attaque) {
        super(nom, pv, attaque);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String attaquelist(Monstre monstre, int choix) {

        int degats;

        if (choix == 1) {
            degats = calculerDegats() + 20 + bonusatt;
            bonusatt = 0;
            monstre.pv -= degats;

            return nom + " utilise boule de feu et inflige " + degats + " pt de dégâts";

        } else if (choix == 2) {
            degats = calculerDegats() + 50 + bonusatt;
            bonusatt = 0;
            monstre.pv -= degats;
            return nom + " utilise trou noir et inflige " + degats + " pt de dégâts";
        } else if (choix == 3) {
            degats = calculerDegats() + 30 + bonusatt;
            bonusatt = 0;
            monstre.pv -= degats;
            return nom + " utilise lance de glace et inflige " + degats + " pt de dégâts";
        } else {
            return "Choix invalide";
        }
    }

    public String utiliserObjet(int choix) {
        if (choix == 1) {
            this.pv += 20;
            return nom + " se soigne de 20 PV";
        } else if (choix == 2) {
            this.bonusatt += 20;
            return nom + " prépare une attaque critique (+20 dégâts au prochain coup)";
        } else {
            return "Choix invalide";
        }
    }
}