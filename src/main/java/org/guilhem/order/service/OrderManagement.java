package org.guilhem.order.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.guilhem.order.domain.Order;
import org.guilhem.purchase.PurchaseManagementAPI;
import org.guilhem.purchase.dto.PurchaseDto;
import org.springframework.stereotype.Service;

@Service
public class OrderManagement {

    private PurchaseManagementAPI purchaseManagementAPI;

    private Map<UUID, Order> orderMap = new HashMap<>();

    public OrderManagement(PurchaseManagementAPI purchaseManagementAPI) {
        this.purchaseManagementAPI = purchaseManagementAPI;
    }

    public UUID orderRecipe(List<String> ingredientList){
        Order order = new Order();

        ingredientList.forEach(ingredient -> {
            order.addIngredient(ingredient);
            UUID purchaseId = purchaseManagementAPI.purchaseIngredient(ingredient);
            PurchaseDto purchase = purchaseManagementAPI.getPurchase(purchaseId);
            order.setTotalPrice(order.getTotalPrice() + purchase.price());
        });

        orderMap.put(order.getId(), order);

        return order.getId();
    }

    public Order getOrder(UUID orderId) {
        return orderMap.get(orderId);
    }
}
