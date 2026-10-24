package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Equipement;

@Repository
public interface EquipementRepository extends JpaRepository<Equipement, Long> {

}
