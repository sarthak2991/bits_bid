package com.bits.bids.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Entity(name = "products")
@Data
@Table(name = "products")
public class Product {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "seller_id", nullable = false)
  private Long sellerId;
  @Column(name = "category_id", nullable = false)
  private Long categoryId;
  @Column(name = "name", nullable = false)
  private String name;
  @Column(name = "description", nullable = false, length = 500)
  private String description;
  @Column(name = "price", nullable = false)
  private Double price;
  @Column(name = "imgUrls", length = 1000)
  private String imgUrls;
  @OneToMany(fetch = FetchType.EAGER)
  @JoinColumn(name = "product_id")
  private List<Bid> bids;
  @Column(name = "status", nullable = false)
  private Integer status;
  @Column(name = "created_at")
  private Long createdAt;

  public enum Status {
    AVAILABLE(1), SOLD(2), UNSOLD(3);

    private final int code;

    Status(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }
  }
}
