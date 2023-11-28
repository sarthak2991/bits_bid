package com.bits.bids.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateProductRequest {
  @NotNull(message = "name required")
  @NotBlank(message = "name required")
  private String name;
  @NotNull(message = "description required")
  @NotBlank(message = "description required")
  @Size(min = 20, max = 150, message = "describe your product in at least 20 or at max 150 chars")
  private String description;
  @NotNull(message = "price required")
  @Positive(message = "price should be positive")
  private Double price;
  @NotNull(message = "category required")
  private Long categoryId;
}
