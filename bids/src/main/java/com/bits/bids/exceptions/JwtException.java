package com.bits.bids.exceptions;

public class JwtException extends RuntimeException {

  private static final Long serialCode = 1L;

  public JwtException(String msg) {
    super(msg);
  }
}
