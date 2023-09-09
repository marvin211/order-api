package com.devmarvin.orderapi.domain.converters;

import com.devmarvin.orderapi.presentation.dto.SignupRequestDTO;
import com.devmarvin.orderapi.presentation.dto.UserDTO;
import com.devmarvin.orderapi.domain.entity.User;

public class UserConverter extends AbstractConverter<User, UserDTO>{
    @Override
    public UserDTO fromEntity(User entity) {
      
        if(entity == null) return null;

        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());

        return dto;
    }

    @Override
    public User fromDTO(UserDTO dto) {
        
        if(dto == null) return null;

        User entity = new User();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());

        return entity;
    }

    //Metodo para convertir el request de signup a una entidad
    public User signup(SignupRequestDTO dto){
        
        if(dto == null) return null;

        User entity = new User();
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());

        return entity;
    }
}
