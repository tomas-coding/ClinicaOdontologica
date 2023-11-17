package com.clinicaodontologica.Clinica.Odontologica.controller;
import com.clinicaodontologica.Clinica.Odontologica.model.Odontologo;
import com.clinicaodontologica.Clinica.Odontologica.model.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

      @DeleteMapping("/eliminar/{id}")
      public ResponseEntity eliminarOdontologo(@PathVariable Integer id)
      {
            if( odontologoService.buscarPorId(id) == null ){
                  return ResponseEntity.badRequest().body("No se ha encontrado el odontologo");
            } else {
                  odontologoService.eliminarPorId(id);
                  return ResponseEntity.ok("Eliminado!");
            }
      }

      @PutMapping ("/actualizar")
      public ResponseEntity actualizarOdontologo(@RequestBody Odontologo odontologo)
      {
            Odontologo odontologoBuscado= odontologoService.buscarPorId(odontologo.getId());
            ResponseEntity response = null;

            if(odontologoBuscado == null) {
                  response = new ResponseEntity<>(HttpStatus.NOT_FOUND);      // este objeto "ResponseEntity" no contendra un Body
            }else{
                  odontologoService.actualizarOdontologo(odontologo);
                  response = new ResponseEntity( odontologo, HttpStatus.OK ); 
            }

            return response;
      }

      @GetMapping("/buscar/atributo")
      public Odontologo buscarPorString( @RequestParam String str )
      {
            return odontologoService.buscarPorString(str);
      }


}
