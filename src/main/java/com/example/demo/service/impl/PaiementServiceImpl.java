package com.example.demo.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Contrat;
import com.example.demo.domain.Paiement;
import com.example.demo.repository.ContratRepository;
import com.example.demo.repository.PaiementRepository;
import com.example.demo.service.PaiementService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaiementServiceImpl implements PaiementService {

    private final PaiementRepository paiementRepository;
    private final ContratRepository contratRepository;

    @Override
    @Transactional
    public Paiement ajouterPaiement(Paiement paiement) {
        paiement.setIdPaiement(null); // force la creation
        if (paiement.getDatePaiement() == null) {
            paiement.setDatePaiement(LocalDate.now());
        }
        paiement.setContrat(resoudreContrat(paiement.getContrat()));
        return paiementRepository.save(paiement);
    }

    @Override
    @Transactional
    public Paiement modifierPaiement(Long id, Paiement paiement) {
        Paiement existant = trouver(id);
        if (paiement.getMontant() != null) {
            existant.setMontant(paiement.getMontant());
        }
        if (paiement.getDatePaiement() != null) {
            existant.setDatePaiement(paiement.getDatePaiement());
        }
        if (paiement.getModePaiement() != null) {
            existant.setModePaiement(paiement.getModePaiement());
        }
        if (paiement.getContrat() != null) {
            existant.setContrat(resoudreContrat(paiement.getContrat()));
        }
        return paiementRepository.save(existant);
    }

    @Override
    @Transactional(readOnly = true)
    public Paiement consulterPaiement(Long id) {
        return trouver(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Paiement> consulterTousLesPaiements() {
        return paiementRepository.findAll();
    }

    @Override
    @Transactional
    public void supprimerPaiementById(Long id) {
        if (!paiementRepository.existsById(id)) {
            throw new EntityNotFoundException("Paiement introuvable avec l'id " + id);
        }
        paiementRepository.deleteById(id);
    }

    private Paiement trouver(Long id) {
        return paiementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paiement introuvable avec l'id " + id));
    }

    private Contrat resoudreContrat(Contrat ref) {
        if (ref == null) {
            return null;
        }
        if (ref.getIdContrat() == null) {
            throw new IllegalArgumentException("Contrat : l'id est obligatoire pour l'associer");
        }
        return contratRepository.findById(ref.getIdContrat())
                .orElseThrow(() -> new EntityNotFoundException("Contrat introuvable avec l'id " + ref.getIdContrat()));
    }
}
