package com.bits.bids.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CurrentTimestamp;

import java.sql.Timestamp;

@Data
@Table(name = "bids")
@Entity(name = "bids")
public class Bid {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "product_id", nullable = false)
  private Long productId;
  @Column(name = "bid_amount", nullable = false)
  private Double bidAmount;
  @Column(name = "bidder_id", nullable = false)
  private Long bidderId;
  @Column(name = "seller_id", nullable = false)
  private Long sellerId;
  @Column(name = "is_frozen", nullable = false)
  private Boolean isFrozen;
  @Column(name = "bid_time")
  @CurrentTimestamp
  private Timestamp bidTime;
}
