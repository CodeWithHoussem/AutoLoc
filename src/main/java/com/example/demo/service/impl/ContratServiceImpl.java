package com.example.demo.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Contrat;
import com.example.demo.domain.Paiement;
import com.example.demo.domain.Reservation;
import com.example.demo.repository.ContratRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.service.ContratService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContratServiceImpl implements ContratService {

    private final ContratRepository contratRepository;
    private final ReservationRepository reservationRepository;

    @Override
    @Transactional
    public Contrat ajouterContrat(Contrat contrat) {
        contrat.setIdContrat(null); // force la creation d'un nouveau contrat

        if (contrat.getDateSignature() == null) {
            contrat.setDateSignature(LocalDate.now());
        }
        if (contrat.getValide() == null) {
            contrat.setValide(false);
        }

        contrat.setReservation(resoudreReservation(contrat.getReservation(), null));

        // Les paiements sont en cascade ALL : on renseigne le lien retour
        if (contrat.getPaiements() != null) {
            for (Paiement p : contrat.getPaiements()) {
                p.setContrat(contrat);
            }
        }
        return contratRepository.save(contrat);
    }

    @Override
    @Transactional
    public Contrat modifierContrat(Long idContrat, Contrat contrat) {
        Contrat existant = trouver(idContrat);

        if (contrat.getDateSignature() != null) {
            existant.setDateSignature(contrat.getDateSignature());
        }
        if (contrat.getMontantTotal() != null) {
            existant.setMontantTotal(contrat.getMontantTotal());
        }
        if (contrat.getValide() != null) {
            existant.setValide(contrat.getValide());
        }
        if (contrat.getReservation() != null) {
            existant.setReservation(resoudreReservation(contrat.getReservation(), idContrat));
        }
        return contratRepository.save(existant);
    }

    @Override
    @Transactional(readOnly = true)
    public Contrat consulterContrat(Long idContrat) {
        return trouver(idContrat);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contrat> consulterTousLesContrats() {
        return contratRepository.findAll();
    }

    @Override
    @Transactional
    public void supprimerContratById(Long idContrat) {
        if (!contratRepository.existsById(idContrat)) {
            throw new EntityNotFoundException("Contrat introuvable avec l'id " + idContrat);
        }
        contratRepository.deleteById(idContrat); // supprime aussi ses paiements (cascade ALL)
    }

    private Contrat trouver(Long idContrat) {
        return contratRepository.findById(idContrat)
                .orElseThrow(() -> new EntityNotFoundException("Contrat introuvable avec l'id " + idContrat));
    }

    /**
     * Charge la reservation depuis la base et verifie qu'elle n'a pas deja un autre contrat.
     * idContratCourant = contrat en cours de modification (null lors d'un ajout).
     */
    private Reservation resoudreReservation(Reservation reservation, Long idContratCourant) {
        if (reservation == null) {
            return null;
        }
        if (reservation.getIdReservation() == null) {
            throw new IllegalArgumentException("La reservation du contrat doit avoir un idReservation");
        }
        Reservation r = reservationRepository.findById(reservation.getIdReservation())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Reservation introuvable avec l'id " + reservation.getIdReservation()));

        Contrat deja = r.getContrat();
        if (deja != null && !deja.getIdContrat().equals(idContratCourant)) {
            throw new IllegalStateException("La reservation " + r.getIdReservation() + " a deja un contrat");
        }
        return r;
    }
}
