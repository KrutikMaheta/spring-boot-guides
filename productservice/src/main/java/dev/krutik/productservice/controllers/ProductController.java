package dev.krutik.productservice.controllers;

import dev.krutik.productservice.dtos.GenericProductDTO;
import dev.krutik.productservice.exception.NotFoundException;
import dev.krutik.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<GenericProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("{id}")
    public GenericProductDTO getProductById(@PathVariable("id") Long id) throws NotFoundException {
        return productService.getProductById(id);
    }

    @PostMapping
    public GenericProductDTO createProduct(@RequestBody GenericProductDTO genericProductDTO) {
        return productService.createProduct(genericProductDTO);
    }

    @PutMapping("{id}")
    public GenericProductDTO updateProductById(@RequestBody GenericProductDTO genericProductDTO, @PathVariable("id") Long id) {
        return productService.updateProductById(id, genericProductDTO);
    }

    @DeleteMapping("{id}")
    public GenericProductDTO deleteProduct(@PathVariable("id") Long id) {
        return productService.deleteProduct(id);
    }
}
