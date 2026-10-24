package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Client;

public interface ClientService {

    Client ajouterClient(Client client);

    Client modifierClient(Long id, Client client);

    Client consulterClient(Long id);

    List<Client> consulterTousLesClients();

    void supprimerClientById(Long id);
}
