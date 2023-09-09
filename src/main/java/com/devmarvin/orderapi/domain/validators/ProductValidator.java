package com.devmarvin.orderapi.domain.validators;

import com.devmarvin.orderapi.domain.entity.Product;
import com.devmarvin.orderapi.common.exceptions.ValidateServiceException;

public class ProductValidator {

    public static void save(Product product){

        if(product.getName() == null || product.getName().trim().isEmpty()){
            throw new ValidateServiceException("El nombre es requerido");
        }

        if(product.getName().length() > 100){
            throw new ValidateServiceException("El nombre es muy largo (maximo 100 caracteres)");
        }

        if (product.getPrice() == null){
            throw new ValidateServiceException("El precio es requerido");
        }

        if(product.getPrice() < 0 ){
            throw new ValidateServiceException("El precio es incorrecto");
        }
    }
}
