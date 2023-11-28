package com.bits.bids.services.category;

import com.bits.bids.constants.HttpResponseMessage;
import com.bits.bids.entities.Category;
import com.bits.bids.exceptions.ApiExecutionException;
import com.bits.bids.mappers.ModelMapper;
import com.bits.bids.models.requests.CreateCategoryRequest;
import com.bits.bids.models.responses.CategoryResponse;
import com.bits.bids.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;
  private final ModelMapper mapper;

  @Autowired
  public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper mapper) {
    this.categoryRepository = categoryRepository;
    this.mapper = mapper;
  }

  @Override
  public Category createCategory(CreateCategoryRequest createCategoryRequest) {
    categoryRepository.findByName(createCategoryRequest.getName()).ifPresent(category -> {
      throw new ApiExecutionException(HttpResponseMessage.CATEGORY_ALREADY_EXISTS);
    });
    return categoryRepository.save(mapper.prepareCategory(createCategoryRequest));
  }

  @Override
  public List<CategoryResponse> getCategories() {
    return mapper.prepareCategoryResponseList(categoryRepository.findAll());
  }
}
