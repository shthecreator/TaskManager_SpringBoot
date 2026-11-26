package org.example.springeducationproj.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, length = 100)
    String name;

    @Column(nullable = false, unique = true, length = 100)
    String email;

    protected User(){}
    public User(String name, String email){this.name = name; this.email = email;}

    public Long getId(){return this.id;}
    public String getName(){return this.name;}
    public String getEmail(){return email;}

    public void setName(String name){this.name = name;}
    public void setEmail(String email){this.email = email;}
}
