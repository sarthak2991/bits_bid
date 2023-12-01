package com.bits.bids.jobs;

import com.bits.bids.entities.Bid;
import com.bits.bids.entities.Product;
import com.bits.bids.repository.BidRepository;
import com.bits.bids.repository.ProductsRepository;
import com.bits.bids.repository.UserRepository;
import com.bits.bids.utils.CollectionUtils;
import com.bits.bids.utils.StringUtils;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class BidSettlementJob {

  @Value("${bid.active.duration}")
  private Long bidActiveDuration;

  private final ProductsRepository productsRepository;
  private final BidRepository bidRepository;
  private final UserRepository userRepository;

  @Autowired
  public BidSettlementJob(ProductsRepository productsRepository, BidRepository bidRepository,
      UserRepository userRepository) {
    this.productsRepository = productsRepository;
    this.bidRepository = bidRepository;
    this.userRepository = userRepository;
  }


  @Scheduled(fixedRate = 300000)
  public void process() {
    log.info("Processing products bids settlement job at {}", new Date(System.currentTimeMillis()));
    List<Product> products = productsRepository.findByStatus(Product.Status.AVAILABLE.getCode());
    log.info("Found {} not placed products for bid settlement", products.size());
    List<Long> notSoldProducts = new ArrayList<>();
    products.forEach(product -> {
      long createAt = product.getCreatedAt();
      long duration = System.currentTimeMillis() - bidActiveDuration;
      if (createAt > duration) {
        log.info("Bid settlement period haven't crossed skipping the product");
        return;
      }
      if (CollectionUtils.isEmpty(product.getBids())) {
        notSoldProducts.add(product.getId());
        return;
      }
      log.info("found highest bit for product Id {} freezing the bid", product.getId());
      Bid highestBid = product.getBids().stream().max(Comparator.comparing(Bid::getBidAmount))
          .orElse(product.getBids().get(0));
      bidRepository.updateIsFrozenById(highestBid.getId(), Boolean.TRUE);
    });
  }
}
