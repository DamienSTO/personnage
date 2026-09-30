package com.iim.personnage.repository;

import com.iim.personnage.rpg.Joueur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JoueurRepository extends JpaRepository<Joueur, Long> {
    Optional<Joueur> findFirstByOrderByIdDesc();
}