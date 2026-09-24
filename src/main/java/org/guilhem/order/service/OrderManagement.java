package org.guilhem.order.service;

import java.util.HashMap;
import java.util.Map;

import org.guilhem.order.domain.Order;
import org.guilhem.recipe.domain.Recipe;
import org.springframework.stereotype.Service;

@Service
public class OrderManagement {
    Map<Long, Order> orderMap = new HashMap<>();

    public Order orderRecipe(Recipe recipe){
        Order order = new Order();

        recipe.getIngredientListCollection().forEach(ingredientList -> {
            order.addIngredients(ingredientList.getIngredients());
        });

        return order;
    }
}
