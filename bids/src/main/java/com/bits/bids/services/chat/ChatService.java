package com.bits.bids.services.chat;

import com.bits.bids.entities.Chat;
import com.bits.bids.entities.User;
import com.bits.bids.models.requests.SendChatRequest;
import com.bits.bids.models.responses.ChatsResponse;

import java.util.List;

public interface ChatService {
  String sendMsg(SendChatRequest sendChatRequest, User user);

  List<Chat> getMsg(Long productId, Long userId, Long time, Integer limit, User user);

  List<ChatsResponse> getChats(User user);
}
