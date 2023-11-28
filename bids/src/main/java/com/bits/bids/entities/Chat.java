package com.bits.bids.entities;

import lombok.Data;

@Data
public class Chat {
  private Long sender;
  private Long receiver;
  private String msg;
  private Long time;
  private Boolean isRead;
}
