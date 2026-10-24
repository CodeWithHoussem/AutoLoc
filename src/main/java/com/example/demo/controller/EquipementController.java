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

import com.example.demo.domain.Equipement;
import com.example.demo.service.EquipementService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("equipement")
public class EquipementController {

    private final EquipementService equipementService;

    @PostMapping
    public Equipement ajouterEquipement(@RequestBody Equipement equipement) {
        return equipementService.ajouterEquipement(equipement);
    }

    @PutMapping("/modifierEquipement/{id}")
    public Equipement modifierEquipement(@PathVariable Long id, @RequestBody Equipement equipement) {
        return equipementService.modifierEquipement(id, equipement);
    }

    @GetMapping("/getById/{id}")
    public Equipement getEquipementById(@PathVariable Long id) {
        return equipementService.consulterEquipement(id);
    }

    @GetMapping("/getAll")
    public List<Equipement> getAllEquipements() {
        return equipementService.consulterTousLesEquipements();
    }

    @DeleteMapping("/supprimerEquipement/{id}")
    public void supprimerEquipementById(@PathVariable Long id) {
        equipementService.supprimerEquipementById(id);
    }
}
