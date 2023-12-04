package com.clinicaodontologica.Clinica.Odontologica.exception;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.apache.log4j.Logger;

@ControllerAdvice
public class GlobalException {
    private static final Logger logger =
                Logger.getLogger( GlobalException.class )
    ;
    @ExceptionHandler({ResorceNotFoundException.class})
    public ResponseEntity<String> tratamientoResorceNotFoundException(ResorceNotFoundException rnfe){
        logger.error( "No funciona" );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
    }
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> tratamientoBadRequestException(BadRequestException rnfe){
        logger.error( "No funciona" );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(rnfe.getMessage());
    }

}
