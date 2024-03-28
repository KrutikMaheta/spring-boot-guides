package dev.krutik.productservice.thirdpartyclients.productservice.fakestore;

import dev.krutik.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FakeStoreProductDTO {

    private Long id;
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;

}
