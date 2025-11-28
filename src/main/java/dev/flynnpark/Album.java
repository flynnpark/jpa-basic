package dev.flynnpark;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "album")
@DiscriminatorValue("album")
public class Album extends ProductItem {
    private String artist;
}
