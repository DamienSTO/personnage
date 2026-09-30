package com.iim.personnage.repository;

import com.iim.personnage.rpg.Joueur;
import com.iim.personnage.rpg.Monstre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MonstreRepository extends JpaRepository<Monstre, Long> {
    Optional<Monstre> findFirstByOrderByIdDesc();
}