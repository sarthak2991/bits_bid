package com.bits.bids.services.category;

import com.bits.bids.entities.Category;
import com.bits.bids.models.requests.CreateCategoryRequest;
import com.bits.bids.models.responses.CategoryResponse;

import java.util.List;

public interface CategoryService {
  Category createCategory(CreateCategoryRequest createCategoryRequest);

  List<CategoryResponse> getCategories();
}
