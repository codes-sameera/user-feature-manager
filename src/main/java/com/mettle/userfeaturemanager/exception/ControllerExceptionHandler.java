package com.mettle.userfeaturemanager.exception;

import com.mettle.userfeaturemanager.model.communication.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.naming.AuthenticationException;
import java.util.Date;
import java.util.NoSuchElementException;


@ControllerAdvice
public class ControllerExceptionHandler {
  
  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<ErrorMessage> authenticationExceptionHandler(AuthenticationException ex, WebRequest request) {
    return createErrorResponse(HttpStatus.UNAUTHORIZED, ex.getMessage(), request);
  }
  
  
  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<ErrorMessage> accessDeniedExceptionHandler(Exception ex, WebRequest request) {
    return createErrorResponse(HttpStatus.FORBIDDEN, ex.getMessage(), request);
  }

  @ExceptionHandler(UsernameNotFoundException.class)
  public ResponseEntity<ErrorMessage> usernameNotFoundExceptionHandler(UsernameNotFoundException ex, WebRequest request) {
    return createErrorResponse(HttpStatus.UNAUTHORIZED, ex.getMessage(), request);
  }

  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<ErrorMessage> noSuchElementExceptionHandler(NoSuchElementException ex, WebRequest request) {
    return createErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorMessage> illegalArgumentExceptionHandler(IllegalArgumentException ex, WebRequest request) {
    return createErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) {
    return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), request);
  }

  private ResponseEntity<ErrorMessage> createErrorResponse(HttpStatus notFound, String ex, WebRequest request) {
    ErrorMessage message = new ErrorMessage(
            notFound.value(),
            new Date(),
            ex,
            request.getDescription(false));
    return new ResponseEntity<ErrorMessage>(message, notFound);
  }
}

