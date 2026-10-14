package org.guilhem;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

public class DocumentationTests {
    ApplicationModules modules = ApplicationModules.of(Main.class);

    @Test
    void writeDocumentationSnippets() {
        new Documenter(modules)
        .writeModulesAsPlantUml()
        .writeIndividualModulesAsPlantUml()
        .writeModuleCanvases()
        .writeAggregatingDocument();
    }
}
