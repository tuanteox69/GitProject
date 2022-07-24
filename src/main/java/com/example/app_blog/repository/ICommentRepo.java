package com.example.app_blog.repository;



import com.example.app_blog.model.Blog;
import com.example.app_blog.model.Comment;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ICommentRepo extends PagingAndSortingRepository<Comment,Long> {
    List<Comment> findByBlog(Blog blog);

}
