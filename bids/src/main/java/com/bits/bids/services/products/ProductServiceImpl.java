package com.bits.bids.services.products;

import com.bits.bids.constants.HttpResponseMessage;
import com.bits.bids.entities.Product;
import com.bits.bids.entities.User;
import com.bits.bids.exceptions.ApiExecutionException;
import com.bits.bids.mappers.ModelMapper;
import com.bits.bids.models.requests.CreateProductRequest;
import com.bits.bids.models.responses.ProductListResponse;
import com.bits.bids.models.responses.ProductResponse;
import com.bits.bids.repository.CategoryRepository;
import com.bits.bids.repository.ProductsRepository;
import com.bits.bids.utils.CollectionUtils;
import com.bits.bids.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductsService {

  @Value("${img.storage.location}")
  private String imgStorageLocation;

  @Value("${img.base.url}")
  private String imgBaseUrl;

  private final ProductsRepository productsRepository;
  private final ModelMapper mapper;
  private final CategoryRepository categoryRepository;

  @Autowired
  public ProductServiceImpl(ProductsRepository productsRepository, ModelMapper mapper,
      CategoryRepository categoryRepository) {
    this.productsRepository = productsRepository;
    this.mapper = mapper;
    this.categoryRepository = categoryRepository;
  }

  @Override
  public ProductResponse getProduct(Long id) {
    Product product = productsRepository.findById(id).orElseThrow(() -> {
      return new ApiExecutionException(HttpResponseMessage.NO_SUCH_PRODUCT);
    });
    return mapper.prepareProductResponse(product);
  }

  @Override
  public ProductListResponse getProducts(Long categoryId, Long pageNo, Long limit, Long sellerId) {
  List<Product> productPage = null;
  if (Objects.nonNull(sellerId)) {
  productPage = productsRepository.findBySellerId(sellerId);
  } else if (Objects.nonNull(categoryId)) {
  log.info("{}", categoryId);
  productPage = productsRepository.findByCategoryId(categoryId);
  } else {
  productPage = productsRepository.findAll();
  }
  List<ProductResponse> productResponses = productPage.stream()
  .map(mapper::prepareProductResponse).collect(Collectors.toList());
  return mapper.prepareProductResponseList(productResponses, pageNo, limit);
  }
  @Override
  public List<ProductResponse> searchProducts(String searchQuery) {
    if (StringUtils.isNullOrEmpty(searchQuery) || searchQuery.length() < 3) {
      throw new ApiExecutionException(HttpResponseMessage.SEARCH_QUERY_MUST_BE_OF_MIN_CHARS);
    }
    return productsRepository.findByNameStartingWith(searchQuery).stream()
        .map(mapper::prepareProductResponse).collect(Collectors.toList());
  }

  @Override
  public Product createProduct(CreateProductRequest createProductRequest, User user) {
    productsRepository.findByNameAndSellerId(createProductRequest.getName(), user.getId())
        .ifPresent(product -> {
          throw new ApiExecutionException(HttpResponseMessage.PRODUCT_ALREADY_EXISTS);
        });
    categoryRepository.findById(createProductRequest.getCategoryId()).orElseThrow(() -> {
      return new ApiExecutionException(HttpResponseMessage.NO_SUCH_CATEGORY);
    });
    return productsRepository.save(mapper.prepareProduct(createProductRequest, user));
  }

  @Override
public String uploadProductPicture(Long productId, String url, User user,
MultipartFile profilePicture) throws IOException {
Product product = productsRepository.findByIdAndSellerId(productId, user.getId()).orElseThrow(() -> {
return new ApiExecutionException(HttpResponseMessage.NO_SUCH_PRODUCT);
});
String imgUrl = null;
if (StringUtils.isNullOrEmpty(url)){
String profilePicFileName = profilePicture.getOriginalFilename();
if (profilePicture.isEmpty() || StringUtils.isNullOrEmpty(profilePicFileName)) {
throw new ApiExecutionException(HttpResponseMessage.IMAGE_INVALID);
}
if (!profilePicFileName.contains(".png") && !profilePicFileName.contains(".jpg")
&& !profilePicFileName.contains(".jpeg")) {
throw new ApiExecutionException(HttpResponseMessage.IMAGE_FORMAT_NOT_ALLOWED);
}
String profilePicName =
"product" +StringUtils.generateRandomString() + "_" + productId + "." + "jpeg";
Path profilePicturePath =
Paths.get(imgStorageLocation, profilePicName);
Files.write(profilePicturePath, profilePicture.getBytes());
imgUrl = imgBaseUrl + profilePicName;
} else {
imgUrl = url;
}
log.info("{}", product.getImgUrls());
List<String> urls = CollectionUtils.convertCsvToList(product.getImgUrls());
log.info("{}", urls);
if (urls.size() >= 3){
throw new ApiExecutionException("Only 3 images allowed");
}
urls.add(imgUrl);

productsRepository.updateImgUrlById(org.springframework.util.StringUtils.collectionToCommaDelimitedString(urls), productId);
return imgUrl;
}}
