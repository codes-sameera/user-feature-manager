package com.mettle.userfeaturemanager.exception;

import com.mettle.userfeaturemanager.model.communication.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.naming.AuthenticationException;
import java.util.Date;


@ControllerAdvice
public class ControllerExceptionHandler {
  
  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<ErrorMessage> authenticationException(AuthenticationException ex, WebRequest request) {
    ErrorMessage message = new ErrorMessage(
        HttpStatus.UNAUTHORIZED.value(),
        new Date(),
        ex.getMessage(),
        request.getDescription(false));
    return new ResponseEntity<ErrorMessage>(message, HttpStatus.UNAUTHORIZED);
  }
  
  
  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<ErrorMessage> globalAccessDeniedExceptionHandler(Exception ex, WebRequest request) {
    ErrorMessage message = new ErrorMessage(
        HttpStatus.FORBIDDEN.value(),
        new Date(),
        ex.getMessage(),
        request.getDescription(false));
    
    return new ResponseEntity<ErrorMessage>(message, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) {
    ErrorMessage message = new ErrorMessage(
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        new Date(),
        ex.getMessage(),
        request.getDescription(false));
    
    return new ResponseEntity<ErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}

