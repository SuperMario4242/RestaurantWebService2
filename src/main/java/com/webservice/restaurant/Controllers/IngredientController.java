package com.webservice.restaurant.Controllers;

import com.webservice.restaurant.DTO.IngredientDTO;
import com.webservice.restaurant.Entities.Dish;
import com.webservice.restaurant.Entities.Ingredient;
import com.webservice.restaurant.Services.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IngredientController {
    private IngredientService ingredientService;
    @GetMapping("/ingredients")
    public List<String> getAllIngredients(){
        return ingredientService.getAllIngredients();

    }
    @PostMapping("/ingredients")
    public void addIngredient(@RequestBody IngredientDTO ingredientDTO) {
        ingredientService.postIngredient(ingredientDTO);
    }

    @GetMapping("/ingredients/{id}")
    public String getIngredientById(@PathVariable Long id){

        return ingredientService.getIngredientById(id);
    }

    @PutMapping("/ingredients/{id}")
    public void updateIngredient(@PathVariable Long id, @RequestBody IngredientDTO ingredientDTO) {
        ingredientService.updateIngredient(id, ingredientDTO);
    }

    @DeleteMapping("/ingredients/{id}")

    public void deleteIngredien(@PathVariable Long id){
        ingredientService.deleteIngredient(id);


    }
}
