package com.bits.bids.services.products;

import com.bits.bids.entities.Product;
import com.bits.bids.entities.User;
import com.bits.bids.models.requests.CreateProductRequest;
import com.bits.bids.models.responses.ProductListResponse;
import com.bits.bids.models.responses.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductsService {
  ProductResponse getProduct(Long id);

  ProductListResponse getProducts(Long categoryId, Long pageNo, Long limit, Long userId);

  List<ProductResponse> searchProducts(String searchQuery);

  Product createProduct(CreateProductRequest createProductRequest, User user);

    String uploadProductPicture(Long productId, User user,
                                MultipartFile profilePicture) throws IOException;
}
