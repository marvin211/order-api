package com.devmarvin.orderapi.common.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class WrapperResponse<T> {

    private boolean ok;
    private String message;
    private T body;

    public ResponseEntity<WrapperResponse<T>> createResponse(){
        return new ResponseEntity<>(this, HttpStatus.OK);
    }
    public ResponseEntity<WrapperResponse<T>> createResponse(HttpStatus status){
        return new ResponseEntity<>(this, status);//this es la instancia actual de WrapperResponse<T> y status es el estado HTTP proporcionado como parámetro
    }

    public WrapperResponse() {
    }

    public WrapperResponse(boolean ok, String message, T body) {
        this.ok = ok;
        this.message = message;
        this.body = body;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }
}
