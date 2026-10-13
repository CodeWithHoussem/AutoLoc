package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Employe;

public interface EmployeService {

    Employe ajouterEmploye(Employe employe);

    Employe modifierEmploye(Long id, Employe employe);

    Employe consulterEmploye(Long id);

    List<Employe> consulterTousLesEmployes();

    void supprimerEmployeById(Long id);
}
