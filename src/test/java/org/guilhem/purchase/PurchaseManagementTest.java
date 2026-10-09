package org.guilhem.purchase;

import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.Scenario;

@ApplicationModuleTest 
public class PurchaseManagementTest {

    @Test
    public void shouldPurchaseIngredient(Scenario scenario) {
        scenario
            .publish(new PurchaseIngredient(UUID.randomUUID(), "Bacon"))
            .andWaitForEventOfType(PurchaseCompleted.class)
            .toArriveAndVerify(event -> {
                Assertions.assertEquals(event.ingredientId(), "Bacon");
                Assertions.assertTrue(event.price() >=1 && event.price() <=10);
            });
    }
}
