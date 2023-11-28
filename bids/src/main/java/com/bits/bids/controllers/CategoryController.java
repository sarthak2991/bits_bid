package com.bits.bids.controllers;

import com.bits.bids.entities.Category;
import com.bits.bids.entities.User;
import com.bits.bids.helpers.services.AuthService;
import com.bits.bids.models.requests.CreateCategoryRequest;
import com.bits.bids.models.responses.ApiResponse;
import com.bits.bids.models.responses.CategoryResponse;
import com.bits.bids.services.category.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@CrossOrigin(originPatterns = "*")
public class CategoryController {

  private final AuthService authService;
  private final CategoryService categoryService;

  @Autowired
  public CategoryController(AuthService authService, CategoryService categoryService) {
    this.authService = authService;
    this.categoryService = categoryService;
  }

  @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ApiResponse<?>> createCategory(
      @Valid @RequestBody CreateCategoryRequest createCategoryRequest, HttpServletRequest request) {
    authService.verifyAccessToken(request);
    ApiResponse<Category> apiResponse = new ApiResponse<>();
    apiResponse.setResult(categoryService.createCategory(createCategoryRequest));
    return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
  }

  @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ApiResponse<?>> getCategories(HttpServletRequest request) {
    authService.verifyAccessToken(request);
    ApiResponse<List<CategoryResponse>> apiResponse = new ApiResponse<>();
    apiResponse.setResult(categoryService.getCategories());
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }
}
