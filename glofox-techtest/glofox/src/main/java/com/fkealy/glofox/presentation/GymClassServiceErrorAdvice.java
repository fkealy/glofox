package com.fkealy.glofox.presentation;


import com.fkealy.glofox.exceptions.BookingServiceException;
import com.fkealy.glofox.exceptions.GymClassServiceValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.*;


@ControllerAdvice
public class GymClassServiceErrorAdvice {

    @ExceptionHandler
    public ResponseEntity<String> handleRunTimeException(RuntimeException e) {
        return error(INTERNAL_SERVER_ERROR, e);
    }

    @ExceptionHandler({GymClassServiceValidationException.class})
    public ResponseEntity<String> handleGymClassServiceValidationException(GymClassServiceValidationException e){
        return error(BAD_REQUEST, e);
    }

    @ExceptionHandler({BookingServiceException.class})
    public ResponseEntity<String> handleBookingServiceException(BookingServiceException e){
        return error(UNPROCESSABLE_ENTITY, e);
    }

    private ResponseEntity<String> error(HttpStatus status, Exception e) {
        return ResponseEntity.status(status).body(e.getMessage());
    }
}
