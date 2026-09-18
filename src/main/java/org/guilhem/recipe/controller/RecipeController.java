package org.guilhem.recipe.controller;

import org.guilhem.recipe.domain.Recipe;
import org.guilhem.recipe.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/")
    String index(Map<String, Object> model) {
        List<Recipe> recipes = recipeService.getAll();
        model.put("recipes", recipes);
        return "page-recette";
    }

    @RequestMapping("/recipe/{recipe-id}")
    @ResponseBody
    Recipe getRecipe(@PathVariable("recipe-id") String recipeId){
        return recipeService.getById(recipeId);
    }

}
