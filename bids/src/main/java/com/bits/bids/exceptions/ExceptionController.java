package com.bits.bids.exceptions;

import com.bits.bids.constants.HttpResponseMessage;
import com.bits.bids.models.responses.ApiResponse;
import com.bits.bids.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

@ControllerAdvice
@Slf4j
public class ExceptionController {

  @Value("${img.storage.location}")
  private String imgStorageLocation;

  @ExceptionHandler(value = ApiExecutionException.class)
  public ResponseEntity<ApiResponse<?>> apiExecutionException(ApiExecutionException e) {
    log.error("exception {}", e.getMessage());
    ApiResponse<?> apiResponse = new ApiResponse<>();
    apiResponse.setMsg(e.getMessage());
    return ResponseEntity.ok(apiResponse);
  }

  @ExceptionHandler(value = JwtException.class)
  public ResponseEntity<ApiResponse<?>> jwtException(JwtException e) {
    log.error("exception {}", e.getMessage());
    ApiResponse<?> apiResponse = new ApiResponse<>();
    apiResponse.setMsg(e.getMessage());
    return ResponseEntity.ok(apiResponse);
  }

  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponse<?>> methodArgumentNotValidException(
      MethodArgumentNotValidException e) {
    log.error("exception {}", e.getMessage());
    ApiResponse<?> apiResponse = new ApiResponse<>();
    apiResponse.setMsg(e.getFieldErrors().get(0).getDefaultMessage());
    return ResponseEntity.ok(apiResponse);
  }

  @ExceptionHandler(value = RuntimeException.class)
  public ResponseEntity<ApiResponse<?>> exception(RuntimeException e) {
    log.error("exception {}", StringUtils.printStackTrace(e));
    ApiResponse<?> apiResponse = new ApiResponse<>();
    apiResponse.setMsg(HttpResponseMessage.SOMETHING_WRONG);
    return ResponseEntity.ok(apiResponse);
  }

  @ExceptionHandler(value = FileNotFoundException.class)
  public ResponseEntity<InputStreamResource> fileNotFoundException(FileNotFoundException e) throws FileNotFoundException {
    log.error("exception {}", StringUtils.printStackTrace(e));
    Path imagePath = Paths.get(imgStorageLocation, "default.jpeg");
    InputStream in = new FileInputStream(imagePath.toAbsolutePath().toString());
    return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(new InputStreamResource(in));
  }
}
