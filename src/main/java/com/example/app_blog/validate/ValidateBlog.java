package com.example.app_blog.validate;

import com.example.app_blog.model.Blog;
import com.example.app_blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class ValidateBlog implements Validator {
    @Autowired
    BlogService blogService;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Blog blog = (Blog) target;
        Optional<Blog> optional = blogService.findByTitle(blog.getTitle());
        if (optional.isPresent()){
            errors.rejectValue("title", "", "Trung title roi nhe");
        }
        if(blog.getContent().equals("Hoàng")){
            errors.rejectValue("content", "", "Content bị cấm");

        }
    }
}
