package com.bits.bids.models.responses;

import lombok.Data;

@Data
public class ApiResponse<T> {
  private String msg;
  private T result;
}
