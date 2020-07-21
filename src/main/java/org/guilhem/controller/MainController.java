package org.guilhem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.guilhem.domain.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
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
        } catch (IOException e) {
            e.printStackTrace();
        }

        model.put("recipes", recipes);
        return "page-recette";
    }

    @RequestMapping("/db")
    String db(Map<String, Object> model) {
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
            stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
            ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

            ArrayList<String> output = new ArrayList<String>();
            while (rs.next()) {
                output.add("Read from DB: " + rs.getTimestamp("tick"));
            }

            model.put("records", output);
            return "db";
        } catch (Exception e) {
            model.put("message", e.getMessage());
            return "error";
        }
    }
}
