package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Agence;

public interface AgenceService {

    Agence ajouterAgence(Agence agence);

    Agence modifierAgence(Long id, Agence agence);

    Agence consulterAgence(Long id);

    List<Agence> consulterToutesLesAgences();

    void supprimerAgenceById(Long id);
}
