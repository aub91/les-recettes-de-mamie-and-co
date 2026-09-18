package org.guilhem;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

public class ModularityTest {
    @Test
    public void verifyModularStructure() {
        ApplicationModules modules = ApplicationModules.of(Main.class);
        modules.forEach(System.out::println);
        modules.verify();
    }
}
