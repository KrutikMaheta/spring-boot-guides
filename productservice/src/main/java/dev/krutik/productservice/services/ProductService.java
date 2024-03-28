package dev.krutik.productservice.services;

import dev.krutik.productservice.dtos.GenericProductDTO;
import dev.krutik.productservice.exception.NotFoundException;
import dev.krutik.productservice.models.Product;

import java.util.List;

public interface ProductService {
    GenericProductDTO getProductById(Long id) throws NotFoundException;
    GenericProductDTO createProduct(GenericProductDTO productDTO);

    List<GenericProductDTO> getAllProducts();

    GenericProductDTO updateProductById(Long id, GenericProductDTO genericProductDTO);

    GenericProductDTO deleteProduct(Long id);
}
