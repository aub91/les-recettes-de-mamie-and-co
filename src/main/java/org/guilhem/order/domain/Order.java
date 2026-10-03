package org.guilhem.order.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {
    private UUID id = UUID.randomUUID();

    private LocalDateTime date = LocalDateTime.now();

    private List<String> ingredientList = new ArrayList<>();

    private Long totalPrice;

    public void addIngredient(String ingredient) {
        this.ingredientList.add(ingredient);
    }

    public void addIngredients(List<String> ingredients) {
        this.ingredientList.addAll(ingredients);
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long price) {
        this.totalPrice = price;
    }

    public UUID getId() {
        return id;
    }
}
