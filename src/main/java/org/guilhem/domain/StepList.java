package org.guilhem.domain;

import java.util.List;

public class StepList {
    /**
     * Title of the list
     */
    private String title;

    /**
     * The list of steps.
     */
    private List<RecipeStep> steps;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<RecipeStep> getSteps() {
        return steps;
    }

    public void setSteps(List<RecipeStep> steps) {
        this.steps = steps;
    }
}
