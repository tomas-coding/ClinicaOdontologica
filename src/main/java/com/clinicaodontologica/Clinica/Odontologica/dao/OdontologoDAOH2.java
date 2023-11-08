package com.clinicaodontologica.Clinica.Odontologica.dao;

import com.clinicaodontologica.Clinica.Odontologica.model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOH2 implements iDao<Odontologo>{
    private static final Logger logger= Logger.getLogger(OdontologoDAOH2.class);
    private static final String SQL_INSERT="INSERT INTO ODONTOLOGOS (MATRICULA, NOMBRE, APELLIDO) VALUES (?,?,?)";
    @Override
    public Odontologo guardar(Odontologo odontologo) {
        logger.info("guardando odontologo");
        Connection connection= null;
        try{
            connection= BD.getConnection(); //obtengo la conexion

            PreparedStatement psInsert= connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1, odontologo.getMatricula());
            psInsert.setString(2, odontologo.getNombre());
            psInsert.setString(3, odontologo.getApellido());
            psInsert.execute(); //en teoria si hay claves se generan
            ResultSet clave= psInsert.getGeneratedKeys();
            while (clave.next()){
                odontologo.setId(clave.getInt(1));
            }
            logger.info(" odontologo guardado");


        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            try{
                connection.close();
            }catch (SQLException ex){
                logger.error(ex.getMessage());
            }
        }
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
        logger.info("iniciando operacion de listar los odontologos");
        Connection connection = null;
        Odontologo odontologo = null;
        List<Odontologo> listaOdontologos = new ArrayList();
        try {
            connection= BD.getConnection();
            PreparedStatement psQuery = connection.prepareStatement("SELECT * FROM ODONTOLOGOS");
            ResultSet result = psQuery.executeQuery();
            while (result.next()){
                odontologo = new Odontologo();
                odontologo.setId(result.getInt("ID"));
                odontologo.setMatricula(result.getString("MATRICULA"));
                odontologo.setNombre(result.getString("NOMBRE"));
                odontologo.setApellido(result.getString("APELLIDO"));
                listaOdontologos.add(odontologo);
            }
            logger.info("se han listado correctamente los datos");

        } catch (Exception e){
            logger.warn(e.getMessage());
        }
        finally {
            try {
                connection.close();
                logger.info("conexion cerrada con exito");
            } catch (SQLException ex){
                logger.warn(ex.getMessage());
            }
        }
        return listaOdontologos;

    }
}
