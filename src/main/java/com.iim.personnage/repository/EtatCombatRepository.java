package com.iim.personnage.repository;

import com.iim.personnage.rpg.EtatCombat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtatCombatRepository extends JpaRepository<EtatCombat, Long> {
}
