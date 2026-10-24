package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Equipement;

public interface EquipementService {

    Equipement ajouterEquipement(Equipement equipement);

    Equipement modifierEquipement(Long id, Equipement equipement);

    Equipement consulterEquipement(Long id);

    List<Equipement> consulterTousLesEquipements();

    void supprimerEquipementById(Long id);
}
