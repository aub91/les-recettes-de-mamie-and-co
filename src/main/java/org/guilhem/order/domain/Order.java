package org.guilhem.order.domain;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Order {
    private UUID id = UUID.randomUUID();

    private LocalDateTime date = LocalDateTime.now();

    private Map<String, Long> ingredientMap = new HashMap<>();

    private String recipeId;

    public Order(String recipeId) {
        this.recipeId = recipeId;
    }

    public void addIngredient(String ingredient) {
        this.ingredientMap.put(ingredient, null);
    }

    public Map<String, Long> getIngredientMap() {
        return ingredientMap;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public Long getTotalPrice() {
        return this.ingredientMap.values().stream().mapToLong(Long::longValue).sum();
    }

    public UUID getId() {
        return id;
    }

    public boolean isCompleted() {
        return this.ingredientMap.values().stream().noneMatch(price -> price == null);
    }
}
