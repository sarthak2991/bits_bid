package com.bits.bids.models.responses;

import lombok.Data;

@Data
public class BalanceTopUpResponse {
  private String message;
  private Double balance;
}
