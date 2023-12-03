package com.clinicaodontologica.Clinica.Odontologica.testsIntegracion;

import com.clinicaodontologica.Clinica.Odontologica.model.Domicilio;
import com.clinicaodontologica.Clinica.Odontologica.model.Odontologo;
import com.clinicaodontologica.Clinica.Odontologica.model.Paciente;
import com.clinicaodontologica.Clinica.Odontologica.model.Turno;
import com.clinicaodontologica.Clinica.Odontologica.service.OdontologoService;
import com.clinicaodontologica.Clinica.Odontologica.service.PacienteService;
import com.clinicaodontologica.Clinica.Odontologica.service.TurnoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false) //nececito desactivar la seguridad en la clase
public class IntegracionTurnosTest {


    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private MockMvc mockMvc;

    public void agregarDatosIniciales(){
        Paciente paciente= new Paciente("Jorgito","Pereyra","11111", LocalDate.of(2023,11,28),new Domicilio("Calle ",1,"La Rioja","La Rioja"),"jorge.pereyra@digitalhouse.com");
        Odontologo odontologo= new Odontologo("123","Maria de Los Angeles","Magaz");
        Turno turno= new Turno(paciente,odontologo,LocalDate.of(2023,12,01));
        pacienteService.registrarPaciente(paciente);
        odontologoService.registrarOdontologo(odontologo);
        turnoService.guardarTurno(turno);
    }
    @Test
    public void listarTurnosTest() throws Exception{
        agregarDatosIniciales();
        MvcResult respuesta= mockMvc.perform(MockMvcRequestBuilders.get("/turnos").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }


}
