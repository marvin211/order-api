package com.devmarvin.orderapi.domain.validators;

import com.devmarvin.orderapi.domain.entity.User;
import com.devmarvin.orderapi.common.exceptions.ValidateServiceException;

public class UserValidator {

    public static void signup(User user){
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()){
            throw new ValidateServiceException("El nombre de usuario es requerido");
        }

        if(user.getUsername().length() > 30){
            throw new ValidateServiceException("El nombre de usuario no debe ser mayor a 30 caracteres");
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()){
            throw new ValidateServiceException("El password es requerido");
        }

        if(user.getPassword().length() > 30){
            throw new ValidateServiceException("El password no debe ser mayor a 30 caracteres");
        }

        if(user.getPassword().length() < 4 ){
            throw new ValidateServiceException("El password debe tener al menos 4 caracteres");
        }
        
    }
}
