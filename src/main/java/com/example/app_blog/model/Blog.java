package com.example.app_blog.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    @Size(min = 3,message = "Min 3")

    private String title;
    @NotNull(message = "Null roi")
    @Column(nullable = false,columnDefinition = "toan")
    private String content;

    private String img;

    private String date ;
    @ManyToOne
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    //    @OneToMany
//    private Collection<Comment> commentCollection;

    public Blog(Category category) {
        this.category = category;
    }

    public Blog(int id, String title, String content, String img, String date, Category category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.img = img;
        this.date = date;
        this.category = category;
    }

    public Blog() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle( String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent( String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public LocalDateTime getDate() {
        return LocalDateTime.now();
    }

    public void setDate(String date) {
        this.date = date;
    }
}
