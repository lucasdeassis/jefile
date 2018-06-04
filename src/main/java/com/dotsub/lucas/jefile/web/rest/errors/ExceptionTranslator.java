package com.dotsub.lucas.jefile.web.rest.errors;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import org.apache.tomcat.util.http.fileupload.FileUploadBase.SizeLimitExceededException;
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
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Controller advice to translate the server side exceptions to client-friendly json structures.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ExceptionTranslator extends ResponseEntityExceptionHandler {

  @ExceptionHandler({ ConstraintViolationException.class })
  public ResponseEntity<Object> handleBadRequest(final ConstraintViolationException ex,
      final WebRequest request) {

    ApiError error = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());

    return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.BAD_REQUEST,
      request);
  }

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

    return handleExceptionInternal(ex, error, new HttpHeaders(), error.getStatus(), request);
  }

  @ExceptionHandler(value = { EntityNotFoundException.class, JeFileNotFoundException.class })
  public ResponseEntity<Object> handleNotFound(
      final RuntimeException ex, final WebRequest request) {

    ApiError error = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());

    return handleExceptionInternal(ex, error, new HttpHeaders(), error.getStatus(), request);
  }

  @ExceptionHandler({ MaxUploadSizeExceededException.class })
  public ResponseEntity<Object> handleMaxExceeded(final MaxUploadSizeExceededException ex,
      final WebRequest request) {
    
    ApiError error = new ApiError(HttpStatus.PAYLOAD_TOO_LARGE,
      "File size exceeds 10MB limit");

    return handleExceptionInternal(ex, error, new HttpHeaders(), error.getStatus(), request);
  }

  @ExceptionHandler({ SizeLimitExceededException.class })
  public ResponseEntity<Object> handleMaxExceeded(final SizeLimitExceededException ex,
      final WebRequest request) {
    
    ApiError error = new ApiError(HttpStatus.PAYLOAD_TOO_LARGE,
      "Request Size exceeds 10MB limit");

    return handleExceptionInternal(ex, error, new HttpHeaders(), error.getStatus(), request);
  }

  @ExceptionHandler({ NullPointerException.class, IllegalArgumentException.class,
      IllegalStateException.class })
  public ResponseEntity<Object> handleInternal(final RuntimeException ex,
      final WebRequest request) {

    ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());

    return handleExceptionInternal(ex, error, new HttpHeaders(),
      error.getStatus(), request);
  }
}
