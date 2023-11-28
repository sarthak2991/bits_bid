package com.bits.bids.utils;

import com.bits.bids.entities.Chat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ChatUtils {

  public static List<Chat> getLatestChats(List<Chat> chats, Long time, Integer limit) {
    List<Chat> resultChats = new ArrayList<>();
    int index = binarySearch(chats, time);
    IntStream.rangeClosed(Math.max(0, index - limit), index).mapToObj(chats::get)
        .forEach(resultChats::add);
    return resultChats;
  }

  private static int binarySearch(List<Chat> chats, Long givenTime) {
    int left = 0;
    int right = chats.size() - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (chats.get(mid).getTime() < givenTime) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return Math.min(right, chats.size() - 1);
  }
}
