package org.guilhem.order;

import java.util.UUID;

import org.jmolecules.event.types.DomainEvent;

public record OrderCompleted(String recipeId, UUID orderId) implements DomainEvent{

}
