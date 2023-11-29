package com.clinicaodontologica.Clinica.Odontologica.controller;


import com.clinicaodontologica.Clinica.Odontologica.dto.TurnoDTO;
import com.clinicaodontologica.Clinica.Odontologica.model.Odontologo;
import com.clinicaodontologica.Clinica.Odontologica.model.Paciente;
import com.clinicaodontologica.Clinica.Odontologica.model.Turno;
import com.clinicaodontologica.Clinica.Odontologica.service.OdontologoService;
import com.clinicaodontologica.Clinica.Odontologica.service.PacienteService;
import com.clinicaodontologica.Clinica.Odontologica.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<TurnoDTO> registrarTurno(@RequestBody TurnoDTO turno){

        Optional<Paciente> pacienteBuscado= pacienteService.buscarPacientePorID(turno.getPacienteId());
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarPorId(turno.getOdontologoId());


        if(pacienteBuscado.isPresent()&&odontologoBuscado.isPresent()){
            Turno t = new Turno();
            t.setPaciente(pacienteBuscado.get());
            t.setOdontologo(odontologoBuscado.get());
            t.setFechaTurno(turno.getFechaTurno());
            return ResponseEntity.ok(turnoService.guardarTurno(t));
        }
        else {
            // Preguntar al profe si hay alguna manera de que una ResponseEntity<TurnoDTO> pueda arrojar el body en null o un error diciendo que esta fallido
            ResponseEntity<TurnoDTO> f = new ResponseEntity<>(new TurnoDTO(), HttpStatus.NOT_FOUND);
            return f;
        }
    }

    // @PostMapping
    // public ResponseEntity<TurnoDTO> registrarTurno(@RequestBody Turno turno){
    //
    //     Optional<Paciente> pacienteBuscado= pacienteService.buscarPacientePorID(turno.getPaciente().getId());
    //     Optional<Odontologo> odontologoBuscado= odontologoService.buscarPorId(turno.getOdontologo().getId());
    //     if(pacienteBuscado.isPresent()&&odontologoBuscado.isPresent()){
    //         return ResponseEntity.ok(turnoService.guardarTurno(turno));
    //     }
    //     else {
    //         return ResponseEntity.badRequest().build();
    //     }
    // }

    @GetMapping
    public ResponseEntity<List<TurnoDTO>> buscarTodos(){
        return ResponseEntity.ok(turnoService.buscarTodos());
    }
    @GetMapping("{id}")
    public ResponseEntity<TurnoDTO> buscarPorId(@PathVariable Long id){
        Optional<TurnoDTO> turnoDtoBuscado= turnoService.buscarTurnoPorId(id);
        if(turnoDtoBuscado.isPresent()){
            return ResponseEntity.ok(turnoDtoBuscado.get());
        }
        else{
            return ResponseEntity.notFound().build(); //404
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> borrarTurno(@PathVariable Long id){
        Optional<TurnoDTO> turnoDtoBuscado= turnoService.buscarTurnoPorId(id);
        if(turnoDtoBuscado.isPresent()){
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("Eliminado con exito");
        }
        else{
            //return ResponseEntity.badRequest().body("Hay inconsistencia con la consulta para eliminar."); //404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo encontrar el turno para su eliminacion: "+id);
        }
    }
    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody TurnoDTO turnoDTO ){
        Optional<TurnoDTO> turnoDTObuscado= turnoService.buscarTurnoPorId(turnoDTO.getId());
        if(turnoDTObuscado.isPresent()){
            Optional<Paciente> pacienteBuscado= pacienteService.buscarPacientePorID(turnoDTO.getPacienteId());
            Optional<Odontologo> odontologoBuscado= odontologoService.buscarPorId(turnoDTO.getOdontologoId());
            if(pacienteBuscado.isPresent()&& odontologoBuscado.isPresent()){
                turnoService.actualizarTurno(turnoDTO);
                return ResponseEntity.ok("El turno se actualizo con éxito");
            }else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encontro paciente y/o odontologo ");
            }
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
