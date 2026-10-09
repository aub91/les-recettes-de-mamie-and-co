package org.guilhem.order.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.guilhem.order.OrderCompleted;
import org.guilhem.order.OrderRecipe;
import org.guilhem.order.domain.Order;
import org.guilhem.purchase.PurchaseCompleted;
import org.guilhem.purchase.PurchaseIngredient;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;

@Service
public class OrderManagement {

    ApplicationEventPublisher eventPublisher;

    private Map<UUID, Order> orderMap = new HashMap<>();

    public OrderManagement(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @ApplicationModuleListener
    public void on(OrderRecipe orderRecipe){
        Order order = new Order(orderRecipe.recipeId());

        orderMap.put(order.getId(), order);

        orderRecipe.ingredientList().forEach(ingredient -> {
            order.addIngredient(ingredient);
        });

        for (String ingredient : orderRecipe.ingredientList()) {
            eventPublisher.publishEvent(new PurchaseIngredient(order.getId(), ingredient));
        }
    }

    @ApplicationModuleListener 
    public void on(PurchaseCompleted purchaseCompleted) {
        Order order = orderMap.get(purchaseCompleted.orderId());
        order.getIngredientMap().put(purchaseCompleted.ingredientId(), purchaseCompleted.price());
        if(order.isCompleted()) {
            eventPublisher.publishEvent(new OrderCompleted(order.getRecipeId(), order.getId()));
        }
    }

    public Order getOrder(UUID orderId) {
        return orderMap.get(orderId);
    }
}
