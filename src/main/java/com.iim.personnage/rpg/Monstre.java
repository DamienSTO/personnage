package com.iim.personnage.rpg;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "monstres")
public class Monstre extends Personnage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int pvMax;


    public Monstre() {
        super();
    }

    public Monstre(String nom, int pv, int attaque) {
        super(nom, pv, attaque);
        this.pvMax = pv;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPvMax(){
        return pvMax;
    }

    public void setPvMax(int pvMax){
        this.pvMax = pvMax;
    }

    public String actionAleatoire(Joueur joueur) {
        String[] actions = {"attaque", "soin"};
        int choix = (int) (Math.random() * actions.length);

        if (actions[choix].equals("attaque")) {
            return this.attaquer(joueur);
        } else {
            int soin = 15;
            this.pv = Math.min(this.pv + soin, this.pvMax);
            return nom + " se soigne de " + soin + " PV";
        }
    }
}