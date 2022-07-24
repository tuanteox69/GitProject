package com.example.app_blog.service;

import com.example.app_blog.model.Blog;
import com.example.app_blog.model.Comment;
import com.example.app_blog.repository.IBlogRepo;
import com.example.app_blog.repository.ICommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CommentService {
    @Autowired
    ICommentRepo iCommentRepo;

    public List<Comment> getAll(Blog blog) {
        return iCommentRepo.findByBlog(blog);
    }

    public void save(Comment comment) {
        iCommentRepo.save(comment);
    }
}

