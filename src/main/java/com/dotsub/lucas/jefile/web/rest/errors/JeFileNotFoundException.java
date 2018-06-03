package com.dotsub.lucas.jefile.web.rest.errors;

/**
 * exception that returns when creating a file with a not valid ID.
 */
public class JeFileNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public JeFileNotFoundException() {
    super("Unknown metadata for provided ID");
  }

  public JeFileNotFoundException(String message) {
    super(message);
  }
}
