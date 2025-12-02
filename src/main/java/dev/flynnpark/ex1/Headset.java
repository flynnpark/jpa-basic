package dev.flynnpark.ex1;

import jakarta.persistence.Entity;

@Entity
public class Headset extends Item {
    private boolean hasMicrophone;

    public boolean isHasMicrophone() {
        return hasMicrophone;
    }

    public void setHasMicrophone(boolean hasMicrophone) {
        this.hasMicrophone = hasMicrophone;
    }
}
