package org.guilhem.purchase.dto;
import java.util.UUID;

public record PurchaseDto(UUID id, String ingredientName, String provider, Long price) {

}
