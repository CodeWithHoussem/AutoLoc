package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Client;
import com.example.demo.domain.Reservation;
import com.example.demo.domain.StatutReservation;
import com.example.demo.domain.Vehicule;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.VehiculeRepository;
import com.example.demo.service.ReservationService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ClientRepository clientRepository;
    private final VehiculeRepository vehiculeRepository;

    @Override
    @Transactional
    public Reservation ajouterReservation(Reservation reservation) {
        reservation.setIdReservation(null); // force la creation
        if (reservation.getStatut() == null) {
            reservation.setStatut(StatutReservation.EN_ATTENTE);
        }
        verifierDates(reservation);
        reservation.setClient(resoudreClient(reservation.getClient()));
        reservation.setVehicule(resoudreVehicule(reservation.getVehicule()));
        return reservationRepository.save(reservation);
    }

    @Override
    @Transactional
    public Reservation modifierReservation(Long id, Reservation reservation) {
        Reservation existant = trouver(id);
        if (reservation.getDateDebut() != null) {
            existant.setDateDebut(reservation.getDateDebut());
        }
        if (reservation.getDateFin() != null) {
            existant.setDateFin(reservation.getDateFin());
        }
        if (reservation.getStatut() != null) {
            existant.setStatut(reservation.getStatut());
        }
        verifierDates(existant);
        if (reservation.getClient() != null) {
            existant.setClient(resoudreClient(reservation.getClient()));
        }
        if (reservation.getVehicule() != null) {
            existant.setVehicule(resoudreVehicule(reservation.getVehicule()));
        }
        return reservationRepository.save(existant);
    }

    @Override
    @Transactional(readOnly = true)
    public Reservation consulterReservation(Long id) {
        return trouver(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> consulterToutesLesReservations() {
        return reservationRepository.findAll();
    }

    @Override
    @Transactional
    public void supprimerReservationById(Long id) {
        Reservation reservation = trouver(id);
        if (reservation.getContrat() != null) {
            throw new IllegalStateException("Impossible de supprimer la reservation " + id + " : un contrat y est associe");
        }
        reservationRepository.delete(reservation);
    }

    private Reservation trouver(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reservation introuvable avec l'id " + id));
    }

    private void verifierDates(Reservation r) {
        if (r.getDateDebut() != null && r.getDateFin() != null && r.getDateFin().isBefore(r.getDateDebut())) {
            throw new IllegalArgumentException("La date de fin doit etre posterieure ou egale a la date de debut");
        }
    }

    private Client resoudreClient(Client ref) {
        if (ref == null) {
            return null;
        }
        if (ref.getIdClient() == null) {
            throw new IllegalArgumentException("Client : l'id est obligatoire pour l'associer");
        }
        return clientRepository.findById(ref.getIdClient())
                .orElseThrow(() -> new EntityNotFoundException("Client introuvable avec l'id " + ref.getIdClient()));
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
