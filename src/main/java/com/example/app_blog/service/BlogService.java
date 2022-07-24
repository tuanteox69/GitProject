package com.example.app_blog.service;


import com.example.app_blog.model.Blog;
import com.example.app_blog.repository.IBlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class BlogService {
    @Autowired
    IBlogRepo iBlogRepo;

    public Page<Blog> getAll(Pageable pageable) {
        return  iBlogRepo.findAll(pageable);
    }
    public void save(Blog blog) {
        iBlogRepo.save(blog);
    }

    public void delete(int id) {
        iBlogRepo.deleteById(id);
    }

    public Optional<Blog> findById(int id) {
        return iBlogRepo.findById(id);
    }

    public Page<Blog> findAllByTitleContaining(String s, Pageable pageable) {
        return iBlogRepo.findAllByTitleContaining(s,pageable);
    }
    public Optional<Blog> findByTitle(String title) {
        return iBlogRepo.findByTitle(title);
    }
}

