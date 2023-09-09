package com.devmarvin.orderapi.domain.services;

import com.devmarvin.orderapi.domain.converters.UserConverter;
import com.devmarvin.orderapi.presentation.dto.LoginRequestDTO;
import com.devmarvin.orderapi.presentation.dto.LoginResponseDTO;
import com.devmarvin.orderapi.domain.entity.User;
import com.devmarvin.orderapi.common.exceptions.GeneralServiceException;
import com.devmarvin.orderapi.common.exceptions.NoDataFoundException;
import com.devmarvin.orderapi.common.exceptions.ValidateServiceException;
import com.devmarvin.orderapi.domain.repository.UserRepository;
import com.devmarvin.orderapi.domain.validators.UserValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserRepository userRepository;

    //Dar de alta al usuario o crearlo
    public User createUser(User user){

        try{
            UserValidator.signup(user);

            User existUser = userRepository.findByUsername(user.getUsername())
                    .orElse(null);

            if(existUser != null){
                throw new ValidateServiceException("El nombre de usuario ya existe");
            }

            //Si no existe el usuario se crea
            return userRepository.save(user);


        }catch (ValidateServiceException | NoDataFoundException e){
            log.info(e.getMessage(), e);
            throw e;
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    //Metodo para iniciar sesion
    public LoginResponseDTO login(LoginRequestDTO request){
        try{

            User user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow( () -> new ValidateServiceException("Usuario o Password incorrectos"));

            if(!user.getPassword().equals(request.getPassword())){
                throw new ValidateServiceException("Usuario o Password incorrectos");
            }

            return new LoginResponseDTO(userConverter.fromEntity(user), "token"); //Esta clase recibe como parametro el usuario y el token

        }catch (ValidateServiceException | NoDataFoundException e){
            log.info(e.getMessage(), e);
            throw e;
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }
}
