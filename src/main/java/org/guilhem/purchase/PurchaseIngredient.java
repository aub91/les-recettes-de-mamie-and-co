package org.guilhem.purchase;

import java.util.UUID;

import org.jmolecules.event.types.DomainEvent;

public record PurchaseIngredient(UUID orderId, String ingredientName) implements DomainEvent {

}
