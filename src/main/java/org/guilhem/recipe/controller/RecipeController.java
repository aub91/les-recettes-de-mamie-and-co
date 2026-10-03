package org.guilhem.recipe.controller;

import org.guilhem.order.domain.Order;
import org.guilhem.recipe.domain.Recipe;
import org.guilhem.recipe.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/")
    String index(Map<String, Object> model) {
        List<Recipe> recipes = recipeService.getAll();
        model.put("recipes", recipes);
        return "page-recette";
    }

    @GetMapping("/recipe/{recipe-id}")
    @ResponseBody
    Recipe getRecipe(@PathVariable("recipe-id") String recipeId){
        return recipeService.getById(recipeId);
    }

    @PostMapping("/recipe/{recipe-id}/order")
    @ResponseBody
    UUID orderRecipe(@PathVariable("recipe-id") String recipeId){
        return recipeService.order(recipeId);
    }

}
