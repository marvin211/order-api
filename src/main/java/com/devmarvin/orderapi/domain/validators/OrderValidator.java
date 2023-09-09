package com.devmarvin.orderapi.domain.validators;

import com.devmarvin.orderapi.domain.entity.Order;
import com.devmarvin.orderapi.domain.entity.OrderLine;
import com.devmarvin.orderapi.common.exceptions.ValidateServiceException;

public class OrderValidator {

    public static void save(Order order){

        if(order.getLines() == null || order.getLines().isEmpty()){
            throw new ValidateServiceException("Las lineas son requeridas");
        }

        //Validar las lineas
        for(OrderLine line: order.getLines()){

            if(line.getProduct() == null || line.getProduct().getId() == null){
                throw new ValidateServiceException("El producto es requerido");
            }

            if(line.getQuantity() == null){
                throw new ValidateServiceException("La cantidad es requerida");
            }

            if(line.getQuantity() < 0){
                throw new ValidateServiceException("La cantidad es incorrecta");
            }

        }
    }
}
