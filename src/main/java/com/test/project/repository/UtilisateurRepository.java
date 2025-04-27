package com.test.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.project.model.Utilisateur;
@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur,Integer> {
    public boolean existsByUsername(String username);
    public Utilisateur findByUsername(String username);
}
