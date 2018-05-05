package br.com.app.offer_box.model;

/**
 * Created by matheuscatossi on 05/05/18.
 */

public class Tag {

    private boolean checked;
    private String description;

    public Tag(String description, boolean checked) {
        this.description = description;
        this.checked = checked;
    }

    public Tag() {

    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
