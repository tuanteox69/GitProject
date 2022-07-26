package com.example.app_blog.service;

import com.example.app_blog.model.Likes;
import com.example.app_blog.repository.ILikeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {
    @Autowired
    ILikeRepo iLikeRepo;
//    public List<Likes> findcountByIdBlog(Long id){
//        return iLikeRepo.findcountByIdBlog(id);
//    }
    public void save(Likes like){
        iLikeRepo.save(like);
    }
    public int getsizelike(int id){
        return iLikeRepo.countLikesByBlog_Id(id);
    }
}
