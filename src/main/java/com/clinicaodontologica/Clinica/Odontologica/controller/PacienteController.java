package com.clinicaodontologica.Clinica.Odontologica.controller;

import com.clinicaodontologica.Clinica.Odontologica.dao.PacienteDAOH2;
import com.clinicaodontologica.Clinica.Odontologica.model.Paciente;
import com.clinicaodontologica.Clinica.Odontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
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
    @PostMapping
    public  Paciente registrarPaciente(@RequestBody Paciente paciente){
        return pacienteService.guardarPaciente(paciente);
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
    @GetMapping
    public String buscarPacientePorCorreo(Model model, @RequestParam("email") String correo){
        Paciente paciente= pacienteService.buscarPorEmail(correo);
        model.addAttribute("nombre",paciente.getNombre());
        model.addAttribute("apellido",paciente.getApellido());
        return "index";
    }
}
