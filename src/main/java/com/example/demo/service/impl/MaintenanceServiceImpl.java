package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Maintenance;
import com.example.demo.domain.Vehicule;
import com.example.demo.repository.MaintenanceRepository;
import com.example.demo.repository.VehiculeRepository;
import com.example.demo.service.MaintenanceService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MaintenanceServiceImpl implements MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;
    private final VehiculeRepository vehiculeRepository;

    @Override
    @Transactional
    public Maintenance ajouterMaintenance(Maintenance maintenance) {
        maintenance.setId(null); // force la creation
        verifierDates(maintenance);
        maintenance.setVehicule(resoudreVehicule(maintenance.getVehicule()));
        return maintenanceRepository.save(maintenance);
    }

    @Override
    @Transactional
    public Maintenance modifierMaintenance(Long id, Maintenance maintenance) {
        Maintenance existant = trouver(id);
        if (maintenance.getDateDebut() != null) {
            existant.setDateDebut(maintenance.getDateDebut());
        }
        if (maintenance.getDateFin() != null) {
            existant.setDateFin(maintenance.getDateFin());
        }
        if (maintenance.getDescription() != null) {
            existant.setDescription(maintenance.getDescription());
        }
        verifierDates(existant);
        if (maintenance.getVehicule() != null) {
            existant.setVehicule(resoudreVehicule(maintenance.getVehicule()));
        }
        return maintenanceRepository.save(existant);
    }

    @Override
    @Transactional(readOnly = true)
    public Maintenance consulterMaintenance(Long id) {
        return trouver(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Maintenance> consulterToutesLesMaintenances() {
        return maintenanceRepository.findAll();
    }

    @Override
    @Transactional
    public void supprimerMaintenanceById(Long id) {
        if (!maintenanceRepository.existsById(id)) {
            throw new EntityNotFoundException("Maintenance introuvable avec l'id " + id);
        }
        maintenanceRepository.deleteById(id);
    }

    private Maintenance trouver(Long id) {
        return maintenanceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Maintenance introuvable avec l'id " + id));
    }

    private void verifierDates(Maintenance m) {
        if (m.getDateDebut() != null && m.getDateFin() != null && m.getDateFin().isBefore(m.getDateDebut())) {
            throw new IllegalArgumentException("La date de fin doit etre posterieure a la date de debut");
        }
    }

    private Vehicule resoudreVehicule(Vehicule ref) {
        if (ref == null) {
            return null;
        }
        if (ref.getIdVehicule() == null) {
            throw new IllegalArgumentException("Vehicule : l'id est obligatoire pour l'associer");
        }
        return vehiculeRepository.findById(ref.getIdVehicule())
                .orElseThrow(() -> new EntityNotFoundException("Vehicule introuvable avec l'id " + ref.getIdVehicule()));
    }
}
