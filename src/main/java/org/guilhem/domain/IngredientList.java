package org.guilhem.domain;

import java.util.List;

/**
 * A list of ingredients.
 */
public class IngredientList {

    /**
     * Title of the list
     */
    private String title;

    /**
     * The lsit of ingredients.
     */
    private List<String> ingredients;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}
