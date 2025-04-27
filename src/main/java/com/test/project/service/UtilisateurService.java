package com.test.project.service;

import com.test.project.model.Utilisateur;
import com.test.project.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<?> createUtilisateur(Utilisateur utilisateur) {
        if (utilisateurRepository.existsByUsername(utilisateur.getUsername())) {
            return ResponseEntity.badRequest().body("User already exists");
        }
        if (utilisateur.getId() == null) {
            utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
            utilisateurRepository.save(utilisateur);
            return ResponseEntity.ok().body(utilisateur.getUsername() + " created successfully");
        }
        return ResponseEntity.badRequest().body("User must not have ID");
    }

    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public Optional<Utilisateur> getUtilisateurById(Integer id) {
        return utilisateurRepository.findById(id);
    }

   

    public Utilisateur updateUtilisateur(Integer id, Utilisateur updatedUtilisateur) {
        return utilisateurRepository.findById(id)
                .map(utilisateur -> {
                    utilisateur.setUsername(updatedUtilisateur.getUsername());
                    utilisateur.setPassword(updatedUtilisateur.getPassword());
                    utilisateur.setRole(updatedUtilisateur.getRole());
                    return utilisateurRepository.save(utilisateur);
                })
                .orElseThrow(() -> new RuntimeException("Utilisateur not found with id: " + id));
    }

    public void deleteUtilisateur(Integer id) {
        utilisateurRepository.deleteById(id);
    }

    public ResponseEntity<?> login(Utilisateur user) {
        Utilisateur entity = utilisateurRepository.findByUsername(user.getUsername());
        
        if (entity == null) {
            return ResponseEntity.badRequest().body("Username not found");
        }
        
        if (!entity.getPassword().equals(user.getPassword())) {
            return ResponseEntity.badRequest().body("Incorrect password");
        }
    
        return ResponseEntity.ok().body("Login successful");
    }
    
}
