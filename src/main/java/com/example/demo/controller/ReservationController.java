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

import com.example.demo.domain.Reservation;
import com.example.demo.service.ReservationService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public Reservation ajouterReservation(@RequestBody Reservation reservation) {
        return reservationService.ajouterReservation(reservation);
    }

    @PutMapping("/modifierReservation/{id}")
    public Reservation modifierReservation(@PathVariable Long id, @RequestBody Reservation reservation) {
        return reservationService.modifierReservation(id, reservation);
    }

    @GetMapping("/getById/{id}")
    public Reservation getReservationById(@PathVariable Long id) {
        return reservationService.consulterReservation(id);
    }

    @GetMapping("/getAll")
    public List<Reservation> getAllReservations() {
        return reservationService.consulterToutesLesReservations();
    }

    @DeleteMapping("/supprimerReservation/{id}")
    public void supprimerReservationById(@PathVariable Long id) {
        reservationService.supprimerReservationById(id);
    }
}
