package dev.krutik.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseModel {
    private String title;
    private String description;
    private String image;
    // Product : Category
    // L to R -> 1 : 1
    // R to L -> m : 1
    // Many to One Relation
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private double price;
}
