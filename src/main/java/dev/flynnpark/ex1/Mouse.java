package dev.flynnpark.ex1;

import jakarta.persistence.Entity;

@Entity
public class Mouse extends Item {
    private int dpi;

    public int getDpi() {
        return dpi;
    }

    public void setDpi(int dpi) {
        this.dpi = dpi;
    }
}
