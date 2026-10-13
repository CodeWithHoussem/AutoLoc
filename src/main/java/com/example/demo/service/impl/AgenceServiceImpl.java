package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Agence;
import com.example.demo.repository.AgenceRepository;
import com.example.demo.service.AgenceService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AgenceServiceImpl implements AgenceService {

    private final AgenceRepository agenceRepository;

    @Override
    @Transactional
    public Agence ajouterAgence(Agence agence) {
        agence.setIdAgence(null); // force la creation

        return agenceRepository.save(agence);
    }

    @Override
    @Transactional
    public Agence modifierAgence(Long id, Agence agence) {
        Agence existant = trouver(id);
        if (agence.getNom() != null) {
            existant.setNom(agence.getNom());
        }
        if (agence.getVille() != null) {
            existant.setVille(agence.getVille());
        }
        if (agence.getAdresse() != null) {
            existant.setAdresse(agence.getAdresse());
        }
        if (agence.getTelephone() != null) {
            existant.setTelephone(agence.getTelephone());
        }
        return agenceRepository.save(existant);
    }

    @Override
    @Transactional(readOnly = true)
    public Agence consulterAgence(Long id) {
        return trouver(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Agence> consulterToutesLesAgences() {
        return agenceRepository.findAll();
    }

    @Override
    @Transactional
    public void supprimerAgenceById(Long id) {
        if (!agenceRepository.existsById(id)) {
            throw new EntityNotFoundException("Agence introuvable avec l'id " + id);
        }
        agenceRepository.deleteById(id);
    }

    private Agence trouver(Long id) {
        return agenceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agence introuvable avec l'id " + id));
    }
}
