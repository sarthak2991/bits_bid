package com.bits.bids.repository;

import com.bits.bids.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmailOrUsernameOrContactNo(String email, String username, String contactNo);

  Optional<User> findByUsername(String username);

  @Transactional
  @Modifying(clearAutomatically = true)
  @Query("update users set balance = balance + :amount where id = :id")
  void updateUserBalance(@Param("id") Long id, @Param("amount") Double amount);

  @Transactional
  @Modifying(clearAutomatically = true)
  @Query("update users set imgUrl = :imgUrl where id = :id")
  void updateImgUrlById(String imgUrl, Long id);
}
