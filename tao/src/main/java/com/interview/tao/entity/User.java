package com.interview.tao.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity(name = "app_user")
public class User {

    @Id
    private UUID id;

    @Column
    private String username;
    @Column
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column
    @OneToMany(mappedBy = "user")
    private List<Inventory> inventories;

    public User(String username, String password, Role role)
    {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User() {
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public List<Inventory> getInventories() {
        return inventories;
    }
}
