package org.guilhem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.guilhem.domain.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private DataSource dataSource;

    @RequestMapping("/")
    String index(Map<String, Object> model) {
        ObjectMapper mapper = new ObjectMapper();
        URL recipesUrl = this.getClass().getResource( "/data/recipe-list.json");
        List<Recipe> recipes = null;
        try {
            recipes = mapper.readValue(recipesUrl, mapper.getTypeFactory().constructCollectionType(List.class, Recipe.class));
            Collections.shuffle(recipes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        model.put("recipes", recipes);
        return "page-recette";
    }

    @RequestMapping("/recipe/{recipe-id}")
    @ResponseBody
    Recipe getRecipe(@PathVariable("recipe-id") String recipeId){
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

}
