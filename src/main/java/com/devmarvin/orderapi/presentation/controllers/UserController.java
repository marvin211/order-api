package com.devmarvin.orderapi.presentation.controllers;

import com.devmarvin.orderapi.domain.converters.UserConverter;
import com.devmarvin.orderapi.presentation.dto.LoginRequestDTO;
import com.devmarvin.orderapi.presentation.dto.LoginResponseDTO;
import com.devmarvin.orderapi.presentation.dto.SignupRequestDTO;
import com.devmarvin.orderapi.presentation.dto.UserDTO;
import com.devmarvin.orderapi.domain.entity.User;
import com.devmarvin.orderapi.domain.services.UserService;
import com.devmarvin.orderapi.common.utils.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserConverter userConverter;

    //Creación de nuevos usuarios
    @PostMapping("/signup")
    public ResponseEntity<WrapperResponse<UserDTO>> signup(@RequestBody SignupRequestDTO request){

        User user = userService.createUser(userConverter.signup(request));
        return new WrapperResponse<>(true, "success", userConverter.fromEntity(user))
                .createResponse(HttpStatus.CREATED);
    }

    //Servicio que permite hacer login
    @PostMapping("/login")
    public ResponseEntity<WrapperResponse<LoginResponseDTO>> login(@RequestBody LoginRequestDTO request){
        LoginResponseDTO response = userService.login(request);
        return new WrapperResponse<>(true, "success", response)
                .createResponse();
    }
}
