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

import com.example.demo.domain.Employe;
import com.example.demo.service.EmployeService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("employe")
public class EmployeController {

    private final EmployeService employeService;

    @PostMapping
    public Employe ajouterEmploye(@RequestBody Employe employe) {
        return employeService.ajouterEmploye(employe);
    }

    @PutMapping("/modifierEmploye/{id}")
    public Employe modifierEmploye(@PathVariable Long id, @RequestBody Employe employe) {
        return employeService.modifierEmploye(id, employe);
    }

    @GetMapping("/getById/{id}")
    public Employe getEmployeById(@PathVariable Long id) {
        return employeService.consulterEmploye(id);
    }

    @GetMapping("/getAll")
    public List<Employe> getAllEmployes() {
        return employeService.consulterTousLesEmployes();
    }

    @DeleteMapping("/supprimerEmploye/{id}")
    public void supprimerEmployeById(@PathVariable Long id) {
        employeService.supprimerEmployeById(id);
    }
}
