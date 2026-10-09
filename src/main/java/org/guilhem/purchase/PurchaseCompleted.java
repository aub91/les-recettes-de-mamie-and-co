package org.guilhem.purchase;

import java.util.UUID;

import org.jmolecules.event.types.DomainEvent;

public record PurchaseCompleted(UUID orderId, String ingredientId, Long price) implements DomainEvent{

}
