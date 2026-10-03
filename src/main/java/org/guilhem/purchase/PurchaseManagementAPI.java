package org.guilhem.purchase;

import java.util.UUID;

import org.guilhem.purchase.dto.PurchaseDto;

public interface PurchaseManagementAPI {
    UUID purchaseIngredient(String ingredientName);
    PurchaseDto getPurchase(UUID purchaseId);
}
