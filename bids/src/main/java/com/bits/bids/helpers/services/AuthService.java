package com.bits.bids.helpers.services;

import com.bits.bids.constants.HttpResponseMessage;
import com.bits.bids.entities.User;
import com.bits.bids.exceptions.ApiExecutionException;
import com.bits.bids.exceptions.JwtException;
import com.bits.bids.models.requests.UserSignInRequest;
import com.bits.bids.models.responses.UserSignInResponse;
import com.bits.bids.repository.UserRepository;
import com.bits.bids.utils.StringUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class AuthService {

  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.max_life}")
  private Long jwtMaxLife;

  @Value("${jwt.subject}")
  private String jwtSubject;

  @Value("${jwt.issuer}")
  private String jwtIssuer;

  private final UserRepository userRepository;
  private final StrongPasswordEncryptor encryptor;

  @Autowired
  public AuthService(UserRepository userRepository, StrongPasswordEncryptor encryptor) {
    this.userRepository = userRepository;
    this.encryptor = encryptor;
  }


  public Builder verifyAccessToken(HttpServletRequest request) {
    String authorizationHeader = request.getHeader("Authorization");
    if (StringUtils.isNullOrEmpty(authorizationHeader)) {
      throw new JwtException(HttpResponseMessage.AUTHORIZATION_HEADER_IS_MISSING);
    }
    if (!authorizationHeader.contains("Bearer ")) {
      throw new JwtException(HttpResponseMessage.INVALID_AUTHORIZATION_HEADER);
    }
    String accessToken = authorizationHeader.replace("Bearer ", "");
    Claims claims = decodeToken(accessToken);
    Date expDate = claims.getExpiration();
    boolean isTokenExpired = expDate.getTime() < System.currentTimeMillis();
    if (isTokenExpired) {
      throw new JwtException(HttpResponseMessage.TOKEN_EXPIRED_OR_INVALID);
    }
    Long userId = claims.get("id", Long.class);
    Builder builder = new Builder();
    return builder.setUserId(userId);
  }

  public String generateToken(Long id) {
    Date jwtIssuedAt = new Date(System.currentTimeMillis());
    Date jwtExpiredAt = new Date(System.currentTimeMillis() + jwtMaxLife);
    return Jwts.builder().setSubject(jwtSubject).setIssuer(jwtIssuer).claim("id", id)
        .setIssuedAt(jwtIssuedAt).setExpiration(jwtExpiredAt)
        .signWith(SignatureAlgorithm.HS512, Base64.getDecoder().decode(secret)).compact();
  }

  private Claims decodeToken(String token) {
    return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
  }

  public UserSignInResponse authenticate(UserSignInRequest signInRequest, User signInUser) {
    if (!encryptor.checkPassword(signInRequest.getPassword(), signInUser.getPassword())) {
      throw new ApiExecutionException(HttpResponseMessage.INCORRECT_PASSWORD);
    }
    String accessToken = generateToken(signInUser.getId());
    UserSignInResponse userSignInResponse = new UserSignInResponse();
    userSignInResponse.setUser(signInUser);
    userSignInResponse.setAccessToken(accessToken);
    return userSignInResponse;
  }

  public class Builder {
    private Long userId;

    public Builder() {

    }

    public Builder setUserId(Long userId) {
      this.userId = userId;
      return this;
    }

    public User getUser() {
      Optional<User> user = userRepository.findById(userId);
      if (user.isEmpty()) {
        throw new ApiExecutionException(HttpResponseMessage.USER_NOT_FOUND);
      }
      return user.get();
    }
  }
}
