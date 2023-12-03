package com.clinicaodontologica.Clinica.Odontologica.testsIntegracion;

import com.clinicaodontologica.Clinica.Odontologica.model.Odontologo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class TestOdontologosMVC {
      @Autowired
      private MockMvc mockMvc;
      @Autowired
      private ObjectMapper objectMapper;


      @Test
      void testearAltaOdontologo() throws Exception{
            Odontologo o = new Odontologo();
            o.setMatricula("Matricula");
            o.setNombre("Tomas");
            o.setApellido("Lovato");


            String odontologoJSON = objectMapper.writeValueAsString(o);

            MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.post("/odontologo")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(odontologoJSON))
                        .andDo(print()).andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();
            o.setId(1L);
            String odontologoTESTJSON = objectMapper.writeValueAsString(o);

            // System.out.println( "ObjectMapper: " + odontologoTESTJSON );
            // System.out.println( "Response: " + respuesta.getResponse().getContentAsString() );

            assertEquals( odontologoTESTJSON, respuesta.getResponse().getContentAsString() );
      }



}
