package com.clinicaodontologica.Clinica.Odontologica.controller;


import com.clinicaodontologica.Clinica.Odontologica.dto.TurnoDTO;
import com.clinicaodontologica.Clinica.Odontologica.model.Odontologo;
import com.clinicaodontologica.Clinica.Odontologica.model.Paciente;
import com.clinicaodontologica.Clinica.Odontologica.model.Turno;
import com.clinicaodontologica.Clinica.Odontologica.service.OdontologoService;
import com.clinicaodontologica.Clinica.Odontologica.service.PacienteService;
import com.clinicaodontologica.Clinica.Odontologica.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private TurnoService turnoService;
    private PacienteService pacienteService;
    private OdontologoService odontologoService;

    @Autowired
    public TurnoController(TurnoService turnoService, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoService = turnoService;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }
    @PostMapping
    public ResponseEntity<TurnoDTO> registrarTurno(@RequestBody Turno turno){

        Optional<Paciente> pacienteBuscado= pacienteService.buscarPacientePorID(turno.getPaciente().getId());
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarPorId(turno.getOdontologo().getId());
        if(pacienteBuscado.isPresent()&&odontologoBuscado.isPresent()){
            return ResponseEntity.ok(turnoService.guardarTurno(turno));
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }
}
