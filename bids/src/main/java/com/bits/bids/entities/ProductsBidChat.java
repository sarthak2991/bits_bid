package com.bits.bids.entities;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Document(value = "chats", collection = "chats")
@Data
public class ProductsBidChat {
  @Id
  @Field("_id")
  private ObjectId id;
  @Field("productId")
  private Long productId;
  @Field("users")
  private List<Long> users;
  @Field("chat")
  private List<Chat> chats = new ArrayList<>();
  @Field("isActive")
  private Boolean isActive = true;
}
