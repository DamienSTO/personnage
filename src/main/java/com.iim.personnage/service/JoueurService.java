package com.iim.personnage.service;

import com.iim.personnage.repository.JoueurRepository;
import com.iim.personnage.rpg.Joueur;
import org.springframework.stereotype.Service;

@Service
public class JoueurService {

    private final JoueurRepository joueurRepository;

    public JoueurService(JoueurRepository joueurRepository) {
        this.joueurRepository = joueurRepository;
    }

    public Joueur creer(String nom, int pv, int attaque) {
        Joueur joueur = new Joueur(nom, pv, attaque);

        return joueurRepository.save(joueur);
    }

    public Joueur getJoueurActuel() {

        return joueurRepository.findFirstByOrderByIdDesc().orElse(null);
    }
}