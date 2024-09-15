package com.interview.tao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.UUID;

@Entity(name = "book")
public class Book {

    @Id
    private UUID id;

    @Column
    private String author;

    @Column
    private String title;

    @Column
    private String image;

    @Column
    @OneToMany(mappedBy = "book")
    private List<Inventory> inventories;

    public Book(UUID id, String author, String title, String image, List<Inventory> inventories) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.image = image;
        this.inventories = inventories;
    }

    public Book() {
    }

    public UUID getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public List<Inventory> getInventories() {
        return inventories;
    }
}
