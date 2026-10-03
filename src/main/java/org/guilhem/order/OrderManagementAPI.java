package org.guilhem.order;

import java.util.List;
import java.util.UUID;

import org.guilhem.order.domain.Order;

public interface OrderManagementAPI {
    UUID orderRecipe(List<String> ingredientList);

    Order getOrder(UUID orderId);
}
