package com.example.demo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Contrat;
import com.example.demo.service.ContratService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("contrat")
public class ContratController {
private final ContratService contratService;
@PostMapping 
public Contrat ajouterContrat(@RequestBody Contrat contrat) {
	return contratService.ajouterContrat(contrat);
}
@PutMapping("/modofierContrat/{id}")
public Contrat modifierContrat(@PathVariable Long id, @RequestBody Contrat contrat) {
    return contratService.modifierContrat(id, contrat);
}
@GetMapping("getById/{id}")
public Contrat getContratById(@PathVariable Long id) {
    return contratService.consulterContrat(id);
}
@DeleteMapping("/SUPPRIMERcONTRAT/{id}")
public void supprimerContratById(@PathVariable Long id) {
    contratService.supprimerContratById(id);
}
}
