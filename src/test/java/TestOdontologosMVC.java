import com.clinicaodontologica.Clinica.Odontologica.model.Odontologo;
import com.clinicaodontologica.Clinica.Odontologica.service.OdontologoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class TestOdontologosMVC {
      @Autowired
      private MockMvc mockMvc;
      @Autowired
      private ObjectMapper objectMapper;


      @Test
      private void testearAltaOdontologo() throws Exception{
            Odontologo o = new Odontologo();
            o.setMatricula("Matricula");
            o.setNombre("Tomas");
            o.setApellido("Lovato");


            String odontologoJSON = objectMapper.writeValueAsString(o);

            MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.post("/odontologo")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(odontologoJSON))
                        .andDo(print()).andExpect(status().isCreated())
                        .andReturn();

      }



}
