package com.clinicaodontologica.Clinica.Odontologica.dao;

import com.clinicaodontologica.Clinica.Odontologica.model.Odontologo;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;


public class OdontologoDAOARRAYLIST implements iDao<Odontologo> 
{
      private List<Odontologo> listaOdontologos = new ArrayList();
      private static int idAutoIncr = 1;


      private static final Logger logger= Logger.getLogger(OdontologoDAOARRAYLIST.class);

      @Override
      public Odontologo guardar(Odontologo odontologo)
      {
            Odontologo odontologoIncremental = new Odontologo( 
                  OdontologoDAOARRAYLIST.idAutoIncr++,  
                  odontologo.getMatricula(), 
                  odontologo.getNombre(), 
                  odontologo.getApellido() 
            );
            listaOdontologos.add(odontologoIncremental);
            logger.info( "Odontologo guardado!" );
            return odontologoIncremental;
      }

      @Override
      public Odontologo buscar(Integer id) 
      {
            logger.info("iniciando la operacion de buscado por id "+id);
            int i = 0;
            Odontologo encontrado = null;
            Odontologo operando;
            while( i < listaOdontologos.size() && encontrado == null ){
                  operando = listaOdontologos.get(i);
                  logger.info("BUCLE");
                  if( operando.getId() == id ){
                        encontrado = operando;
                        logger.info( "Odontologo encontrado!" );
                        return encontrado;
                  }
                  i++;
            }
            logger.warn( "Odontologo NO encontrado!" );
            return encontrado;
      }

      @Override
      public void eliminar(Integer id) 
      {
            int i = 0;
            Integer pae = null; // Posicion A Eliminar (PAE) -> (pae)
                                //
            // Si no hay odontologos
            if( listaOdontologos.size() == 0 ){
                  logger.warn( "No hay odontologos!" ); // Retorno
                  return;
            }

            // Lo busco..
            while( i < listaOdontologos.size() && pae == null ){
                  if( listaOdontologos.get(i).getId() == id )
                        pae = i;
                  i++;
            }

            // Si no lo encontre
            if( pae == null )
                  logger.warn( "Odontologo NO encontrado!" ); // Retorno

            // Entonces... ELIMINO la posicion
            listaOdontologos.remove( pae.intValue() );
            logger.info( "Odontologo Eliminado!" );
            return;
      }

      @Override
      public void actualizar(Odontologo odontologo) 
      {
            int i = 0;
            Integer paa = null; // Posicion A Actualizar (PAA) -> (paa)
                                //
            // Si no hay odontologos
            if( listaOdontologos.size() == 0 ){
                  logger.warn( "No hay odontologos!" ); // Retorno
                  return;
            }

            // Lo busco..
            while( i < listaOdontologos.size() && paa == null ){
                  if( listaOdontologos.get(i).getId() == odontologo.getId() )
                        paa = i;
                  i++;
            }

            // Si no lo encontre
            if( paa == null )
                  logger.warn( "Odontologo NO encontrado!" ); // Retorno

            // Entonces... ELIMINO la posicion
            listaOdontologos.add( paa, odontologo );
            logger.info( "Odontologo Actualizado!" );
            return;
      }

      @Override
      public List<Odontologo> buscarTodos() 
      {
            return listaOdontologos;
      }

      @Override
      public Odontologo buscarPorString(String valor) 
      {
            // 'valor' puede ser:
            // Matricula
            // Nombre
            // Apellido
            int i = 0;
            Odontologo encontrado = null;
            Odontologo operando;
            while( i < listaOdontologos.size() && encontrado == null ){
                  operando = listaOdontologos.get(i);
                  if( 
                        operando.getMatricula() == valor ||
                        operando.getNombre() == valor ||
                        operando.getApellido() == valor
                  ){
                        encontrado = operando;
                        logger.info( "Odontologo encontrado!" );
                        return encontrado;
                  }
                  i++;
            }
            if( encontrado == null )
                  logger.warn( "Odontologo NO encontrado!" );
            return encontrado;
      }
}
