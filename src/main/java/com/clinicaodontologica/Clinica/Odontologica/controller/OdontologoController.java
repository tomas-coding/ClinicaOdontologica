package com.clinicaodontologica.Clinica.Odontologica.controller;
import com.clinicaodontologica.Clinica.Odontologica.model.Odontologo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.clinicaodontologica.Clinica.Odontologica.service.*;
import java.util.List;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {
      private OdontologoService odontologoService;

      @Autowired
      public OdontologoController()
      {
            this.odontologoService = new OdontologoService();
      }

      // public Odontologo buscarOdontologoPorId(@PathVariable Integer id)
      @GetMapping("/buscar/{id}")
      public Odontologo buscarOdontologoPorId(@PathVariable Integer id)
      {
            return odontologoService.buscarPorId(id);
      }

      @GetMapping("/listar")
      public List<Odontologo> listarTodos()
      {
            return odontologoService.listarOdontologos();
      }

      // Metodo de Testeo para establecer el tipo de implementacion 
      // de la persistencia (H2: SQL // ArrayList: En Memoria)
      // Recibo por parametro (url) un string.
      // Este debe tener uno de los siguientes valores:
      //    1. "h2"
      //    2. "memory"
      // *** Preguntar si esta bien pasar por parametro de tipo "path" en metodos POST * * * * *
      @PostMapping("/persistencia/{clave}")
      public void registrarOdontologo(@PathVariable String clave)
      {
            odontologoService.setEstrategiaDePersistencia(clave);
      }

      @PostMapping("/guardar")
      public Odontologo registrarOdontologo(@RequestBody Odontologo odontologo)
      {
            return odontologoService.guardarOdontologo(odontologo);
      }


}
