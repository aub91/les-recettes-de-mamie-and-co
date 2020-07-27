package org.guilhem.domain;

/**
 * A video file of a recipe.
 */
public class Video {

    /**
     * Title of the video
     */
    private String title;

    /**
     * Path of the video.
     */
    private String src;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
