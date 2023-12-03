package com.clinicaodontologica.Clinica.Odontologica.service;


import com.clinicaodontologica.Clinica.Odontologica.dto.TurnoDTO;
import com.clinicaodontologica.Clinica.Odontologica.model.Domicilio;
import com.clinicaodontologica.Clinica.Odontologica.model.Odontologo;
import com.clinicaodontologica.Clinica.Odontologica.model.Paciente;
import com.clinicaodontologica.Clinica.Odontologica.model.Turno;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class TurnoServiceTest {
      @Autowired
      private TurnoService turnoService;
      @Autowired
      private PacienteService pacienteService;
      @Autowired
      private OdontologoService odontologoService;

      @Test
      @Order(1)
      public void guardarTurno(){
            //que necesitamos para guardar un turno
            Paciente p = new Paciente("Jorgito","Pereyra","11111", LocalDate.of(2023,11,28),new Domicilio("Calle ",1,"La Rioja","La Rioja"),"jorge.pereyra@digitalhouse.com");
            Odontologo o = new Odontologo("F100", "Tomas", "Lovato");
            pacienteService.registrarPaciente(p);
            odontologoService.registrarOdontologo(o);
            Turno turno = new Turno( p, o, LocalDate.now() );
            TurnoDTO retorno = turnoService.guardarTurno(turno);
            assertEquals(1L,retorno.getId());
      }
      @Test
      @Order(2)
      public void buscarTurnoPorId(){
            Long id=1L;
            Optional<TurnoDTO> turnoBuscado= turnoService.buscarTurnoPorId(id);
            assertNotNull(turnoBuscado);
      }
      @Test
      @Order(3)
      public void buscarTurnosTest(){
            List<TurnoDTO> turnos = turnoService.buscarTodos();
            assertEquals(1,turnos.size());
      }
      @Test
      @Order(4)
      public void actualizarTurno(){
            TurnoDTO turnoActualizar = new TurnoDTO( 1L, LocalDate.of(2024, 11, 2), 1L, 1L );
            turnoService.actualizarTurno(turnoActualizar);
            Optional<TurnoDTO> turnoBuscado= turnoService.buscarTurnoPorId(1L);
            assertEquals( LocalDate.of(2024, 11, 2), turnoBuscado.get().getFechaTurno() );
      }
      @Test
      @Order(5)
      public void eliminarTurno(){
            Long idEliminar= 1L;
            turnoService.eliminarTurno(1L);
            Optional<TurnoDTO> turnoEliminado= turnoService.buscarTurnoPorId(idEliminar);
            assertFalse(turnoEliminado.isPresent());
      }
}
