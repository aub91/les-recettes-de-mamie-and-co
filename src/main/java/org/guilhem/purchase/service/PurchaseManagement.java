package org.guilhem.purchase.service;

import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.guilhem.purchase.PurchaseCompleted;
import org.guilhem.purchase.PurchaseIngredient;
import org.guilhem.purchase.domain.Purchase;
import org.guilhem.purchase.dto.PurchaseDto;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;

@Service 
public class PurchaseManagement {
    private ApplicationEventPublisher eventPublisher;

    Map<UUID, Purchase> purchaseMap = new HashMap<>();

    List<String> providers = List.of("Amazon", "Auchan", "Leclerc", "Carrefour");

    public PurchaseManagement(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @ApplicationModuleListener 
    public void on(PurchaseIngredient purchaseIngredient) {
        Collections.shuffle(new ArrayList<>(providers));
        String randomProvider = providers.get(0);
        Purchase purchase = new Purchase(purchaseIngredient.ingredientName(), randomProvider, getRandomPrice());

        purchaseMap.put(purchase.getId(), purchase);

        System.out.println(String.format("Purchase made: %s, %s, %d", purchase.getIngredientName(), purchase.getProvider(), purchase.getPrice()));
        
        eventPublisher.publishEvent(new PurchaseCompleted(purchaseIngredient.orderId(), purchaseIngredient.ingredientName(), purchase.getPrice()));
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
