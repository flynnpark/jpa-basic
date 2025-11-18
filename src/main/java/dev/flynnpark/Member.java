package dev.flynnpark;

import jakarta.persistence.*;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "NAME_AGE_UNIQUE", columnNames = {"name", "age"})})
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name", nullable = false, length = 10)
    private String name;

    public Member() {
    }

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String username) {
        this.name = username;
    }
}
