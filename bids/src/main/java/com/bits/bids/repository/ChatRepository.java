package com.bits.bids.repository;

import com.bits.bids.entities.ProductsBidChat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends MongoRepository<ProductsBidChat, String> {

  @Query("{ 'productId': ?0, 'users': { $all: ?1 } }")
  Optional<ProductsBidChat> findByProductIdAndUsers(Long productId, List<Long> userIds);

  @Query("{ 'users': { $all: ?0 } }")
  List<ProductsBidChat> findByUsers(List<Long> userIds);
}
