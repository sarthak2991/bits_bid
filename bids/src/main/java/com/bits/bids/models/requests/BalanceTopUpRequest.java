package com.bits.bids.models.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class BalanceTopUpRequest {
  @NotNull(message = "top up amount required")
  @Positive(message = "amount should be positive")
  private Double topUpAmount;
}
