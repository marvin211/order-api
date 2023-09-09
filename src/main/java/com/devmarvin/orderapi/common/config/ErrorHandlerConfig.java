package com.devmarvin.orderapi.common.config;

import com.devmarvin.orderapi.common.utils.WrapperResponse;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.devmarvin.orderapi.common.exceptions.GeneralServiceException;
import com.devmarvin.orderapi.common.exceptions.NoDataFoundException;
import com.devmarvin.orderapi.common.exceptions.ValidateServiceException;

import ch.qos.logback.classic.Logger;

// Definir como van a ser retornados los errores al usuario
@ControllerAdvice
public class ErrorHandlerConfig extends ResponseEntityExceptionHandler {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(ErrorHandlerConfig.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> all(Exception e, WebRequest request){
        logger.error("Error no controlado: " + e.getMessage(), e);
        WrapperResponse<?> response = new WrapperResponse<>(false, "Interval Server Error", null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR); // Se retorna la respuesta al usuario
    }

    @ExceptionHandler(ValidateServiceException.class)
    public ResponseEntity<?> validate(ValidateServiceException e, WebRequest request){
        logger.info("Error de validación: " + e.getMessage(), e);
        WrapperResponse<?> response = new WrapperResponse<>(false, e.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<?> noDataFound(NoDataFoundException e, WebRequest request){
        logger.info("No se encontraron datos: " + e.getMessage(), e);
        WrapperResponse<?> response = new WrapperResponse<>(false, e.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GeneralServiceException.class)
    public ResponseEntity<?> general(GeneralServiceException e, WebRequest request){
        logger.error("Error general: " + e.getMessage(), e);
        WrapperResponse<?> response = new WrapperResponse<>(false, "Interval Server Error", null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
