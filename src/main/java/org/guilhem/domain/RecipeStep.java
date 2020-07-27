package org.guilhem.domain;

/**
 * A recipe's step
 */
public class RecipeStep {

    /**
     * Path to the image of the step. If there is an image, there is no video.
     */
    private String image;

    /**
     * Path to a video of the step. If there is a video, there is no image.
     */
    private String video;

    /**
     * Text of the step.
     */
    private String text;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
