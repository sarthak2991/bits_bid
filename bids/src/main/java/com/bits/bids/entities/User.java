package com.bits.bids.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "users")
@Table(name = "users")
public class User {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "email", unique = true, nullable = false)
  private String email;
  @Column(name = "balance", nullable = false)
  private Double balance;
  @Column(name = "bid_inv_balance", nullable = false)
  private Double bidInvBalance;
  @Column(name = "name", nullable = false)
  private String name;
  @Column(name = "hostel", nullable = false)
  private String hostel;
  @Column(name = "contact_no", nullable = false, unique = true)
  private String contactNo;
  @Column(name = "username", nullable = false, unique = true)
  private String username;
  @Column(name = "password", nullable = false)
  private String password;
  @OneToMany
  @JoinColumn(name = "bidder_id")
  private List<Bid> bids;
  @Column(name = "imgUrl")
  private String imgUrl;
}
