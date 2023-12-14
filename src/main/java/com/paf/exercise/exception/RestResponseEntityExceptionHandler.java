package com.paf.exercise.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
    extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorRepresentation> handleResourceNotFoundException(
      ResourceNotFoundException resourceNotFoundException) {
    String reason = "Resource not found";
    ErrorRepresentation errorRepresentation = getRejectedResponse(INTERNAL_SERVER_ERROR, reason,
        resourceNotFoundException.getMessage());

    return new ResponseEntity<>(errorRepresentation, INTERNAL_SERVER_ERROR);
  }

  private ErrorRepresentation getRejectedResponse(HttpStatus httpStatus, String reason,
      String message) {
    return ErrorRepresentation.builder()
        .code(httpStatus.value())
        .reason(reason)
        .message(message)
        .referenceError(httpStatus.getReasonPhrase())
        .build();
  }
}