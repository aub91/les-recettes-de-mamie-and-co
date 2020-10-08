package org.guilhem.domain;

public enum RecipeFamily {
    BOUVIER ("Bouvier"),
    ALCOVER ("Alcover"),
    MAMIE ("Mamie"),
    VANCAPP ("Vancapp")
    ;

    private String label;

    RecipeFamily(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
