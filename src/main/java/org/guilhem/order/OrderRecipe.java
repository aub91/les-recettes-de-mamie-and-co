package org.guilhem.order;

import java.util.List;

import org.jmolecules.event.types.DomainEvent;

public record OrderRecipe(String recipeId, List<String> ingredientList) implements DomainEvent {

}
