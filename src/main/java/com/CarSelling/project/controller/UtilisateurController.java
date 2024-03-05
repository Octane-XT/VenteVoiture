package com.CarSelling.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CarSelling.project.authentification.JwtGenerator;
import com.CarSelling.project.entity.UtilisateurEntity;
import com.CarSelling.project.service.UtilisateurService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/api/usercontroller")
public class UtilisateurController {
    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private JwtGenerator jwtGenerator;

    @PostMapping("/login")
    public String controlConnexion(HttpServletRequest request) throws Exception {
        try {
            String email = request.getParameter("email");
            String mdp = request.getParameter("mdp");
            String token = this.controlUtilisateur(email, mdp);
            return token;
        } catch (Exception e) {
            throw e;
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<String> insertUtilisateur(HttpServletRequest request) throws Exception {
        try {
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String dtn = request.getParameter("dtn");
            String mdp = request.getParameter("mdp");
            String email = request.getParameter("email");
            Integer sexe = Integer.valueOf(request.getParameter("sexe"));

            this.utilisateurService.insertUser(nom, prenom, dtn, sexe, email, mdp);
            return ResponseEntity.ok("Tongasoa, Bienvenue ");
        } catch (Exception e) {
            throw e;
        }
    }

    public String controlUtilisateur(String email, String mdp) throws Exception {
        UtilisateurEntity user = null;
        try {
            user = this.utilisateurService.getCurrentUser(email, mdp);
            if (user == null) {
                throw new Exception("Il y a erreur, veuillez vérifer votre email ou votre mot de passe");
            } else {
                return this.generateToken(user.getIdutilisateur(), user.getNom());
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/test")
    public String test() throws Exception {
        try {
            return "helloooooo";
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/generate")
    public String generateToken(Integer id, String username) throws Exception {
        String idString = String.valueOf(id);
        try {
            return jwtGenerator.generateJwt(idString, username);
        } catch (Exception e) {
            throw e;
        }
    }

}
