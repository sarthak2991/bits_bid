package com.bits.bids.services.chat;

import com.bits.bids.constants.HttpResponseMessage;
import com.bits.bids.entities.Chat;
import com.bits.bids.entities.Product;
import com.bits.bids.entities.ProductsBidChat;
import com.bits.bids.entities.User;
import com.bits.bids.exceptions.ApiExecutionException;
import com.bits.bids.mappers.ModelMapper;
import com.bits.bids.models.requests.SendChatRequest;
import com.bits.bids.models.responses.ChatsResponse;
import com.bits.bids.repository.ChatRepository;
import com.bits.bids.repository.ProductsRepository;
import com.bits.bids.repository.UserRepository;
import com.bits.bids.utils.ChatUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ChatServiceImpl implements ChatService {

  @Value("${img.base.url}")
  private String imgBaseUrl;

  private final UserRepository userRepository;
  private final ProductsRepository productsRepository;
  private final ChatRepository chatRepository;
  private final ModelMapper mapper;

  @Autowired
  public ChatServiceImpl(UserRepository userRepository, ProductsRepository productsRepository,
      ChatRepository chatRepository, ModelMapper mapper) {
    this.userRepository = userRepository;
    this.productsRepository = productsRepository;
    this.chatRepository = chatRepository;
    this.mapper = mapper;
  }

  @Override
  public String sendMsg(SendChatRequest sendChatRequest, User user) {
    userRepository.findById(sendChatRequest.getTo()).orElseThrow(() -> {
      return new ApiExecutionException(HttpResponseMessage.USER_NOT_FOUND);
    });
    productsRepository.findById(sendChatRequest.getProductId()).orElseThrow(() -> {
      return new ApiExecutionException(HttpResponseMessage.NO_SUCH_PRODUCT);
    });
    if (sendChatRequest.getTo() == user.getId().longValue()) {
      throw new ApiExecutionException(HttpResponseMessage.CHAT_WITH_OTHERS);
    }
    Optional<ProductsBidChat> productsBidChatOpt = chatRepository.findByProductIdAndUsers(
        sendChatRequest.getProductId(), List.of(sendChatRequest.getTo(), user.getId()));
    if (productsBidChatOpt.isEmpty()) {
      chatRepository.save(mapper.prepareProductBidChat(sendChatRequest, user));
      return HttpResponseMessage.MSG_SENT;
    }
    ProductsBidChat productsBidChat = productsBidChatOpt.get();
    productsBidChat.getChats().add(mapper.prepareChat(sendChatRequest, user));
    chatRepository.save(productsBidChat);
    return HttpResponseMessage.MSG_SENT;
  }

  @Override
  public List<Chat> getMsg(Long productId, Long userId, Long time, Integer limit, User user) {
    Product product = productsRepository.findById(productId).orElseThrow(() -> {
      return new ApiExecutionException(HttpResponseMessage.NO_SUCH_PRODUCT);
    });
    if (userId == user.getId().longValue()) {
      throw new ApiExecutionException(HttpResponseMessage.CHAT_WITH_OTHERS);
    }
    Optional<ProductsBidChat> productsBidChatOpt =
        chatRepository.findByProductIdAndUsers(productId, List.of(user.getId(), userId));
    if (productsBidChatOpt.isEmpty()) {
      chatRepository.save(mapper.prepareProductBidChat(product, userId, user));
      return new ArrayList<>();
    }
    ProductsBidChat productsBidChat = productsBidChatOpt.get();
    return ChatUtils.getLatestChats(productsBidChat.getChats(), time, limit);
  }

  @Override
  public List<ChatsResponse> getChats(User user) {
    List<ProductsBidChat> chatsUsers = chatRepository.findByUsers(List.of(user.getId()));
    return chatsUsers.stream().map(productsBidChat -> {
      ChatsResponse chatsResponse = new ChatsResponse();
      chatsResponse.setUser(productsBidChat.getUsers()
              .stream()
              .filter(id -> id != user.getId().longValue())
              .findFirst().orElse(null));
      chatsResponse.setImgUrl(imgBaseUrl + "users_" + chatsResponse.getUser() + ".jpeg");
      chatsResponse.setProductId(productsBidChat.getProductId());
      return chatsResponse;
    }).collect(Collectors.toList());
  }
}
