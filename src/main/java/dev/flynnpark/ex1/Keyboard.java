package dev.flynnpark.ex1;

import jakarta.persistence.Entity;

@Entity
public class Keyboard extends Item {
    private String layout;

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }
}
