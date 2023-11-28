package com.bits.bids.exceptions;

public class ApiExecutionException extends RuntimeException {

  private static final Long serialCode = 1L;

  public ApiExecutionException(String msg) {
    super(msg);
  }
}
