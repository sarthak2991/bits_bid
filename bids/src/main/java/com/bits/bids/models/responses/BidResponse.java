package com.bits.bids.models.responses;

import lombok.Data;

@Data
public class BidResponse {

  private Long bidderId;
  private Long id;
  private Double bidAmount;
  private Boolean isFrozen;
  private Long bidTime;
}
