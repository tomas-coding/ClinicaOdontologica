package com.clinicaodontologica.Clinica.Odontologica.service;

import com.clinicaodontologica.Clinica.Odontologica.dao.OdontologoDAOH2;
import com.clinicaodontologica.Clinica.Odontologica.dao.iDao;
import com.clinicaodontologica.Clinica.Odontologica.model.Odontologo;

import java.util.List;

public class OdontologoService {
    private iDao<Odontologo> odontologoiDao;

    public OdontologoService(iDao<Odontologo> pacienteiDao) {
        this.odontologoiDao = new OdontologoDAOH2();
    }

    public Odontologo guardarOdontologo(Odontologo odontologo){
        return odontologoiDao.guardar(odontologo);
    }
    public List<Odontologo> listarOdontologos(){
        return odontologoiDao.buscarTodos();
    }
}
