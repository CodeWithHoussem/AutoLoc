package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Paiement;
import com.example.demo.service.PaiementService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("paiement")
public class PaiementController {

    private final PaiementService paiementService;

    @PostMapping
    public Paiement ajouterPaiement(@RequestBody Paiement paiement) {
        return paiementService.ajouterPaiement(paiement);
    }

    @PutMapping("/modifierPaiement/{id}")
    public Paiement modifierPaiement(@PathVariable Long id, @RequestBody Paiement paiement) {
        return paiementService.modifierPaiement(id, paiement);
    }

    @GetMapping("/getById/{id}")
    public Paiement getPaiementById(@PathVariable Long id) {
        return paiementService.consulterPaiement(id);
    }

    @GetMapping("/getAll")
    public List<Paiement> getAllPaiements() {
        return paiementService.consulterTousLesPaiements();
    }

    @DeleteMapping("/supprimerPaiement/{id}")
    public void supprimerPaiementById(@PathVariable Long id) {
        paiementService.supprimerPaiementById(id);
    }
}
