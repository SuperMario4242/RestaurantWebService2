package com.webservice.restaurant.Services;


import com.webservice.restaurant.DTO.IngredientDTO;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service
public class IngredientService {

    private static final String INGREDIENT_SERVICE_URL = "http://localhost:5000/ingredients";

    private final RestTemplate restTemplate;

    public IngredientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public List<String> getAllIngredients() {
        // Build URL for fetching all ingredients
        String url = UriComponentsBuilder.fromHttpUrl(INGREDIENT_SERVICE_URL)
                .path("/ingredients")
                .build()
                .toUriString();

        // Fetch ingredients from the external service
        String[] ingredients = restTemplate.getForObject(url, String[].class);

        // Convert array to list and return
        assert ingredients != null;
        return Arrays.asList(ingredients);
    }

    public String getIngredientById(Long ingredientId) {
        String url = UriComponentsBuilder.fromHttpUrl(INGREDIENT_SERVICE_URL)
                .path("/ingredients/{id}")
                .buildAndExpand(ingredientId)
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        return responseEntity.getBody();
    }

    public void postIngredient(IngredientDTO ingredientDTO) {
        String url = UriComponentsBuilder.fromHttpUrl(INGREDIENT_SERVICE_URL)
                .path("/ingredients")
                .build()
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Include generated ID in the request body
        String requestBody = "{\"id\": " + ingredientDTO.getId() + ", \"name\": \"" + ingredientDTO.getName() + "\", \"amount\": " + ingredientDTO.getAmount() + "}";

        // Create HTTP entity
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // Make POST request
        restTemplate.postForObject(url, requestEntity, String.class);
    }


    public void deleteIngredient(Long ingredientId) {
        String url = UriComponentsBuilder.fromHttpUrl(INGREDIENT_SERVICE_URL)
                .path("/ingredients/{id}")
                .buildAndExpand(ingredientId)
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);

        restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class);
    }

    public void updateIngredient(Long ingredientId, IngredientDTO ingredientDTO) {
        String url = UriComponentsBuilder.fromHttpUrl(INGREDIENT_SERVICE_URL)
                .path("/ingredients/{id}")
                .buildAndExpand(ingredientId)
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestBody = "{\"name\": \"" + ingredientDTO.getName() + "\", \"amount\": " + ingredientDTO.getAmount() + "}";
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
    }
}
