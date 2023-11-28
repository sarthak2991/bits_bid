package com.bits.bids.controllers;

import com.bits.bids.entities.Chat;
import com.bits.bids.entities.User;
import com.bits.bids.helpers.services.AuthService;
import com.bits.bids.models.requests.SendChatRequest;
import com.bits.bids.models.responses.ApiResponse;
import com.bits.bids.models.responses.ChatsResponse;
import com.bits.bids.services.chat.ChatService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/chat")
@CrossOrigin(originPatterns = "*")
public class ChatController {

  private final AuthService authService;
  private final ChatService chatService;

  @Autowired
  public ChatController(AuthService authService, ChatService chatService) {
    this.authService = authService;
    this.chatService = chatService;
  }


  @PostMapping(value = "/send", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ApiResponse<?>> sendChat(
      @Valid @RequestBody SendChatRequest sendChatRequest, HttpServletRequest request) {
    User user = authService.verifyAccessToken(request).getUser();
    ApiResponse<String> apiResponse = new ApiResponse<>();
    apiResponse.setResult(chatService.sendMsg(sendChatRequest, user));
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }

  @GetMapping(value = "/{productId}/{userId}/get", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ApiResponse<?>> getChat(
      @RequestParam(value = "time", required = false) Long time,
      @RequestParam(value = "limit", defaultValue = "20") Integer limit, HttpServletRequest request,
      @PathVariable Long productId,
      @PathVariable Long userId) {
    User user = authService.verifyAccessToken(request).getUser();
    if (Objects.isNull(time)) {
      time = System.currentTimeMillis();
    }
    ApiResponse<List<Chat>> apiResponse = new ApiResponse<>();
    apiResponse.setResult(chatService.getMsg(productId, userId, time, limit, user));
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }

  @GetMapping(value = "/chats",
          produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ApiResponse<?>> getChats(HttpServletRequest request) {
    User user = authService.verifyAccessToken(request).getUser();
    ApiResponse<List<ChatsResponse>> apiResponse = new ApiResponse<>();
    apiResponse.setResult(chatService.getChats(user));
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }
}
