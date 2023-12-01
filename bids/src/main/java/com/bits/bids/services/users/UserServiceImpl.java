package com.bits.bids.services.users;

import com.bits.bids.constants.HttpResponseMessage;
import com.bits.bids.entities.Bid;
import com.bits.bids.entities.User;
import com.bits.bids.exceptions.ApiExecutionException;
import com.bits.bids.helpers.services.AuthService;
import com.bits.bids.mappers.ModelMapper;
import com.bits.bids.models.requests.BalanceTopUpRequest;
import com.bits.bids.models.requests.UserSignInRequest;
import com.bits.bids.models.requests.UserSignUpRequest;
import com.bits.bids.models.responses.BalanceTopUpResponse;
import com.bits.bids.models.responses.UserSignInResponse;
import com.bits.bids.repository.BidRepository;
import com.bits.bids.repository.UserRepository;
import com.bits.bids.utils.CollectionUtils;
import com.bits.bids.utils.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


  @Value("${img.storage.location}")
  private String imgStorageLocation;

  @Value("${img.base.url}")
  private String imgBaseUrl;

  private final UserRepository userRepository;
  private final ModelMapper mapper;
  private final AuthService authService;
  private final BidRepository bidRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository, ModelMapper mapper, AuthService authService,
      BidRepository bidRepository) {
    this.userRepository = userRepository;
    this.mapper = mapper;
    this.authService = authService;
    this.bidRepository = bidRepository;
  }

  @Override
  public User signUp(UserSignUpRequest signUpRequest) {
    Optional<User> existingUser =
        userRepository.findByEmailOrUsernameOrContactNo(signUpRequest.getEmail(), signUpRequest.getUsername(), signUpRequest.getContactNo());
    if (existingUser.isPresent()) {
      throw new ApiExecutionException(HttpResponseMessage.EMAIL_OR_USERNAME_ALREADY_USED);
    }
    return userRepository.save(mapper.prepareUser(signUpRequest));
  }

  @Override
  public UserSignInResponse signIn(UserSignInRequest signInRequest) {
    User signInUser = userRepository.findByUsername(signInRequest.getUsername()).orElseThrow(() -> {
      return new ApiExecutionException(HttpResponseMessage.USER_NOT_FOUND);
    });
    return authService.authenticate(signInRequest, signInUser);
  }

  @Override
  public BalanceTopUpResponse balanceTopUp(BalanceTopUpRequest balanceTopUpRequest, User user) {
    userRepository.updateUserBalance(user.getId(), balanceTopUpRequest.getTopUpAmount());
    return mapper.prepareBalanceTopUpResponse(balanceTopUpRequest, user);
  }

  @Override
  public User getProfile(User user, Long userId) {
    if (Objects.isNull(userId)) {
      return user;
    }
    Optional<User> requestedUser = userRepository.findById(userId);
    requestedUser.orElseThrow(() -> {
      throw new ApiExecutionException(HttpResponseMessage.USER_NOT_FOUND);
    });
    List<Bid> bids = bidRepository.findByBidderIdAndSellerIdAndIsFrozen(user.getId(), userId, Boolean.TRUE);
if (CollectionUtils.isEmpty(bids)){
throw new ApiExecutionException(HttpResponseMessage.CANT_VIEW_BUYER_INFO);
};
    return requestedUser.get();
  }

  @Override
public String uploadProfilePicture(User user, String url,
MultipartFile profilePicture) throws IOException {
String imgUrl = null;
if (StringUtils.isNullOrEmpty(url)){
String profilePicFileName = profilePicture.getOriginalFilename();
if (profilePicture.isEmpty()) {
throw new ApiExecutionException(HttpResponseMessage.IMAGE_INVALID);
}
if (!profilePicFileName.contains(".png") && !profilePicFileName.contains(".jpg")
&& !profilePicFileName.contains(".jpeg")) {
throw new ApiExecutionException(HttpResponseMessage.IMAGE_FORMAT_NOT_ALLOWED);
}
String profilePicName =
"users" + "_" + user.getId() + "." + "jpeg";
Path profilePicturePath =
Paths.get(imgStorageLocation, profilePicName);
Files.write(profilePicturePath, profilePicture.getBytes());
imgUrl = imgBaseUrl + profilePicName;
} else {
imgUrl = url;
}
userRepository.updateImgUrlById(imgUrl, user.getId());
return imgUrl;
}}

