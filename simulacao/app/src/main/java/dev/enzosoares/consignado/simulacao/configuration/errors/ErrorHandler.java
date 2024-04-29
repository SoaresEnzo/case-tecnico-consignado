package dev.enzosoares.consignado.simulacao.configuration.errors;

import dev.enzosoares.consignado.simulacao.errors.BadRequestException;
import dev.enzosoares.consignado.simulacao.errors.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException e) {
        return ResponseEntity
                .status(400)
                .body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e) {
        return ResponseEntity
                .status(404)
                .body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleMethodNotSupported(Exception e) {
        this.logger.error("{} {}", e.getClass(), e.getMessage());
        return ResponseEntity
                .status(405)
                .body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleMediaTypeNotSupported(Exception e) {
        this.logger.error("{} {}", e.getClass(), e.getMessage());
        return ResponseEntity
                .status(415)
                .body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        return ResponseEntity
                .status(500)
                .body(new ErrorResponse(e.getMessage()));
    }

}
