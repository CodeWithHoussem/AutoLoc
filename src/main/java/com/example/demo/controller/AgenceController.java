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

import com.example.demo.domain.Agence;
import com.example.demo.service.AgenceService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("agence")
public class AgenceController {

    private final AgenceService agenceService;

    @PostMapping
    public Agence ajouterAgence(@RequestBody Agence agence) {
        return agenceService.ajouterAgence(agence);
    }

    @PutMapping("/modifierAgence/{id}")
    public Agence modifierAgence(@PathVariable Long id, @RequestBody Agence agence) {
        return agenceService.modifierAgence(id, agence);
    }

    @GetMapping("/getById/{id}")
    public Agence getAgenceById(@PathVariable Long id) {
        return agenceService.consulterAgence(id);
    }

    @GetMapping("/getAll")
    public List<Agence> getAllAgences() {
        return agenceService.consulterToutesLesAgences();
    }

    @DeleteMapping("/supprimerAgence/{id}")
    public void supprimerAgenceById(@PathVariable Long id) {
        agenceService.supprimerAgenceById(id);
    }
}
