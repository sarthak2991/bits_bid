package com.bits.bids.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SendChatRequest {
  @NotNull(message = "receiver Id required")
  private Long to;
  @NotNull(message = "msg required")
  @NotBlank(message = "msg required")
  private String msg;
  @NotNull(message = "product Id required")
  private Long productId;
}
