package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Maintenance;

public interface MaintenanceService {

    Maintenance ajouterMaintenance(Maintenance maintenance);

    Maintenance modifierMaintenance(Long id, Maintenance maintenance);

    Maintenance consulterMaintenance(Long id);

    List<Maintenance> consulterToutesLesMaintenances();

    void supprimerMaintenanceById(Long id);
}
