package org.guilhem.recipe.service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.guilhem.order.OrderCompleted;
import org.guilhem.order.OrderRecipe;
import org.guilhem.recipe.domain.IngredientList;
import org.guilhem.recipe.domain.Recipe;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RecipeService {
    private ApplicationEventPublisher eventPublisher;

    public RecipeService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
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

    @Transactional 
    public void order(String recipeId) {
        ObjectMapper mapper = new ObjectMapper();
        URL recipesUrl = this.getClass().getResource( "/data/recipe-list.json");
        List<Recipe> recipes = null;
        Recipe recipe = null;
        try {
            recipes = mapper.readValue(recipesUrl, mapper.getTypeFactory().constructCollectionType(List.class, Recipe.class));
            recipe = recipes.stream().filter(recip -> recip.getId().equals(recipeId)).findFirst().get();

            eventPublisher.publishEvent(
                new OrderRecipe(
                    recipe.getId(), 
                    recipe.getIngredientListCollection().stream()
                        .map(IngredientList::getIngredients)
                        .flatMap(Collection::stream).toList()
                ));
        } catch (IOException e) {
            throw new RuntimeException("Failed to create order");
        }
    }

    @ApplicationModuleListener 
    public void on(OrderCompleted orderCompleted) {
        System.out.println(String.format("Order completed for recipe %s: %s", orderCompleted.recipeId(), orderCompleted.orderId()));
    }
}
