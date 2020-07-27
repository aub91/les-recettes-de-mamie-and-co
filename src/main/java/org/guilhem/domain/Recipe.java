package org.guilhem.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Recipe {

    private String id;

    private String title;

    private String image;

    private RecipeCategory category;

    private RecipeFamily family;

    private List<String> tags;

    @JsonProperty("recap-sheet")
    private String recapSheet;

    @JsonProperty("ingredients-lists")
    private List<IngredientList> ingredientListCollection;

    private String quantite;

    private List<RecipeStep> steps;

    @JsonProperty("chef_word")
    private String chefWord;

    private List<Video> videos;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public RecipeCategory getCategory() {
        return category;
    }

    public void setCategory(RecipeCategory category) {
        this.category = category;
    }

    public RecipeFamily getFamily() {
        return family;
    }

    public void setFamily(RecipeFamily family) {
        this.family = family;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getRecapSheet() {
        return recapSheet;
    }

    public void setRecapSheet(String recapSheet) {
        this.recapSheet = recapSheet;
    }

    public List<IngredientList> getIngredientListCollection() {
        return ingredientListCollection;
    }

    public void setIngredientListCollection(List<IngredientList> ingredientListCollection) {
        this.ingredientListCollection = ingredientListCollection;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public List<RecipeStep> getSteps() {
        return steps;
    }

    public void setSteps(List<RecipeStep> steps) {
        this.steps = steps;
    }

    public String getChefWord() {
        return chefWord;
    }

    public void setChefWord(String chefWord) {
        this.chefWord = chefWord;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }
}
