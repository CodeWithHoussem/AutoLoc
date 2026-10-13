package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Vehicule;

public interface VehiculeService {

    Vehicule ajouterVehicule(Vehicule vehicule);

    Vehicule modifierVehicule(Long id, Vehicule vehicule);

    Vehicule consulterVehicule(Long id);

    List<Vehicule> consulterTousLesVehicules();

    void supprimerVehiculeById(Long id);
}
