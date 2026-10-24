package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Reservation;

public interface ReservationService {

    Reservation ajouterReservation(Reservation reservation);

    Reservation modifierReservation(Long id, Reservation reservation);

    Reservation consulterReservation(Long id);

    List<Reservation> consulterToutesLesReservations();

    void supprimerReservationById(Long id);
}
