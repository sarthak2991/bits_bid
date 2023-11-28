package com.bits.bids.models.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserSignUpRequest {

  @NotNull(message = "name required")
  @NotBlank(message = "name required")
  private String name;
  @NotNull(message = "username required")
  @NotBlank(message = "username required")
  private String username;
  @NotNull(message = "email required")
  @NotBlank(message = "email required")
  @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@hyderabad\\.bits-pilani\\.ac\\.in$",
      message = "Invalid email")
  private String email;
  @NotNull(message = "password required")
  @NotBlank(message = "password required")
  private String password;
  @NotNull(message = "hostel required")
  @NotBlank(message = "hostel required")
  private String hostel;
  @NotNull(message = "contactNo required")
  @Pattern(regexp = "^([+]\\d{2})?\\d{10}$",
          message = "valid contact number required")
  private String contactNo;

}
