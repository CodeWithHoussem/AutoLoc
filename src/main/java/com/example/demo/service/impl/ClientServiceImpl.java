package com.example.demo.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Client;
import com.example.demo.repository.ClientRepository;
import com.example.demo.service.ClientService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    @Transactional
    public Client ajouterClient(Client client) {
        client.setIdClient(null); // force la creation
        if (clientRepository.existsByEmail(client.getEmail())) {
            throw new IllegalStateException("Un client avec l'email " + client.getEmail() + " existe deja");
        }
        if (client.getDateInscription() == null) {
            client.setDateInscription(LocalDate.now());
        }
        return clientRepository.save(client);
    }

    @Override
    @Transactional
    public Client modifierClient(Long id, Client client) {
        Client existant = trouver(id);
        if (client.getEmail() != null && !client.getEmail().equals(existant.getEmail())
                && clientRepository.existsByEmail(client.getEmail())) {
            throw new IllegalStateException("Un client avec l'email " + client.getEmail() + " existe deja");
        }
        if (client.getNom() != null) {
            existant.setNom(client.getNom());
        }
        if (client.getPrenom() != null) {
            existant.setPrenom(client.getPrenom());
        }
        if (client.getEmail() != null) {
            existant.setEmail(client.getEmail());
        }
        if (client.getTelephone() != null) {
            existant.setTelephone(client.getTelephone());
        }
        if (client.getNumPermis() != null) {
            existant.setNumPermis(client.getNumPermis());
        }
        if (client.getDateInscription() != null) {
            existant.setDateInscription(client.getDateInscription());
        }
        return clientRepository.save(existant);
    }

    @Override
    @Transactional(readOnly = true)
    public Client consulterClient(Long id) {
        return trouver(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Client> consulterTousLesClients() {
        return clientRepository.findAll();
    }

    @Override
    @Transactional
    public void supprimerClientById(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new EntityNotFoundException("Client introuvable avec l'id " + id);
        }
        clientRepository.deleteById(id);
    }

    private Client trouver(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client introuvable avec l'id " + id));
    }
}
