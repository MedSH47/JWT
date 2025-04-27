package com.test.project.controller;

import org.springframework.web.bind.annotation.RestController;

import com.test.project.model.Utilisateur;
import com.test.project.service.UtilisateurService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/utilisateur")
public class UserController {
    @Autowired
    private UtilisateurService utilisateurService;

    @PostMapping
    public ResponseEntity<?> createUtilisateur(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.createUtilisateur(utilisateur);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Utilisateur user) {
        return utilisateurService.login(user);
    }
    
}
