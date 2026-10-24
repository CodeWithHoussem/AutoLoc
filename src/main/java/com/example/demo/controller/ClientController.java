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

import com.example.demo.domain.Client;
import com.example.demo.service.ClientService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("client")
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public Client ajouterClient(@RequestBody Client client) {
        return clientService.ajouterClient(client);
    }

    @PutMapping("/modifierClient/{id}")
    public Client modifierClient(@PathVariable Long id, @RequestBody Client client) {
        return clientService.modifierClient(id, client);
    }

    @GetMapping("/getById/{id}")
    public Client getClientById(@PathVariable Long id) {
        return clientService.consulterClient(id);
    }

    @GetMapping("/getAll")
    public List<Client> getAllClients() {
        return clientService.consulterTousLesClients();
    }

    @DeleteMapping("/supprimerClient/{id}")
    public void supprimerClientById(@PathVariable Long id) {
        clientService.supprimerClientById(id);
    }
}
