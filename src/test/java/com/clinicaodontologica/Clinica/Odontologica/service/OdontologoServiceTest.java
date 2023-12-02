package com.clinicaodontologica.Clinica.Odontologica.service;

import com.clinicaodontologica.Clinica.Odontologica.model.Domicilio;
import com.clinicaodontologica.Clinica.Odontologica.model.Odontologo;
import com.clinicaodontologica.Clinica.Odontologica.model.Paciente;
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
public class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    public void guardarOdontologo(){
        //que necesitamos para guardar un odontologo
        Odontologo odontologo = new Odontologo ("matricula 1","Tomas","Lovato");
        odontologoService.registrarOdontologo(odontologo);
        assertEquals(1L,odontologo.getId());
    }
    @Test
    @Order(2)
    public void buscarOdontologoPorId(){
        Long id=1L;
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarPorId(id);
        assertNotNull(odontologoBuscado);
    }
    @Test
    @Order(3)
    public void buscarOdontologosTest(){
        List<Odontologo> odontologos= odontologoService.listarTodos();
        assertEquals(1,odontologos.size());
    }
    @Test
    @Order(4)
    public void actualizarOdontologo(){
        Odontologo odontologoActualizar= new Odontologo("Matricula 1att","Javier","Pignataro");
        odontologoService.actualizarOdontologo(odontologoActualizar);
        Optional<Odontologo> odontologoBuscado= odontologoService.buscarPorId(1L);
        assertEquals("Javier",odontologoBuscado.get().getNombre());
    }
    @Test
    @Order(5)
    public void eliminarPaciente(){
        Long idEliminar= 1L;
        odontologoService.eliminarOdontologo(1L);
        Optional<Odontologo> odontologoEliminado= odontologoService.buscarPorId(1L);
        assertFalse(odontologoEliminado.isPresent());

    }
}
