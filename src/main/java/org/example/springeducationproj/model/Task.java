package org.example.springeducationproj.model;

import jakarta.persistence.*;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String discription;

    @Column(nullable = false)
    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    protected Task(){}
    public Task(String title,String discription,User user, Category category){
        this.title = title;
        this.user = user;
        this.category = category;
        this.discription = discription;
        this.completed = false;
    }

    public Long getId(){return this.id;}
    public String getTitle(){return this.title;}
    public String getDiscription(){return this.discription;}
    public boolean getCompleted(){return this.completed;}
    public User getUser(){return this.user;}
    public Category getCategory(){return this.category;}

    public void setTitle(String title){this.title = title;}
    public void setDiscription(String discription){this.discription=discription;}
    public void setCompleted(boolean completed) {this.completed = completed;}
    public void setCategory(Category category) {this.category = category;}
}
