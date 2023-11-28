package com.bits.bids.controllers;

import com.bits.bids.entities.Bid;
import com.bits.bids.entities.User;
import com.bits.bids.helpers.services.AuthService;
import com.bits.bids.models.requests.CreateBidRequest;
import com.bits.bids.models.requests.UpdateBidRequest;
import com.bits.bids.models.responses.ApiResponse;
import com.bits.bids.services.bids.BidService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bids")
@CrossOrigin(originPatterns = "*")
public class BidController {

  private final AuthService authService;
  private final BidService bidService;

  @Autowired
  public BidController(AuthService authService, BidService bidService) {
    this.authService = authService;
    this.bidService = bidService;
  }

  @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ApiResponse<?>> createBid(
      @Valid @RequestBody CreateBidRequest createBidRequest, HttpServletRequest request) {
    User user = authService.verifyAccessToken(request).getUser();
    ApiResponse<Bid> apiResponse = new ApiResponse<>();
    apiResponse.setResult(bidService.createBid(createBidRequest, user));
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }

  @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ApiResponse<?>> updateBid(
      @Valid @RequestBody UpdateBidRequest updateBidRequest, HttpServletRequest request,
      @PathVariable Long id) {
    User user = authService.verifyAccessToken(request).getUser();
    ApiResponse<String> apiResponse = new ApiResponse<>();
    apiResponse.setResult(bidService.updateBid(id, updateBidRequest, user));
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }

  @PostMapping(value = "/{id}/buyout",
          produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ApiResponse<?>> bidBuyOut(HttpServletRequest request,
          @PathVariable Long id) {
    User user = authService.verifyAccessToken(request).getUser();
    ApiResponse<String> apiResponse = new ApiResponse<>();
    apiResponse.setResult(bidService.bidBuyout(id, user));
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }
}
