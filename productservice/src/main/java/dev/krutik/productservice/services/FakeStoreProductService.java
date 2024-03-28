package dev.krutik.productservice.services;

import dev.krutik.productservice.dtos.GenericProductDTO;
import dev.krutik.productservice.exception.NotFoundException;
import dev.krutik.productservice.thirdpartyclients.productservice.fakestore.FakeStoreProductDTO;
import dev.krutik.productservice.thirdpartyclients.productservice.fakestore.FakeStoreProductServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {

    private FakeStoreProductServiceClient fakeStoreProductServiceClient;

    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient) {
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO productDTO) {
        return convertToGenericDto(fakeStoreProductServiceClient.createProduct(productDTO));
    }

    @Override
    public List<GenericProductDTO> getAllProducts() {
        List<GenericProductDTO> result = new ArrayList<>();
        List<FakeStoreProductDTO> fakeStoreProductDTOS = fakeStoreProductServiceClient.getAllProducts();

        // @formatter:off
        return fakeStoreProductDTOS
                .stream()
                .map(fakeStoreProductDTO -> convertToGenericDto(fakeStoreProductDTO))
                .collect(Collectors.toList());
        // @formatter:on
    }

    @Override
    public GenericProductDTO getProductById(Long id) throws NotFoundException {

        // Have one DTO for data coming from out of the system.
        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductServiceClient.getProductById(id);

        if (fakeStoreProductDTO == null) {
            throw new NotFoundException("Product with id: " + id + " doesn't exist.");
        }
        // Have a separate DTO for our application's codebase.
        return convertToGenericDto(fakeStoreProductDTO);
    }

    @Override
    public GenericProductDTO updateProductById(Long id, GenericProductDTO genericProductDTO) {
        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductServiceClient.updateProductById(id, genericProductDTO);
        return convertToGenericDto(fakeStoreProductDTO);
    }

    @Override
    public GenericProductDTO deleteProduct(Long id) {
        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductServiceClient.deleteProduct(id);
        return convertToGenericDto(fakeStoreProductDTO);
    }

    private static GenericProductDTO convertToGenericDto(FakeStoreProductDTO fakeStoreProductDTO) {
        GenericProductDTO genericProductDTO = new GenericProductDTO();
        genericProductDTO.setId(fakeStoreProductDTO.getId());
        genericProductDTO.setImage(fakeStoreProductDTO.getImage());
        genericProductDTO.setDescription(fakeStoreProductDTO.getDescription());
        genericProductDTO.setTitle(fakeStoreProductDTO.getTitle());
        genericProductDTO.setPrice(fakeStoreProductDTO.getPrice());
        genericProductDTO.setCategory(fakeStoreProductDTO.getCategory());
        return genericProductDTO;
    }
}
