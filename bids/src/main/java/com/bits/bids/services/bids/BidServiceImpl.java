package com.bits.bids.services.bids;

import com.bits.bids.constants.HttpResponseMessage;
import com.bits.bids.entities.Bid;
import com.bits.bids.entities.Product;
import com.bits.bids.entities.User;
import com.bits.bids.exceptions.ApiExecutionException;
import com.bits.bids.mappers.ModelMapper;
import com.bits.bids.models.requests.CreateBidRequest;
import com.bits.bids.models.requests.UpdateBidRequest;
import com.bits.bids.repository.BidRepository;
import com.bits.bids.repository.ProductsRepository;
import com.bits.bids.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BidServiceImpl implements BidService {

  private final BidRepository bidRepository;
  private final ProductsRepository productsRepository;
  private final ModelMapper mapper;
  private final UserRepository userRepository;

  @Autowired
  public BidServiceImpl(BidRepository bidRepository, ProductsRepository productsRepository,
                        ModelMapper mapper, UserRepository userRepository) {
    this.bidRepository = bidRepository;
    this.productsRepository = productsRepository;
    this.mapper = mapper;
    this.userRepository = userRepository;
  }

  @Override
  public Bid createBid(CreateBidRequest createBidRequest, User user) {
    if (user.getBalance() < createBidRequest.getBidAmount()) {
      throw new ApiExecutionException(HttpResponseMessage.INSUFFICIENT_BALANCE);
    }
    Product product =
        productsRepository.findById(createBidRequest.getProductId()).orElseThrow(() -> {
          return new ApiExecutionException(HttpResponseMessage.NO_SUCH_PRODUCT);
        });
    if (product.getStatus() != Product.Status.AVAILABLE.getCode()){
      throw new ApiExecutionException(HttpResponseMessage.PRODUCT_NOT_IN_AVAILABLE_STATE);
    }
    if (product.getSellerId() == user.getId().longValue()) {
      throw new ApiExecutionException(HttpResponseMessage.CANT_BID_ON_OWN_PRODUCT);
    }
    if (createBidRequest.getBidAmount() < product.getPrice()) {
      throw new ApiExecutionException(HttpResponseMessage.BID_GREATER_THAN_BASE_PRICE);
    }
    bidRepository.findByProductIdAndBidderId(createBidRequest.getProductId(), user.getId())
        .ifPresent(bid -> {
          throw new ApiExecutionException(HttpResponseMessage.YOU_ALREADY_PLACED_BID);
        });
    return bidRepository.save(mapper.prepareBid(createBidRequest, user, product.getSellerId()));
  }

  @Override
  public String updateBid(Long id, UpdateBidRequest updateBidRequest, User user) {
    Bid bid = bidRepository
        .findByIdAndBidderIdAndProductId(id, user.getId(), updateBidRequest.getProductId())
        .orElseThrow(() -> {
          return new ApiExecutionException(HttpResponseMessage.PLACED_BID_FIRST);
        });
    if (user.getBalance() < updateBidRequest.getBidAmount()) {
      throw new ApiExecutionException(HttpResponseMessage.INSUFFICIENT_BALANCE);
    }
    if (Objects.nonNull(updateBidRequest.getWithdraw()) && updateBidRequest.getWithdraw()) {
      bidRepository.deleteById(id);
      return HttpResponseMessage.BID_UPDATE_SUCCESS;
    }
    Product product =
        productsRepository.findById(updateBidRequest.getProductId()).orElseThrow(() -> {
          return new ApiExecutionException(HttpResponseMessage.NO_SUCH_PRODUCT);
        });
    if (product.getStatus() != Product.Status.AVAILABLE.getCode()){
      throw new ApiExecutionException(HttpResponseMessage.PRODUCT_NOT_IN_AVAILABLE_STATE);
    }
    if (updateBidRequest.getBidAmount() < product.getPrice()) {
      throw new ApiExecutionException(HttpResponseMessage.BID_GREATER_THAN_BASE_PRICE);
    }
    bidRepository.updateBidAmountById(updateBidRequest.getBidAmount(), bid.getId());
    return HttpResponseMessage.BID_UPDATE_SUCCESS;
  }

  @Override
  public String bidBuyout(Long id, User user) {
    Bid bid = bidRepository
            .findById(id)
            .orElseThrow(() -> {
              return new ApiExecutionException(HttpResponseMessage.PLACED_BID_FIRST);
            });
    Product product =
            productsRepository.findById(bid.getProductId()).orElseThrow(() -> {
              return new ApiExecutionException(HttpResponseMessage.NO_SUCH_PRODUCT);
            });
    if (product.getStatus() != Product.Status.AVAILABLE.getCode()){
      throw new ApiExecutionException(HttpResponseMessage.PRODUCT_NOT_IN_AVAILABLE_STATE);
    }
    if (!bid.getIsFrozen()){
      throw new ApiExecutionException(HttpResponseMessage.BID_NOT_FROZEN);
    }
    if (user.getBalance() < bid.getBidAmount()) {
      throw new ApiExecutionException(HttpResponseMessage.INSUFFICIENT_BALANCE);
    }
    processBidBuyout(bid, product);
    return HttpResponseMessage.BID_UPDATE_SUCCESS;
  }

  @Transactional
  public void processBidBuyout(Bid bid, Product product) {
    userRepository.updateUserBalance(bid.getBidderId(), -bid.getBidAmount());
    userRepository.updateUserBalance(product.getSellerId(), bid.getBidAmount());
    bidRepository.updateIsFrozenById(bid.getId(), Boolean.TRUE);
    productsRepository.updateStatusById(Product.Status.SOLD.getCode(), product.getId());
  }


}
