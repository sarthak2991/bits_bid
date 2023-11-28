package com.bits.bids.models.responses;

import com.bits.bids.entities.User;
import lombok.Data;

@Data
public class UserSignInResponse {
  private User user;
  private String accessToken;
}
