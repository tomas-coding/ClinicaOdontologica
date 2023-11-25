package com.clinicaodontologica.Clinica.Odontologica.controller;
import com.clinicaodontologica.Clinica.Odontologica.model.Odontologo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.clinicaodontologica.Clinica.Odontologica.service.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {
      //relacion de asociacion con el servicio
      @Autowired
      private OdontologoService odontologoService;


      @PostMapping
      public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo){
            return ResponseEntity.ok(odontologoService.registrarOdontologo(odontologo));
      }
      @GetMapping("/todos")
      public ResponseEntity<List<Odontologo>> buscarTodos(){
            return ResponseEntity.ok(odontologoService.listarTodos());
      }
      @PutMapping
      public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo){
            Optional<Odontologo> odontologoBuscado= odontologoService.buscarPorId(odontologo.getId());
            if(odontologoBuscado.isPresent()){
                  odontologoService.actualizarOdontologo(odontologo);
                  return ResponseEntity.ok("Odontologo actualizado");
            }
            else{
                  return ResponseEntity.badRequest().body("Odontologo no encontrado");
            }
      }
      @GetMapping("/{id}")
      public ResponseEntity<Optional<Odontologo>> buscarPorID(@PathVariable Long id){

            return ResponseEntity.ok(odontologoService.buscarPorId(id));
      }
      @GetMapping("/busqueda/{matricula}")
      public ResponseEntity<Optional<Odontologo>> buscarPorMatricula(@PathVariable String matricula){
            return ResponseEntity.ok(odontologoService.buscarPorMatricula(matricula));
      }

      @DeleteMapping("{id}")
      public ResponseEntity<String> eliminarPorId (@PathVariable Long id){
            Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(id);
            if (odontologoBuscado.isPresent()){
                  odontologoService.eliminarOdontologo(id);
                  return ResponseEntity.ok("Odontologo eliminado con exito");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Odontologo no encontrado");
      }
}
