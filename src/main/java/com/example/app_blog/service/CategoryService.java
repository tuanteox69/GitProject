package com.example.app_blog.service;


import com.example.app_blog.model.Category;
import com.example.app_blog.repository.ICategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    ICategoryRepo iCategoryRepo;
    public List<Category> getAll(){
        return (List<Category>) iCategoryRepo.findAll();
    }
    public Category findById(int id){
        return iCategoryRepo.findById(id).get();
    }
}
