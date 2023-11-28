package com.bits.bids.repository;

import com.bits.bids.entities.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {
  Page<Product> findBySellerId(Long sellerId, Pageable pageable);

  Page<Product> findByCategoryId(Long categoryId, Pageable pageable);

  List<Product> findByNameStartingWith(String name);

  List<Product> findByStatus(Integer status);

  @Transactional
  @Modifying
  @Query(value = "update products set status = :status where id = :id")
  void updateStatusById(@Param("status") int status, @Param("id") Long id);

  @Transactional
  @Modifying
  @Query(value = "update products set status = :status where id in :ids")
  void updateStatusByIdIn(@Param("status") int status, @Param("ids") List<Long> ids);

  Optional<Product> findByNameAndSellerId(String name, Long sellerId);

  Optional<Product> findByIdAndSellerId(Long id, Long productId);

  @Transactional
  @Modifying(clearAutomatically = true)
  @Query(value = "update products set imgUrls = :imgUrl where id = :id")
  void updateImgUrlById(String imgUrl, Long id);
}
