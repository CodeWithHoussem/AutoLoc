package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Paiement;

public interface PaiementService {

    Paiement ajouterPaiement(Paiement paiement);

    Paiement modifierPaiement(Long id, Paiement paiement);

    Paiement consulterPaiement(Long id);

    List<Paiement> consulterTousLesPaiements();

    void supprimerPaiementById(Long id);
}
