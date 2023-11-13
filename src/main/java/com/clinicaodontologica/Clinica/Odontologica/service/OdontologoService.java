package com.clinicaodontologica.Clinica.Odontologica.service;

import com.clinicaodontologica.Clinica.Odontologica.dao.OdontologoDAOH2;
import com.clinicaodontologica.Clinica.Odontologica.dao.iDao;
import com.clinicaodontologica.Clinica.Odontologica.model.Odontologo;
import com.clinicaodontologica.Clinica.Odontologica.dao.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService {
      private iDao<Odontologo> odontologoiDao;
      private static final OdontologoDAOH2 odontologoiDaoH2 = new OdontologoDAOH2();
      private static final OdontologoDAOARRAYLIST odontologoiDaoArrayList = new OdontologoDAOARRAYLIST();

      public OdontologoService()
      {
             this.odontologoiDao = new OdontologoDAOH2();
      }

      public void setEstrategiaDePersistencia(String clave)
      {
            switch( clave ){
                  case "h2":
                        this.odontologoiDao = odontologoiDaoH2;
                        break;
                  case "memory":
                        this.odontologoiDao = odontologoiDaoArrayList;
                        break;
            }
      }

      public Odontologo guardarOdontologo(Odontologo odontologo)
      {
            return odontologoiDao.guardar(odontologo);
      }
      public List<Odontologo> listarOdontologos()
      {
            return odontologoiDao.buscarTodos();
      }

      public Odontologo buscarPorId(Integer id)
      {
            return odontologoiDao.buscar(id);
      }
      public void eliminarPorId(Integer id) {
            odontologoiDao.eliminar(id);
      }
      public void actualizarOdontologo (Odontologo odontologo){odontologoiDao.actualizar(odontologo);}
}
