package org.guilhem.purchase.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Purchase {
    private UUID id = UUID.randomUUID();

    private String ingredientName;

    private String provider;

    private Long price;

    private LocalDateTime date = LocalDateTime.now();

    public Purchase(String ingredientName, String provider, Long price) {
        this.ingredientName = ingredientName;
        this.provider = provider;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public String getProvider() {
        return provider;
    }

    public Long getPrice() {
        return price;
    }
}
