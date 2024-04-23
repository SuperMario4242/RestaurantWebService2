package com.webservice.restaurant.Repositories;

import com.webservice.restaurant.Entities.Dish;
import com.webservice.restaurant.Entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {


}
