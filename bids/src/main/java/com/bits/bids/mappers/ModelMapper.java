package com.bits.bids.mappers;

import com.bits.bids.constants.HttpResponseMessage;
import com.bits.bids.entities.*;
import com.bits.bids.models.requests.*;
import com.bits.bids.models.responses.BalanceTopUpResponse;
import com.bits.bids.models.responses.BidResponse;
import com.bits.bids.models.responses.CategoryResponse;
import com.bits.bids.models.responses.ProductResponse;
import com.bits.bids.models.responses.ProductListResponse;
import com.bits.bids.utils.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ModelMapper {

  private final StrongPasswordEncryptor encryptor;

  @Autowired
  public ModelMapper(StrongPasswordEncryptor encryptor) {
    this.encryptor = encryptor;
  }

  public User prepareUser(UserSignUpRequest signUpRequest) {
    if (Objects.isNull(signUpRequest)) {
      return null;
    }
    User user = new User();
    user.setUsername(signUpRequest.getUsername());
    user.setName(signUpRequest.getName());
    user.setEmail(signUpRequest.getEmail());
    user.setPassword(encryptor.encryptPassword(signUpRequest.getPassword()));
    user.setBalance(0D);
    user.setBidInvBalance(0D);
    user.setHostel(signUpRequest.getHostel());
    user.setContactNo(signUpRequest.getContactNo());
    return user;
  }

  public ProductResponse prepareProductResponse(Product product) {
    if (Objects.isNull(product)) {
      return null;
    }
    ProductResponse productResponse = new ProductResponse();
    productResponse.setName(product.getName());
    productResponse.setPrice(product.getPrice());
    productResponse.setImgUrls(CollectionUtils.convertCsvToList(product.getImgUrls()));
    productResponse.setDescription(product.getDescription());
    productResponse.setBids(prepareBidResponseList(product.getBids()));
    productResponse.setId(product.getId());
    productResponse.setSellerId(product.getSellerId());
    productResponse.setCategoryId(product.getCategoryId());
    return productResponse;
  }

  private List<BidResponse> prepareBidResponseList(List<Bid> bids) {
    return bids.stream().map(this::prepareBidResponse).collect(Collectors.toList());
  }

  private BidResponse prepareBidResponse(Bid bid) {
    if (Objects.isNull(bid)) {
      return null;
    }
    BidResponse bidResponse = new BidResponse();
    bidResponse.setBidAmount(bid.getBidAmount());
    bidResponse.setBidTime(bid.getBidTime().getTime());
    bidResponse.setBidderId(bid.getBidderId());
    bidResponse.setIsFrozen(bid.getIsFrozen());
    bidResponse.setId(bid.getId());
    return bidResponse;
  }

  public Category prepareCategory(CreateCategoryRequest createCategoryRequest) {
    if (Objects.isNull(createCategoryRequest)) {
      return null;
    }
    Category category = new Category();
    category.setName(createCategoryRequest.getName());
    return category;
  }

  public List<CategoryResponse> prepareCategoryResponseList(List<Category> categories) {
    return categories.stream().map(this::prepareCategoryResponse).collect(Collectors.toList());
  }

  private CategoryResponse prepareCategoryResponse(Category category) {
    if (Objects.isNull(category)) {
      return null;
    }
    CategoryResponse categoryResponse = new CategoryResponse();
    categoryResponse.setId(category.getId());
    categoryResponse.setName(category.getName());
    return categoryResponse;
  }

  public ProductListResponse prepareProductResponseList(List<ProductResponse> productResponses,
      Long pageNo, Long limit) {
    ProductListResponse productListResponse = new ProductListResponse();
    productListResponse.setProducts(productResponses);
    boolean nextPageAvailable = productResponses.size() == limit;
    productListResponse.setNextPageAvailable(nextPageAvailable);
    productListResponse.setNextPage(nextPageAvailable ? pageNo + 1 : pageNo);
    return productListResponse;
  }

  public BalanceTopUpResponse prepareBalanceTopUpResponse(BalanceTopUpRequest balanceTopUpRequest,
      User user) {
    BalanceTopUpResponse balanceTopUpResponse = new BalanceTopUpResponse();
    balanceTopUpResponse.setMessage(HttpResponseMessage.BALANCE_TOP_UP_SUCCESS);
    balanceTopUpResponse.setBalance(user.getBalance() + balanceTopUpRequest.getTopUpAmount());
    return balanceTopUpResponse;
  }

  public Bid prepareBid(CreateBidRequest createBidRequest, User user, Long sellerId) {
    Bid bid = new Bid();
    bid.setBidAmount(createBidRequest.getBidAmount());
    bid.setBidTime(new Timestamp(System.currentTimeMillis()));
    bid.setBidderId(user.getId());
    bid.setIsFrozen(Boolean.FALSE);
    bid.setSellerId(sellerId);
    bid.setProductId(createBidRequest.getProductId());
    return bid;
  }

  public Product prepareProduct(CreateProductRequest createProductRequest, User user) {
    if (Objects.isNull(createProductRequest)) {
      return null;
    }
    Product product = new Product();
    product.setName(createProductRequest.getName());
    product.setPrice(createProductRequest.getPrice());
    product.setDescription(createProductRequest.getDescription());
    product.setStatus(Product.Status.AVAILABLE.getCode());
    product.setCategoryId(createProductRequest.getCategoryId());
    product.setSellerId(user.getId());
    product.setCreatedAt(System.currentTimeMillis());
    return product;
  }

  public ProductsBidChat prepareProductBidChat(SendChatRequest chatRequest, User user) {
    ProductsBidChat productsBidChat = new ProductsBidChat();
    productsBidChat.setChats(new ArrayList<>());
    productsBidChat.setUsers(List.of(chatRequest.getTo(), user.getId()));
    productsBidChat.setIsActive(Boolean.TRUE);
    productsBidChat.setProductId(chatRequest.getProductId());
    Chat chat = prepareChat(chatRequest, user);
    productsBidChat.getChats().add(chat);
    return productsBidChat;
  }

  public ProductsBidChat prepareProductBidChat(Product product, Long to, User user) {
    ProductsBidChat productsBidChat = new ProductsBidChat();
    productsBidChat.setChats(new ArrayList<>());
    productsBidChat.setUsers(List.of(to, user.getId()));
    productsBidChat.setIsActive(Boolean.TRUE);
    productsBidChat.setProductId(product.getId());
    return productsBidChat;
  }

  public Chat prepareChat(SendChatRequest chatRequest, User user) {
    Chat chat = new Chat();
    chat.setMsg(chatRequest.getMsg());
    chat.setSender(user.getId());
    chat.setReceiver(chatRequest.getTo());
    chat.setTime(System.currentTimeMillis());
    chat.setIsRead(Boolean.FALSE);
    return chat;
  }
}
