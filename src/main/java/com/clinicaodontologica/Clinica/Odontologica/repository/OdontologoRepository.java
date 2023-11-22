package com.clinicaodontologica.Clinica.Odontologica.repository;

import com.clinicaodontologica.Clinica.Odontologica.model.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OdontologoRepository extends JpaRepository<Odontologo,Long> {
    Optional<Odontologo> findByMatricula(String matricula);
}
