package com.interview.tao.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "inventory")
public class Inventory {

    @Id
    private UUID id;

    @JoinColumn(name = "bookId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;

    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column
    @CreatedDate
    private LocalDateTime loanDate;

    public Inventory(Book book, User user) {
        this.book = book;
        this.user = user;
    }

    public Inventory() {
    }

    public UUID getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDateTime loanDate) {
        this.loanDate = loanDate;
    }
}
