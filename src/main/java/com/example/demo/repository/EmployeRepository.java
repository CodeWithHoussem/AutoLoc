package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Employe;
import com.example.demo.domain.RoleEmploye;

@Repository
public interface EmployeRepository extends JpaRepository<Employe, Long> {

    List<Employe> findByAgence_IdAgence(Long idAgence);

    List<Employe> findByRole(RoleEmploye role);
}
