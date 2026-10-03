package org.guilhem.recipe.service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.guilhem.order.OrderManagementAPI;
import org.guilhem.recipe.domain.IngredientList;
import org.guilhem.recipe.domain.Recipe;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RecipeService {
    private OrderManagementAPI orderManagement;

    public RecipeService(OrderManagementAPI orderManagement) {
        this.orderManagement = orderManagement;
    }
    public List<Recipe> getAll() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            URL recipesUrl = this.getClass().getResource( "/data/recipe-list.json");
            List<Recipe> recipes = mapper.readValue(recipesUrl, mapper.getTypeFactory().constructCollectionType(List.class, Recipe.class));
            Collections.shuffle(recipes);
            return recipes;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Recipe getById(String recipeId) {
        ObjectMapper mapper = new ObjectMapper();
        URL recipesUrl = this.getClass().getResource( "/data/recipe-list.json");
        List<Recipe> recipes = null;
        Recipe result = null;
        try {
            recipes = mapper.readValue(recipesUrl, mapper.getTypeFactory().constructCollectionType(List.class, Recipe.class));
            result = recipes.stream().filter(recipe -> recipe.getId().equals(recipeId)).findFirst().get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public UUID order(String recipeId) {
        ObjectMapper mapper = new ObjectMapper();
        URL recipesUrl = this.getClass().getResource( "/data/recipe-list.json");
        List<Recipe> recipes = null;
        Recipe recipe = null;
        try {
            recipes = mapper.readValue(recipesUrl, mapper.getTypeFactory().constructCollectionType(List.class, Recipe.class));
            recipe = recipes.stream().filter(recip -> recip.getId().equals(recipeId)).findFirst().get();

            return orderManagement.orderRecipe(
                recipe.getIngredientListCollection().stream()
                .map(IngredientList::getIngredients)
                .flatMap(Collection::stream).toList()
            );
        } catch (IOException e) {
            throw new RuntimeException("Failed to create order");
        }
    }
}
