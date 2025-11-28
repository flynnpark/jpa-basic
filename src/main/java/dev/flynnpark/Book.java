package dev.flynnpark;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "book")
@DiscriminatorValue("book")
public class Book  extends ProductItem {
    private String author;
}
