package com.clinicaodontologica.Clinica.Odontologica.dao;

import com.clinicaodontologica.Clinica.Odontologica.model.Odontologo;

import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOARRAYLIST implements iDao<Odontologo> {
    private List<Odontologo> listaOdontologos = new ArrayList();



    @Override
    public Odontologo guardar(Odontologo odontologo) {
        listaOdontologos.add(odontologo);
        return odontologo;
    }

    @Override
    public Odontologo buscar(Integer id) {
        return null;
    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public void actualizar(Odontologo odontologo) {

    }

    @Override
    public List<Odontologo> buscarTodos() {
        return listaOdontologos;
    }

    @Override
    public Odontologo buscarPorString(String valor) {
        return null;
    }
}
