package com.bits.bids.models.responses;

import com.bits.bids.models.responses.ProductResponse;
import lombok.Data;

import java.util.List;

@Data
public class ProductListResponse {
  private List<ProductResponse> products;
  private Boolean nextPageAvailable;
  private Long nextPage;
}
