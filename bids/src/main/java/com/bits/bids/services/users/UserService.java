package com.bits.bids.services.users;

import com.bits.bids.entities.User;
import com.bits.bids.models.requests.BalanceTopUpRequest;
import com.bits.bids.models.requests.UserSignInRequest;
import com.bits.bids.models.requests.UserSignUpRequest;
import com.bits.bids.models.responses.BalanceTopUpResponse;
import com.bits.bids.models.responses.UserSignInResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {

  User getProfile(User user, Long userId);

  User signUp(UserSignUpRequest signUpRequest);

  UserSignInResponse signIn(UserSignInRequest signInRequest);

  BalanceTopUpResponse balanceTopUp(BalanceTopUpRequest balanceTopUpRequest, User user);

    String uploadProfilePicture(User user,
                                MultipartFile profilePicture) throws IOException;
}
