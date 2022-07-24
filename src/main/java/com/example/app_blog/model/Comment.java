package com.example.app_blog.model;

import javax.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nameuser;
    private String email;
    private String contentComment;
    private String date ;
     @ManyToOne
    private Blog blog;

    public Comment(Blog blog) {
        this.blog = blog;
    }
    public Comment(long id, String nameuser, String email, String contentComment, String date, Blog blog) {
        this.id = id;
        this.nameuser = nameuser;
        this.email = email;
        this.contentComment = contentComment;
        this.date = date;
        this.blog = blog;
    }

    public Comment(long id, String nameuser, String email, String commemt, Blog blog) {
        this.id = id;
        this.nameuser = nameuser;
        this.email = email;
        this.contentComment = commemt;
        this.blog = blog;
    }

    public Comment() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameuser() {
        return nameuser;
    }

    public void setNameuser(String nameuser) {
        this.nameuser = nameuser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContentComment() {
        return contentComment;
    }

    public void setContentComment(String commemt) {
        this.contentComment = commemt;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }
}
