package com.bits.bids.repository;

import com.bits.bids.entities.Bid;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {


  Optional<Bid> findByProductIdAndBidderId(Long productId, Long id);

  Optional<Bid> findByIdAndBidderIdAndProductId(Long id, Long bidderId, Long productId);

  Optional<Bid> findByBidderIdAndSellerIdAndIsFrozen(Long bidderId, Long sellerId,
      Boolean isFrozen);

  @Transactional
  @Modifying
  @Query(nativeQuery = true, value = "update bids set bid_amount = :bidAmount where id = :id")
  void updateBidAmountById(Double bidAmount, Long id);

  @Transactional
  @Modifying
  @Query(nativeQuery = true, value = "update bids set is_frozen = :isFrozen where id = :id")
  void updateIsFrozenById(Long id, Boolean isFrozen);
}
