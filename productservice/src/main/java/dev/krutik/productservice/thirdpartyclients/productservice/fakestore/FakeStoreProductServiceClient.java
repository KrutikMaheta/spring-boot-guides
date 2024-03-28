package dev.krutik.productservice.thirdpartyclients.productservice.fakestore;

import dev.krutik.productservice.dtos.GenericProductDTO;
import dev.krutik.productservice.exception.NotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FakeStoreProductServiceClient {
    private RestTemplateBuilder restTemplateBuilder;
    @Value("${fakestore.api.url}")
    private String fakeStoreApiUrl;
    @Value("${fakestore.api.paths.product}")
    private String fakeStoreProductsApiPath;
    private String specificProductRequestUrl;
    private String productRequestBaseUrl;

    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;

    }

    @PostConstruct
    public void init() {
        this.specificProductRequestUrl = fakeStoreApiUrl + fakeStoreProductsApiPath + "/{id}";
        this.productRequestBaseUrl = fakeStoreApiUrl + fakeStoreProductsApiPath;
    }

    public FakeStoreProductDTO createProduct(GenericProductDTO productDTO) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> response = restTemplate.postForEntity(productRequestBaseUrl, productDTO, FakeStoreProductDTO.class);
        return response.getBody();
    }

    public List<FakeStoreProductDTO> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();

        // This won't work because of the Type Erasure at runtime.
        // At runtime, java removes the template information.
        // ResponseEntity<List<FakeStoreProductDTO>> response = restTemplate.getForEntity(getProductByIdUrl, List<FakeStoreProductDTO>.class);

        ResponseEntity<FakeStoreProductDTO[]> response = restTemplate
                .getForEntity(productRequestBaseUrl, FakeStoreProductDTO[].class);

        // Another Way
        // ResponseEntity<List<GenericProductDTO>> response2 = restTemplate
        //             .exchange(productRequestBaseUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<GenericProductDTO>>() {});

        return Arrays.stream(response.getBody()).toList();
    }


    public FakeStoreProductDTO getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();

        /*
        Data coming from out of the system or going out of the system should always being transferred
        using DTO object, models are not usually good choice for this as models are usually
        associated with DB tables
         */
        ResponseEntity<FakeStoreProductDTO> response = restTemplate.getForEntity(specificProductRequestUrl, FakeStoreProductDTO.class, id);
        return response.getBody();


    }

    public FakeStoreProductDTO updateProductById(Long id, GenericProductDTO genericProductDTO) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        // Define URL parameters
        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(id));

        // Create the complete URL
        String url = specificProductRequestUrl.replace("{id}", params.get("id"));

        // Create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create HttpEntity
        HttpEntity<GenericProductDTO> requestEntity = new HttpEntity<>(genericProductDTO, headers);

        // Send PUT request
        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, FakeStoreProductDTO.class);

        return responseEntity.getBody();
    }

    public FakeStoreProductDTO deleteProduct(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDTO.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDTO>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDTO.class);
        ResponseEntity<FakeStoreProductDTO> response = restTemplate.execute(specificProductRequestUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);

        return response.getBody();
    }


}
