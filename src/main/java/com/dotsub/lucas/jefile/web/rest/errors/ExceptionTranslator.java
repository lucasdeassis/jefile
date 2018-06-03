package com.dotsub.lucas.jefile.web.rest.errors;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Controller advice to translate the server side exceptions to client-friendly json structures.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ExceptionTranslator extends ResponseEntityExceptionHandler {

  @Override
  public ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    BindingResult result = ex.getBindingResult();
    List<FieldError> fieldErrors = result.getFieldErrors();

    ApiError error = new ApiError(HttpStatus.BAD_REQUEST, "Validation Error").addValidationErrors(
      fieldErrors);

    return buildResponseEntity(error, error.getStatus());
  }

  @ExceptionHandler(value = { EntityNotFoundException.class, JeFileNotFoundException.class })
  public ResponseEntity<Object> handleNotFound(
      final RuntimeException ex, final WebRequest request) {

    ApiError error = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());

    return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

  @ExceptionHandler({ NullPointerException.class, IllegalArgumentException.class,
      IllegalStateException.class })
  public ResponseEntity<Object> handleInternal(final RuntimeException ex,
      final WebRequest request) {

    ApiError error = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());

    return handleExceptionInternal(ex, error, new HttpHeaders(),
      HttpStatus.INTERNAL_SERVER_ERROR, request);
  }

  private <T extends Object> ResponseEntity<T> buildResponseEntity(T apiError, HttpStatus status) {
    return new ResponseEntity<T>(apiError, new HttpHeaders(), status);
  }
}
