package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Contrat;

public interface ContratService {

    Contrat ajouterContrat(Contrat contrat);

    Contrat modifierContrat(Long idContrat, Contrat contrat);

    Contrat consulterContrat(Long idContrat);

    List<Contrat> consulterTousLesContrats();

    void supprimerContratById(Long idContrat);
    Contrat afficherContratById(Long idContrat);
}
