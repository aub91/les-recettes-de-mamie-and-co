package org.guilhem.recipe.domain;

public enum RecipeCategory {
    PLAT_PRINCIPAL ("Plat principal"),
    DESSERT ("Dessert"),
    SAUCE ("sauce")
    ;

    private String label;

    RecipeCategory(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
