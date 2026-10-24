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

import com.example.demo.domain.Maintenance;
import com.example.demo.service.MaintenanceService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("maintenance")
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    @PostMapping
    public Maintenance ajouterMaintenance(@RequestBody Maintenance maintenance) {
        return maintenanceService.ajouterMaintenance(maintenance);
    }

    @PutMapping("/modifierMaintenance/{id}")
    public Maintenance modifierMaintenance(@PathVariable Long id, @RequestBody Maintenance maintenance) {
        return maintenanceService.modifierMaintenance(id, maintenance);
    }

    @GetMapping("/getById/{id}")
    public Maintenance getMaintenanceById(@PathVariable Long id) {
        return maintenanceService.consulterMaintenance(id);
    }

    @GetMapping("/getAll")
    public List<Maintenance> getAllMaintenances() {
        return maintenanceService.consulterToutesLesMaintenances();
    }

    @DeleteMapping("/supprimerMaintenance/{id}")
    public void supprimerMaintenanceById(@PathVariable Long id) {
        maintenanceService.supprimerMaintenanceById(id);
    }
}
