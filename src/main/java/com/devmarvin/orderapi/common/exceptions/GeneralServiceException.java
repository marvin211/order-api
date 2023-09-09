package com.devmarvin.orderapi.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR) // El servidor devolverá un código 500
public class GeneralServiceException extends RuntimeException {
    public GeneralServiceException() {
    }

    public GeneralServiceException(String message) {
        super(message);
    }

    public GeneralServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public GeneralServiceException(Throwable cause) {
        super(cause);
    }

    public GeneralServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
