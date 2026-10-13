package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Agence;
import com.example.demo.domain.Employe;
import com.example.demo.repository.AgenceRepository;
import com.example.demo.repository.EmployeRepository;
import com.example.demo.service.EmployeService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeServiceImpl implements EmployeService {

    private final EmployeRepository employeRepository;
    private final AgenceRepository agenceRepository;

    @Override
    @Transactional
    public Employe ajouterEmploye(Employe employe) {
        employe.setIdEmploye(null); // force la creation
        employe.setAgence(resoudreAgence(employe.getAgence()));
        return employeRepository.save(employe);
    }

    @Override
    @Transactional
    public Employe modifierEmploye(Long id, Employe employe) {
        Employe existant = trouver(id);
        if (employe.getNom() != null) {
            existant.setNom(employe.getNom());
        }
        if (employe.getPrenom() != null) {
            existant.setPrenom(employe.getPrenom());
        }
        if (employe.getRole() != null) {
            existant.setRole(employe.getRole());
        }
        if (employe.getAgence() != null) {
            existant.setAgence(resoudreAgence(employe.getAgence()));
        }
        return employeRepository.save(existant);
    }

    @Override
    @Transactional(readOnly = true)
    public Employe consulterEmploye(Long id) {
        return trouver(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employe> consulterTousLesEmployes() {
        return employeRepository.findAll();
    }

    @Override
    @Transactional
    public void supprimerEmployeById(Long id) {
        if (!employeRepository.existsById(id)) {
            throw new EntityNotFoundException("Employe introuvable avec l'id " + id);
        }
        employeRepository.deleteById(id);
    }

    private Employe trouver(Long id) {
        return employeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employe introuvable avec l'id " + id));
    }

    private Agence resoudreAgence(Agence ref) {
        if (ref == null) {
            return null;
        }
        if (ref.getIdAgence() == null) {
            throw new IllegalArgumentException("Agence : l'id est obligatoire pour l'associer");
        }
        return agenceRepository.findById(ref.getIdAgence())
                .orElseThrow(() -> new EntityNotFoundException("Agence introuvable avec l'id " + ref.getIdAgence()));
    }
}
