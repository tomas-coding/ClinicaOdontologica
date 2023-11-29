package com.clinicaodontologica.Clinica.Odontologica.repository;

import com.clinicaodontologica.Clinica.Odontologica.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

Optional<Usuario> findByEmail(String correo);
}
