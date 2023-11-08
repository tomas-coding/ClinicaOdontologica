package com.clinicaodontologica.Clinica.Odontologica.dao;

import com.clinicaodontologica.Clinica.Odontologica.model.Domicilio;
import com.clinicaodontologica.Clinica.Odontologica.model.Paciente;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAOH2 implements iDao<Paciente>{
    private static final Logger logger= Logger.getLogger(PacienteDAOH2.class);
    private static  final String SQL_INSERT="INSERT INTO PACIENTES (NOMBRE, APELLIDO, CEDULA, FECHA_INGRESO, DOMICILIO_ID, EMAIL) VALUES(?,?,?,?,?,?)";
    private static final String SQL_SELECT_BY="SELECT * FROM PACIENTES WHERE ID=?";
    private static final String SQL_DELETE_BY_ID="DELETE FROM PACIENTES WHERE ID=?";
    private static final String SQL_UPDATE="UPDATE PACIENTES SET NOMBRE=?, APELLIDO=?, CEDULA=?, FECHA_INGRESO=?, DOMICILIO_ID=?, EMAIL=? WHERE ID =?";
    private static final String SQL_SELECT_ALL="SELECT * FROM PACIENTES";
    private static final String SQL_SELECT_BY_EMAIL="SELECT * FROM PACIENTES WHERE EMAIL=?";
    @Override
    public Paciente guardar(Paciente paciente) {
        logger.info("guardando paciente");
        Connection connection= null;
        try{
            connection= BD.getConnection(); //obtengo la conexion
            DomicilioDAOH2 daoAux= new DomicilioDAOH2(); //creo una instancia de dao domicilio para devolver un objeto domicilio
            Domicilio domicilio= daoAux.guardar(paciente.getDomicilio());
            PreparedStatement psInsert= connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1, paciente.getNombre());
            psInsert.setString(2, paciente.getApellido());
            psInsert.setString(3, paciente.getCedula());
            psInsert.setDate(4, Date.valueOf(paciente.getFechaIngreso()));
            psInsert.setInt(5,domicilio.getId());
            psInsert.setString(6, paciente.getEmail());
            psInsert.execute(); //en teoria si hay claves se generan
            ResultSet clave= psInsert.getGeneratedKeys();
            while (clave.next()){
                paciente.setId(clave.getInt(1));
            }
            logger.info(" paciente guardado");


        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            try{
                connection.close();
            }catch (SQLException ex){
                logger.error(ex.getMessage());
            }
        }
        return paciente;
    }

    @Override
    public Paciente buscar(Integer id) {
        logger.info("iniciando las operaciones de : buscado por id "+id);
        Connection connection= null;
        Paciente paciente=null;
        Domicilio domicilio=null;
        try{
            connection= BD.getConnection();
            PreparedStatement psSelectOne= connection.prepareStatement(SQL_SELECT_BY);
            psSelectOne.setInt(1,id);
            ResultSet rs= psSelectOne.executeQuery();
            DomicilioDAOH2 daoAux= new DomicilioDAOH2();
            while (rs.next()){
                domicilio= daoAux.buscar(rs.getInt(6));
                paciente= new Paciente(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5).toLocalDate(),domicilio,rs.getString(7));

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
        return paciente;
    }


    @Override
    public void eliminar(Integer id) {
        logger.info("iniciando las operaciones de : eliminar por id "+id);
        Connection connection= null;
        Domicilio domicilio=null;
        Paciente paciente = null;
        try{
            connection= BD.getConnection();
            PreparedStatement psDeletetOne= connection.prepareStatement(SQL_DELETE_BY_ID);
            psDeletetOne.setInt(1,id);
            PacienteDAOH2 daoPacienteAux= new PacienteDAOH2();
            paciente=daoPacienteAux.buscar(id);
            DomicilioDAOH2 daoDomicilioAux= new DomicilioDAOH2();
            daoDomicilioAux.eliminar(paciente.getDomicilio().getId());
            psDeletetOne.execute();


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
    public void actualizar(Paciente paciente) {
        logger.info("iniciando las operaciones de : actualizado de un paciente ");
        Connection connection= null;
        try{
            connection= BD.getConnection();
            DomicilioDAOH2 daoAux= new DomicilioDAOH2();
            daoAux.actualizar(paciente.getDomicilio());
            PreparedStatement ps_Update= connection.prepareStatement(SQL_UPDATE);
            //van las parametrizadas
            ps_Update.setString(1, paciente.getNombre());
            ps_Update.setString(2, paciente.getApellido());
            ps_Update.setString(3, paciente.getCedula());
            ps_Update.setDate(4,Date.valueOf(paciente.getFechaIngreso()));
            ps_Update.setInt(5,paciente.getDomicilio().getId());
            ps_Update.setString(6,paciente.getEmail());
            ps_Update.setInt(7,paciente.getId());
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
    public List<Paciente> buscarTodos() {
        logger.info("iniciando las operaciones de : listado de un paciente ");
        Connection connection= null;
        List<Paciente> pacientes= new ArrayList<>();
        Paciente paciente=null;
        Domicilio domicilio=null;
        try{

            connection= BD.getConnection();
            Statement statement= connection.createStatement();
            ResultSet rs= statement.executeQuery(SQL_SELECT_ALL);
            DomicilioDAOH2 daoAux= new DomicilioDAOH2();
            while(rs.next()){
                domicilio= daoAux.buscar(rs.getInt(6));
                paciente= new Paciente(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5).toLocalDate(),domicilio,rs.getString(7));
                pacientes.add(paciente);
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
        return pacientes;
    }

    @Override
    public Paciente buscarPorString(String valor) {
        logger.info("iniciando las operaciones de : buscado por email "+valor);
        Connection connection= null;
        Paciente paciente=null;
        Domicilio domicilio=null;
        try{
            connection= BD.getConnection();
            PreparedStatement psSelectOne= connection.prepareStatement(SQL_SELECT_BY_EMAIL);
            psSelectOne.setString(1,valor);
            ResultSet rs= psSelectOne.executeQuery();
            DomicilioDAOH2 daoAux= new DomicilioDAOH2();
            while (rs.next()){
                domicilio= daoAux.buscar(rs.getInt(6));
                paciente= new Paciente(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5).toLocalDate(),domicilio,rs.getString(7));

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
        return paciente;
    }
}
