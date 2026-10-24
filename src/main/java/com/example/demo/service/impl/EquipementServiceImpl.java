package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Equipement;
import com.example.demo.domain.Vehicule;
import com.example.demo.repository.EquipementRepository;
import com.example.demo.service.EquipementService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EquipementServiceImpl implements EquipementService {

    private final EquipementRepository equipementRepository;

    @Override
    @Transactional
    public Equipement ajouterEquipement(Equipement equipement) {
        equipement.setIdEquipement(null); // force la creation

        return equipementRepository.save(equipement);
    }

    @Override
    @Transactional
    public Equipement modifierEquipement(Long id, Equipement equipement) {
        Equipement existant = trouver(id);
        if (equipement.getLibelle() != null) {
            existant.setLibelle(equipement.getLibelle());
        }
        return equipementRepository.save(existant);
    }

    @Override
    @Transactional(readOnly = true)
    public Equipement consulterEquipement(Long id) {
        return trouver(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Equipement> consulterTousLesEquipements() {
        return equipementRepository.findAll();
    }

    @Override
    @Transactional
    public void supprimerEquipementById(Long id) {
        Equipement equipement = trouver(id);
        // Vehicule est le cote proprietaire de la relation : on detache l'equipement avant suppression
        for (Vehicule v : new ArrayList<>(equipement.getVehicules())) {
            v.getEquipements().remove(equipement);
        }
        equipementRepository.delete(equipement);
    }

    private Equipement trouver(Long id) {
        return equipementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Equipement introuvable avec l'id " + id));
    }
}
