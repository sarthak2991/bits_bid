package com.bits.bids.controllers;

import com.bits.bids.entities.User;
import com.bits.bids.helpers.services.AuthService;
import com.bits.bids.models.requests.BalanceTopUpRequest;
import com.bits.bids.models.requests.UserSignInRequest;
import com.bits.bids.models.requests.UserSignUpRequest;
import com.bits.bids.models.responses.ApiResponse;
import com.bits.bids.models.responses.BalanceTopUpResponse;
import com.bits.bids.models.responses.UserSignInResponse;
import com.bits.bids.services.users.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(originPatterns = "*")
public class UserController {

  private final UserService userService;
  private final AuthService authService;

  @Autowired
  public UserController(UserService userService, AuthService authService) {
    this.userService = userService;
    this.authService = authService;
  }

  @GetMapping(value = "/profile", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ApiResponse<?>> getProfile(HttpServletRequest request,
      @RequestParam(value = "id", required = false) Long id) {
    User user = authService.verifyAccessToken(request).getUser();
    ApiResponse<User> apiResponse = new ApiResponse<>();
    apiResponse.setResult(userService.getProfile(user, id));
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }

  @PostMapping(value = "/sign_up", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ApiResponse<?>> signUp(HttpServletRequest request,
      @Valid @RequestBody UserSignUpRequest signUpRequest) {
    ApiResponse<User> apiResponse = new ApiResponse<>();
    apiResponse.setResult(userService.signUp(signUpRequest));
    return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
  }

  @PostMapping(value = "/sign_in", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ApiResponse<?>> signIn(HttpServletRequest request,
      @Valid @RequestBody UserSignInRequest signInRequest) {
    ApiResponse<UserSignInResponse> apiResponse = new ApiResponse<>();
    apiResponse.setResult(userService.signIn(signInRequest));
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }

  @PostMapping(value = "/balance", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ApiResponse<?>> balanceTopUp(HttpServletRequest request,
      @Valid @RequestBody BalanceTopUpRequest balanceTopUpRequest) {
    final User user = authService.verifyAccessToken(request).getUser();
    ApiResponse<BalanceTopUpResponse> apiResponse = new ApiResponse<>();
    apiResponse.setResult(userService.balanceTopUp(balanceTopUpRequest, user));
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }

  @PostMapping(value = "/profile_pic", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<ApiResponse<?>> uploadProfilePic(HttpServletRequest request,
@RequestParam(value = "pic", required = false) MultipartFile pic,
@RequestParam(value = "url",required = false) String url) throws IOException {
final User user = authService.verifyAccessToken(request).getUser();
ApiResponse<String> apiResponse = new ApiResponse<>();
apiResponse.setResult(userService.uploadProfilePicture(user, url, pic));
return new ResponseEntity<>(apiResponse, HttpStatus.OK);
}

}
