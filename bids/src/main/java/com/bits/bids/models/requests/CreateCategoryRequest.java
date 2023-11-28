package com.bits.bids.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import javax.xml.stream.events.StartElement;

@Data
public class CreateCategoryRequest {

  @NotNull
  @NotBlank(message = "name required")
  private String name;
}
