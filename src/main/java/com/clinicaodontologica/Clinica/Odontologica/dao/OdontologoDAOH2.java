package com.clinicaodontologica.Clinica.Odontologica.dao;

import com.clinicaodontologica.Clinica.Odontologica.model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOH2 implements iDao<Odontologo>
{
      private static final Logger logger= Logger.getLogger(OdontologoDAOH2.class);
      private static final String SQL_INSERT="INSERT INTO ODONTOLOGOS (MATRICULA, NOMBRE, APELLIDO) VALUES (?,?,?)";
      private static final String SQL_SELECT_BY="SELECT * FROM ODONTOLOGOS WHERE ID=?";
      private static final String SQL_DELETE_BY_ID="DELETE FROM ODONTOLOGOS WHERE ID=?";
      private static final String SQL_UPDATE="UPDATE ODONTOLOGOS SET MATRICULA=?, NOMBRE=?, APELLIDO=? WHERE ID =?";
      private static final String SQL_SELECT_ALL="SELECT * FROM ODONTOLOGOS";
      private static final String SQL_SELECT_BY_STRING="SELECT * FROM ODONTOLOGOS WHERE MATRICULA=? OR NOMBRE=? OR APELLIDO=?";

      @Override
      public Odontologo guardar(Odontologo odontologo) 
      {
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
      public Odontologo buscar(Integer id) 
      {
            logger.info("iniciando la operacion de buscado por id "+id);
            Connection connection = null;
            Odontologo odontologo = null;
            try{
                  connection= BD.getConnection();
                  PreparedStatement psSelectOne= connection.prepareStatement(SQL_SELECT_BY);
                  psSelectOne.setInt(1,id);
                  ResultSet rs= psSelectOne.executeQuery();
                  while (rs.next()){
                        odontologo = new Odontologo(
                                    rs.getInt(1),
                                    rs.getString(2),
                                    rs.getString(3),
                                    rs.getString(4)
                        );
                  }
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
      public void eliminar(Integer id) 
      {
            logger.info("iniciando las operaciones de : eliminar por id "+id);
            Connection connection= null;
            try{
                  connection= BD.getConnection();
                  PreparedStatement psDeleteOne= connection.prepareStatement(SQL_DELETE_BY_ID);
                  psDeleteOne.setInt(1,id);
                  psDeleteOne.execute();
                  logger.info("Odontologo con Id: " + id + " eliminado con exito");
            }catch (Exception e){
                  logger.error(e.getMessage());
            }finally {
                  try{
                        connection.close();
                  }catch (SQLException ex){
                        logger.error(ex.getMessage());
                  }
            }
      }

      @Override
      public void actualizar(Odontologo odontologo) 
      {
            logger.info("iniciando la operacion de actualizado de un paciente ");
            Connection connection= null;
            try{
                  connection= BD.getConnection();
                  PreparedStatement ps_Update= connection.prepareStatement(SQL_UPDATE);
                  //van las parametrizadas
                  ps_Update.setString(1, odontologo.getMatricula());
                  ps_Update.setString(2, odontologo.getNombre());
                  ps_Update.setString(3, odontologo.getApellido());
                  ps_Update.setInt(4, odontologo.getId());
                  ps_Update.execute();
            }catch (Exception e){
                  logger.error(e.getMessage());
            }finally {
                  try{
                        connection.close();
                  }catch (SQLException ex){
                        logger.error(ex.getMessage());
                  }
            }
      }



      @Override
      public List<Odontologo> buscarTodos()
      {
            logger.info("iniciando operacion de listar los odontologos");
            Connection connection = null;
            Odontologo odontologo = null;
            List<Odontologo> listaOdontologos = new ArrayList<Odontologo>();
            try {
                  connection= BD.getConnection();
                  PreparedStatement psQuery = connection.prepareStatement(SQL_SELECT_ALL);
                  ResultSet result = psQuery.executeQuery();
                  while (result.next()){
                        odontologo = new Odontologo();
                        odontologo.setId(result.getInt("ID"));
                        odontologo.setMatricula(result.getString("MATRICULA"));
                        odontologo.setNombre(result.getString("NOMBRE"));
                        odontologo.setApellido(result.getString("APELLIDO"));
                        listaOdontologos.add(odontologo);
                  }
                  logger.info("se han enlistado correctamente los datos");

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

      @Override
      public Odontologo buscarPorString(String valor)
      {
            logger.info("iniciando la operacion buscado por string: "+valor);
            Connection connection = null;
            Odontologo odontologo = null;
            try{
                  connection= BD.getConnection();
                  PreparedStatement psSelectOne= connection.prepareStatement(SQL_SELECT_BY_STRING);
                  psSelectOne.setString(1,valor);
                  psSelectOne.setString(2,valor);
                  psSelectOne.setString(3,valor);
                  ResultSet rs= psSelectOne.executeQuery();

                  while (rs.next()){
                        // domicilio= daoAux.buscar(rs.getInt(6));
                        // paciente= new Paciente(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5).toLocalDate(),domicilio,rs.getString(7));
                        odontologo = new Odontologo(
                                    rs.getInt(1),
                                    rs.getString(2),
                                    rs.getString(3),
                                    rs.getString(4)
                                    );
                  }

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
}
