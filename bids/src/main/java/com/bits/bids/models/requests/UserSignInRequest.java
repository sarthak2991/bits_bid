package com.bits.bids.models.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserSignInRequest {
  @NotNull
  @NotBlank(message = "username required")
  private String username;
  @NotNull
  @NotBlank(message = "password required")
  private String password;
}
