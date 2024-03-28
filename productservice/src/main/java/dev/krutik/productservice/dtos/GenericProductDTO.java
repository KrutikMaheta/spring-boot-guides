package dev.krutik.productservice.dtos;

import dev.krutik.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDTO {
    private Long id;
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;
}
