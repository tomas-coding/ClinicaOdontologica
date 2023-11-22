package com.clinicaodontologica.Clinica.Odontologica.service;
import com.clinicaodontologica.Clinica.Odontologica.model.Paciente;
import com.clinicaodontologica.Clinica.Odontologica.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente registrarPaciente(Paciente paciente){
        return  pacienteRepository.save(paciente);
    }
    public void actualizarPaciente(Paciente paciente){
        pacienteRepository.save(paciente);
    }
    public void eliminarPaciente(Long id){
        pacienteRepository.deleteById(id);
    }
    public Optional<Paciente> buscarPacientePorID(Long id){
        return pacienteRepository.findById(id);
    }
    public Optional<Paciente> buscarPorCorreo(String email){
        return pacienteRepository.findByEmail(email);
    }
    public List<Paciente> listarTodos(){
        return pacienteRepository.findAll();
    }
}
