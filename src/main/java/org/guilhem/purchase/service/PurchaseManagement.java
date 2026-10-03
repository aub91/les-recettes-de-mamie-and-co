package org.guilhem.purchase.service;

import java.rmi.server.UID;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.guilhem.purchase.domain.Purchase;
import org.guilhem.purchase.dto.PurchaseDto;
import org.springframework.stereotype.Service;

@Service 
public class PurchaseManagement {
    Map<UUID, Purchase> purchaseMap = new HashMap<>();

    List<String> providers = List.of("Amazon", "Auchan", "Leclerc", "Carrefour");

    public UUID purchaseIngredient(String ingredientName) {
        Collections.shuffle(providers);
        String randomProvider = providers.get(0);
        Purchase purchase = new Purchase(ingredientName, randomProvider, getRandomPrice());

        purchaseMap.put(purchase.getId(), purchase);

        System.out.println("Purchase made: " + purchase);
        
        return purchase.getId();
    }

    public PurchaseDto getPurchase(UUID purchaseId) {
        Purchase purchase = purchaseMap.get(purchaseId);
        return new PurchaseDto(purchase.getId(), purchase.getIngredientName(), purchase.getProvider(), purchase.getPrice());
    }

    private long getRandomPrice() {
        long leftLimit = 1L;
        long rightLimit = 10L;
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }
}
