package com.example.app_blog.repository;


import com.example.app_blog.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface IBlogRepo extends PagingAndSortingRepository<Blog, Integer> {
    Page<Blog> findAllByTitleContaining(String pageable, Pageable title);
    Optional<Blog> findByTitle(String title);
}
