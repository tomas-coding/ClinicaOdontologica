package com.clinicaodontologica.Clinica.Odontologica.service;

import com.clinicaodontologica.Clinica.Odontologica.dao.PacienteDAOH2;
import com.clinicaodontologica.Clinica.Odontologica.dao.iDao;
import com.clinicaodontologica.Clinica.Odontologica.model.Paciente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {
    private iDao<Paciente> pacienteiDao;



    public PacienteService() {
        pacienteiDao= new PacienteDAOH2();
    }
    /* public PacienteService(iDao<Paciente> pacienteiDao) {
        this.pacienteiDao = pacienteiDao;
    }*/

    //se generan manuales los metodos
    public Paciente guardarPaciente(Paciente paciente){
        return pacienteiDao.guardar(paciente);
    }
    public Paciente buscarPorId(Integer id){
        return pacienteiDao.buscar(id);
    }
    public void eliminarPorId(Integer id){
        pacienteiDao.eliminar(id);
    }
    public void actualizarPaciente(Paciente paciente){
        pacienteiDao.actualizar(paciente);
    }
    public List<Paciente> obtenerTodosLosPacientes(){
        return pacienteiDao.buscarTodos();
    }
    public Paciente buscarPorEmail(String correo){
        return pacienteiDao.buscarPorString(correo);
    }


}
