package com.clinicaodontologica.Clinica.Odontologica.repository;

import com.clinicaodontologica.Clinica.Odontologica.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository  extends JpaRepository<Paciente,Long> {


    Optional<Paciente> findByEmail(String email);
}
