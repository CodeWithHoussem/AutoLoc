package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Agence;
import com.example.demo.domain.Equipement;
import com.example.demo.domain.StatutVehicule;
import com.example.demo.domain.Vehicule;
import com.example.demo.repository.AgenceRepository;
import com.example.demo.repository.EquipementRepository;
import com.example.demo.repository.VehiculeRepository;
import com.example.demo.service.VehiculeService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehiculeServiceImpl implements VehiculeService {

    private final VehiculeRepository vehiculeRepository;
    private final AgenceRepository agenceRepository;
    private final EquipementRepository equipementRepository;

    @Override
    @Transactional
    public Vehicule ajouterVehicule(Vehicule vehicule) {
        vehicule.setIdVehicule(null); // force la creation
        if (vehiculeRepository.existsByImmatriculation(vehicule.getImmatriculation())) {
            throw new IllegalStateException("Un vehicule avec l'immatriculation "
                    + vehicule.getImmatriculation() + " existe deja");
        }
        if (vehicule.getStatut() == null) {
            vehicule.setStatut(StatutVehicule.DISPONIBLE);
        }
        vehicule.setAgence(resoudreAgence(vehicule.getAgence()));
        vehicule.setEquipements(resoudreEquipements(vehicule.getEquipements()));
        return vehiculeRepository.save(vehicule);
    }

    @Override
    @Transactional
    public Vehicule modifierVehicule(Long id, Vehicule vehicule) {
        Vehicule existant = trouver(id);
        if (vehicule.getImmatriculation() != null
                && !vehicule.getImmatriculation().equals(existant.getImmatriculation())
                && vehiculeRepository.existsByImmatriculation(vehicule.getImmatriculation())) {
            throw new IllegalStateException("Un vehicule avec l'immatriculation "
                    + vehicule.getImmatriculation() + " existe deja");
        }
        if (vehicule.getImmatriculation() != null) {
            existant.setImmatriculation(vehicule.getImmatriculation());
        }
        if (vehicule.getMarque() != null) {
            existant.setMarque(vehicule.getMarque());
        }
        if (vehicule.getModele() != null) {
            existant.setModele(vehicule.getModele());
        }
        if (vehicule.getCategorie() != null) {
            existant.setCategorie(vehicule.getCategorie());
        }
        if (vehicule.getTarifJournalier() != null) {
            existant.setTarifJournalier(vehicule.getTarifJournalier());
        }
        if (vehicule.getStatut() != null) {
            existant.setStatut(vehicule.getStatut());
        }
        if (vehicule.getAgence() != null) {
            existant.setAgence(resoudreAgence(vehicule.getAgence()));
        }
        if (vehicule.getEquipements() != null && !vehicule.getEquipements().isEmpty()) {
            existant.getEquipements().clear();
            existant.getEquipements().addAll(resoudreEquipements(vehicule.getEquipements()));
        }
        return vehiculeRepository.save(existant);
    }

    @Override
    @Transactional(readOnly = true)
    public Vehicule consulterVehicule(Long id) {
        return trouver(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicule> consulterTousLesVehicules() {
        return vehiculeRepository.findAll();
    }

    @Override
    @Transactional
    public void supprimerVehiculeById(Long id) {
        if (!vehiculeRepository.existsById(id)) {
            throw new EntityNotFoundException("Vehicule introuvable avec l'id " + id);
        }
        vehiculeRepository.deleteById(id);
    }

    private Vehicule trouver(Long id) {
        return vehiculeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicule introuvable avec l'id " + id));
    }

    private List<Equipement> resoudreEquipements(List<Equipement> refs) {
        List<Equipement> resultat = new ArrayList<>();
        if (refs == null) {
            return resultat;
        }
        for (Equipement ref : refs) {
            if (ref.getIdEquipement() == null) {
                throw new IllegalArgumentException("Equipement : l'id est obligatoire pour l'associer");
            }
            resultat.add(equipementRepository.findById(ref.getIdEquipement())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Equipement introuvable avec l'id " + ref.getIdEquipement())));
        }
        return resultat;
    }

    private Agence resoudreAgence(Agence ref) {
        if (ref == null) {
            return null;
        }
        if (ref.getIdAgence() == null) {
            throw new IllegalArgumentException("Agence : l'id est obligatoire pour l'associer");
        }
        return agenceRepository.findById(ref.getIdAgence())
                .orElseThrow(() -> new EntityNotFoundException("Agence introuvable avec l'id " + ref.getIdAgence()));
    }
}
