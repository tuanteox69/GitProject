package com.example.app_blog.repository;


import com.example.app_blog.model.Likes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ILikeRepo extends CrudRepository<Likes,Long> {
//    @Query(nativeQuery = true,value = "SELECT count(blog_id) FROM app_blog.Like WHERE blog_id=:id")
//@Query(nativeQuery = true,value = "SELECT * FROM blog_c0322g1.Likes where blogs_id =:id")
//
//List<Likes> findcountByIdBlog(@Param("id") Long id);
int countLikesByBlog_Id(int id);
}
