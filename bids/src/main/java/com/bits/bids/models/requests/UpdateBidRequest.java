package com.bits.bids.models.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class UpdateBidRequest {
  private Boolean withdraw;
  @NotNull(message = "product required")
  private Long productId;
  @NotNull(message = "bid amount required")
  @Positive(message = "bid amount must be positive")
  private Double bidAmount;
}
