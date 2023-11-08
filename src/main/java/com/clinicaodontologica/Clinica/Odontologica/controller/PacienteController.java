package com.clinicaodontologica.Clinica.Odontologica.controller;

import com.clinicaodontologica.Clinica.Odontologica.dao.PacienteDAOH2;
import com.clinicaodontologica.Clinica.Odontologica.model.Paciente;
import com.clinicaodontologica.Clinica.Odontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    private PacienteService pacienteService;
    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }
    @GetMapping("/{id}")
    public Paciente buscarPacientePorID(@PathVariable Integer id){
        return pacienteService.buscarPorId(id);
    }
    @PutMapping
    public String actualizarPaciente(@RequestBody Paciente paciente){
        Paciente pacienteBuscado= pacienteService.buscarPorId(paciente.getId());
        if(pacienteBuscado!=null) {
            pacienteService.actualizarPaciente(paciente);
            return "paciente actualizado";
        }else{
            return "paciente no encontrado";
        }
    }
    @DeleteMapping ("/delete/{id}")
    public void borrarPaciente(@PathVariable Integer id){
        pacienteService.eliminarPorId(id);
    }
}
