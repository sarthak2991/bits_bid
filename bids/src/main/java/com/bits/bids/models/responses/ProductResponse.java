package com.bits.bids.models.responses;

import lombok.Data;

import java.util.List;

@Data
public class ProductResponse {
  private Long id;
  private String name;
  private Double price;
  private String description;
  private List<String> imgUrls;
  private List<BidResponse> bids;
  private Long sellerId;
  private Long categoryId;
}
