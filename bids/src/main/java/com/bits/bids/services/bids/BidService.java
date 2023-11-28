package com.bits.bids.services.bids;

import com.bits.bids.entities.Bid;
import com.bits.bids.entities.User;
import com.bits.bids.models.requests.CreateBidRequest;
import com.bits.bids.models.requests.UpdateBidRequest;

public interface BidService {
  Bid createBid(CreateBidRequest createBidRequest, User user);

  String updateBid(Long id, UpdateBidRequest updateBidRequest, User user);

  String bidBuyout(Long id, User user);
}
