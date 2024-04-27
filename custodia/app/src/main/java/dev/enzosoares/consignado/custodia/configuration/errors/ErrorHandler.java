package dev.enzosoares.consignado.custodia.configuration.errors;

import dev.enzosoares.consignado.custodia.errors.BadRequestException;
import dev.enzosoares.consignado.custodia.errors.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e) {
        return ResponseEntity
                .status(404)
                .body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException e) {
        return ResponseEntity
                .status(400)
                .body(new ErrorResponse(e.getMessage()));
    }

}
