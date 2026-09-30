package com.iim.personnage.service;

import com.iim.personnage.repository.MonstreRepository;
import com.iim.personnage.rpg.Monstre;
import org.springframework.stereotype.Service;

@Service
public class MonstreService {

    private final MonstreRepository monstreRepository;

    public MonstreService(MonstreRepository monstreRepository) {
        this.monstreRepository = monstreRepository;
    }

    public Monstre creer(String nom, int pv, int attaque) {
        Monstre monstre = new Monstre(nom, pv, attaque);

        return monstreRepository.save(monstre);
    }

    public Monstre getMonstreActuel() {

        return monstreRepository.findFirstByOrderByIdDesc().orElse(null);
    }

    public Monstre creerNouveauMonstre(){
        Monstre ancienMonstre = getMonstreActuel();

        if (ancienMonstre == null){
            return null;
        }

        Monstre nouveauMonstre = new Monstre(
                ancienMonstre.getNom(),
                ancienMonstre.getPvMax(),
                ancienMonstre.getAttaque()
        );

        return monstreRepository.save(nouveauMonstre);
    }
}