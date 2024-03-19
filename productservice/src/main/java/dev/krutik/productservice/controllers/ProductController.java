package dev.krutik.productservice.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public void getAllProducts() {

    }

    @GetMapping("{id}")
    public String getProductById(@PathVariable("id") Long id) {
        return "Here is product id : " + id;
    }

    @PostMapping
    public void createProduct() {

    }

    @PutMapping("{id}")
    public void updateProductById() {

    }

}
