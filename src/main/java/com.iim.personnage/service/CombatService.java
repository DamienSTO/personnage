package com.iim.personnage.service;

import com.iim.personnage.repository.EtatCombatRepository;
import com.iim.personnage.repository.MonstreRepository;
import com.iim.personnage.repository.JoueurRepository;

import com.iim.personnage.rpg.EtatCombat;
import com.iim.personnage.rpg.Joueur;
import com.iim.personnage.rpg.Monstre;
import org.springframework.stereotype.Service;

@Service
public class CombatService {

    private final JoueurService joueurService;
    private final MonstreService monstreService;
    private final JoueurRepository joueurRepository;
    private final MonstreRepository monstreRepository;
    private final EtatCombatRepository etatCombatRepository;


    private Joueur joueur;
    private Monstre monstre;


    public CombatService(JoueurService joueurService, MonstreService monstreService, JoueurRepository joueurRepository, MonstreRepository monstreRepository, EtatCombatRepository  etatCombatRepository){
        this.joueurService = joueurService;
        this.monstreService = monstreService;
        this.joueurRepository = joueurRepository;
        this.monstreRepository = monstreRepository;
        this.etatCombatRepository = etatCombatRepository;
    }

    private EtatCombat getEtat(){
        return etatCombatRepository.findById(1L).orElseGet(() -> {
            EtatCombat e = new EtatCombat();
            e.setCombatEnCours(false);
            return etatCombatRepository.save(e);
        });
    }

    public String demarrer(){
        this.joueur = joueurService.getJoueurActuel();

        if (joueur == null ){
            return "Il faut d'abord créer un joueur (POST)";
        }

        this.monstre = monstreService.creerNouveauMonstre();

        if (monstre == null ){
            return "Il faut crée un monstre (POST)";
        }

        EtatCombat etat = getEtat();
        etat.setCombatEnCours(true);
        etatCombatRepository.save(etat);

        return "Combat démarré ! "
                + joueur.getNom() + " (PV: " + joueur.getPv() + ") "
                + "VS "
                + monstre.getNom() + " (PV: " + monstre.getPv() + ")";
    }

    public String attaquer(int choix){

        EtatCombat etat = getEtat();

        if (!etat.isCombatEnCours()){
            return "Aucun combat en cours. Cliquez sur démarrer combat";
        }

        Joueur joueur = joueurService.getJoueurActuel();
        Monstre monstre = monstreService.getMonstreActuel();

        if (joueur == null || monstre == null){
            return "Joueur ou monstre introuvable";
        }

        if(!joueur.estVivant()){
            return "Le Héros est mort";

        }

        if (!monstre.estVivant()){
            return "Le monstre est déja mort. Démarrez un nouveau combat";
        }

        String resultat = joueur.attaquelist(monstre, choix);
        return apresActionJoueur( joueur, monstre, resultat);
    }

    public String utiliserObjet(int choix){

        EtatCombat etat = getEtat();

        if(!etat.isCombatEnCours()){
            return "Aucun combat en cours. Cliquez sur démarrer combat";
        }

        Joueur joueur = joueurService.getJoueurActuel();
        Monstre monstre = monstreService.getMonstreActuel();

        if(!joueur.estVivant()){
            return "Le Héros est mort";

        }

        if (!monstre.estVivant()){
            return "Le monstre est déja mort. Démarrez un nouveau combat";
        }

        String resultat = joueur.utiliserObjet(choix);
        return apresActionJoueur(joueur, monstre, resultat);
    }

    public String fuir(){
        EtatCombat etat = getEtat();
        etat.setCombatEnCours(false);
        etatCombatRepository.save(etat);
        return "Vous prenez la fuite.";
    }

    public String etat(){
        Joueur joueur = joueurService.getJoueurActuel();
        Monstre monstre = monstreService.getMonstreActuel();
        if (joueur == null || monstre == null) return "Aucun combat en cours.";

        boolean enCours = getEtat().isCombatEnCours();

        return (enCours ? "Combat en cours |" : "Pas de combat ")
                + joueur.getNom() + " PV: " + joueur.getPv()
                + " | Attaque: " + joueur.getAttaque()
                + " | Bonus: " + joueur.getBonusatt()
                + "  ||  "
                + monstre.getNom() + " PV: " + monstre.getPv()
                + " | Attaque: " + monstre.getAttaque();
    }

    private String apresActionJoueur(Joueur joueur, Monstre monstre, String resultat){
        if (monstre.estVivant()){
            resultat += " | " + monstre.actionAleatoire(joueur);
        }

        joueurRepository.save(joueur);
        monstreRepository.save(monstre);


        if (!joueur.estVivant() || !monstre.estVivant()){
            EtatCombat etat = getEtat();
            etat.setCombatEnCours(false);
            etatCombatRepository.save(etat);
            resultat += !joueur.estVivant()
                    ? " || Le " + monstre.getNom() + " a gagné !"
                    : " || Le " + joueur.getNom() + " a gagné !";
        }
        return resultat;
    }
}