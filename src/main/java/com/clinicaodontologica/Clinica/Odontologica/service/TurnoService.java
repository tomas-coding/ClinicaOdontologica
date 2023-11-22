package com.clinicaodontologica.Clinica.Odontologica.service;


import com.clinicaodontologica.Clinica.Odontologica.dto.TurnoDTO;
import com.clinicaodontologica.Clinica.Odontologica.model.Turno;
import com.clinicaodontologica.Clinica.Odontologica.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TurnoService {
    @Autowired
    private TurnoRepository turnoRepository;

    public TurnoDTO guardarTurno(Turno turno){
        Turno turnoGuardado= turnoRepository.save(turno);
        return turnoATurnoDTO(turnoGuardado);
    }
    public void eliminarTurno(Long id){
        turnoRepository.deleteById(id);
    }
    public void actualizarTurno(Turno turno){
        turnoRepository.save(turno);
    }
    public Optional<Turno> buscarTurnoPorId(Long id){
         return turnoRepository.findById(id);
    }

    private TurnoDTO turnoATurnoDTO(Turno turno){
        TurnoDTO respuesta= new TurnoDTO();
        respuesta.setId(turno.getId());
        respuesta.setPacienteId(turno.getPaciente().getId());
        respuesta.setOdontologoId(turno.getOdontologo().getId());
        respuesta.setFechaTurno(turno.getFechaTurno());
        return  respuesta;
    }
}
