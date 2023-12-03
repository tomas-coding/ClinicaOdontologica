package com.clinicaodontologica.Clinica.Odontologica.controller;


import com.clinicaodontologica.Clinica.Odontologica.dto.TurnoDTO;
import com.clinicaodontologica.Clinica.Odontologica.exception.ResorceNotFoundException;
import com.clinicaodontologica.Clinica.Odontologica.model.Odontologo;
import com.clinicaodontologica.Clinica.Odontologica.model.Paciente;
import com.clinicaodontologica.Clinica.Odontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;


    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPacientePorID(@PathVariable Long id) throws ResorceNotFoundException {

        Optional<Paciente> pacienteBuscado= pacienteService.buscarPacientePorID(id);
        if(pacienteBuscado.isPresent()){
            return ResponseEntity.ok(pacienteBuscado.get());
        }
        else{
            throw new ResorceNotFoundException("No se pudo encontrar el paciente, debido a que no existe");
        }

    }
    @PostMapping
    public  ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente){
        return ResponseEntity.ok(pacienteService.registrarPaciente(paciente));
    }
    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente) throws ResorceNotFoundException {
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPacientePorID(paciente.getId());
        if(pacienteBuscado.isPresent()) {
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("paciente actualizado");
        }else{
            throw new ResorceNotFoundException("No se pudo acutalizar el paciente, debido a que no existe");
        }
    }
    @GetMapping("/buscar/{email}")
    public ResponseEntity<Paciente> buscarPacientePorCorreo(@PathVariable String correo) throws ResorceNotFoundException {
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPorCorreo(correo);
        if(pacienteBuscado.isPresent()) {
            return ResponseEntity.ok(pacienteBuscado.get());
        }else{
            throw new ResorceNotFoundException("No se pudo encontrar el paciente por el correo, debido a que no existe");
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> elimninarPaciente(@PathVariable Long id) throws ResorceNotFoundException {
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPacientePorID(id);
        if(pacienteBuscado.isPresent()){
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok("Eliminado con exito");
        }else{
            throw new ResorceNotFoundException("No se pudo eliminar el paciente, debido a que no existe");
        }

    }

    @GetMapping("/todos")
    public ResponseEntity<List<Paciente>> buscarTodos(){
        return ResponseEntity.ok(pacienteService.listarTodos());
    }
}
