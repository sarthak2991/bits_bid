package com.bits.bids.controllers;

import com.bits.bids.entities.User;
import com.bits.bids.helpers.services.AuthService;
import com.bits.bids.models.requests.CreateProductRequest;
import com.bits.bids.models.responses.ApiResponse;
import com.bits.bids.models.responses.ProductResponse;
import com.bits.bids.models.responses.ProductListResponse;
import com.bits.bids.services.products.ProductsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(originPatterns = "*")
public class ProductsController {

  private final AuthService authService;
  private final ProductsService productsService;

  @Autowired
  public ProductsController(AuthService authService, ProductsService productsService) {
    this.authService = authService;
    this.productsService = productsService;
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ApiResponse<?>> getProduct(@PathVariable Long id,
      HttpServletRequest request) {
    authService.verifyAccessToken(request);
    ApiResponse<ProductResponse> apiResponse = new ApiResponse<>();
    apiResponse.setResult(productsService.getProduct(id));
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }

  @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ApiResponse<?>> getProducts(
      @RequestParam(value = "categoryId", required = false) Long categoryId,
      @RequestParam(value = "pageNo", defaultValue = "1") Long pageNo,
      @RequestParam(value = "limit", defaultValue = "8") Long limit,
      @RequestParam(value = "sellerId", required = false) Long sellerId,
      HttpServletRequest request) {
    authService.verifyAccessToken(request);
    ApiResponse<ProductListResponse> apiResponse = new ApiResponse<>();
    apiResponse.setResult(productsService.getProducts(categoryId, pageNo, limit, sellerId));
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }

  @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ApiResponse<?>> searchProducts(
      @Valid @RequestParam(value = "name", required = false) String searchQuery,
      HttpServletRequest request) {
    authService.verifyAccessToken(request);
    ApiResponse<List<ProductResponse>> apiResponse = new ApiResponse<>();
    apiResponse.setResult(productsService.searchProducts(searchQuery));
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }

  @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ApiResponse<?>> createProducts(
      @Valid @RequestBody CreateProductRequest createProductRequest, HttpServletRequest request) {
    User user = authService.verifyAccessToken(request).getUser();
    ApiResponse<Object> apiResponse = new ApiResponse<>();
    apiResponse.setResult(productsService.createProduct(createProductRequest, user));
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }

  @PostMapping(value = "/{productId}/pic", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<ApiResponse<?>> uploadProfilePic(HttpServletRequest request,
@RequestParam(value = "pic", required = false) MultipartFile pic, @RequestParam("url") String url,
@PathVariable Long productId) throws IOException {
final User user = authService.verifyAccessToken(request).getUser();
ApiResponse<String> apiResponse = new ApiResponse<>();
apiResponse.setResult(productsService.uploadProductPicture(productId, url, user, pic));
return new ResponseEntity<>(apiResponse, HttpStatus.OK);
}
}
