
package com.devmarvin.orderapi.domain.converters;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractConverter<E, D> {

    //Convertir de entidad a dto
    public abstract D fromEntity(E entity);

    //Convertir de dto a entidad
    public abstract E fromDTO(D dto);

    //Convertir listas
    public List<D> fromEntity(List<E> entitys){
        return entitys.stream()
                .map( e -> fromEntity(e))
                .collect(Collectors.toList());
    }

    public List<E> fromDTO(List<D> dtos){
        return dtos.stream()
                .map( dto -> fromDTO(dto))
                .collect(Collectors.toList());
    }
}
