package com.example.app_blog.controller;

import com.example.app_blog.model.Blog;
import com.example.app_blog.model.Category;
import com.example.app_blog.model.Comment;
import com.example.app_blog.model.Likes;
import com.example.app_blog.service.BlogService;
import com.example.app_blog.service.CategoryService;
import com.example.app_blog.service.CommentService;
import com.example.app_blog.service.LikeService;
import com.example.app_blog.validate.ValidateBlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

import java.util.List;
import java.util.Optional;

@Controller
public class BlogController {
    @Autowired
    BlogService blogService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    CommentService commentService;
    @Autowired
    ValidateBlog validateBlog;
    @Autowired
    LikeService likeService;
    @ModelAttribute(name = "category")
    public List<Category> categories(){
        return categoryService.getAll();
    }
    @ModelAttribute(name = "blog")
    public Blog blog(){return new Blog();}
    @GetMapping("/erro")
    public ModelAndView Erro() {
        return new ModelAndView("Erro404");
    }

    @GetMapping("/blogs")
    public ModelAndView showblog(@RequestParam int id , Model model) {
        ModelAndView modelAndView = new ModelAndView("post");
        Optional<Blog> optional = blogService.findById(id);
        if (optional.isPresent()) {
            List<Comment> comments = commentService.getAll(optional.get());
            modelAndView.addObject("likes",likeService.getsizelike(id));
            model.addAttribute("comment",comments);
                modelAndView.addObject("blogs", optional.get());
        } else {
            modelAndView.addObject("erro");
        }
        return modelAndView;
    }
        @PostMapping("/blogs")
        public ModelAndView comment(@RequestParam int id, @ModelAttribute("blog") Blog blog, @RequestParam("nameuser") String nameuser,
                              @RequestParam("contentComment")String contentComment) {

            Optional<Blog> optional = blogService.findById(id);
            Comment comment = new Comment();
            comment.setBlog(blog);
            comment.setNameuser(nameuser);
            comment.setDate(java.time.LocalDateTime.now() + "");
            comment.setContentComment(contentComment);
            commentService.save(comment);
            ModelAndView modelAndView = new ModelAndView("post");
            modelAndView.addObject("comment",commentService.getAll(optional.get()));
            modelAndView.addObject("blogs", optional.get());
            return modelAndView;

        }
//    public ModelAndView showblog() {
//        ModelAndView modelAndView = new ModelAndView("post");
//        modelAndView.addObject("blogs", blogService.getAll());
//        return modelAndView;


    @GetMapping("/about")
    public ModelAndView showCreate() {
        return new ModelAndView("about");
    }

    @PostMapping("/about")
    public ModelAndView create(@Valid @ModelAttribute("blog") Blog blog, @RequestParam MultipartFile file, BindingResult bindingResult) {
//        validateBlog.validate(blog,bindingResult);
//        if (bindingResult.hasFieldErrors()) {
//            return new ModelAndView("redirect:/about");
//        }
        String nameImg = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(), new File("C:\\Module_4\\App_Blog\\src\\main\\webapp\\WEB-INF\\views\\img\\" + nameImg));
        } catch (IOException e) {
            e.printStackTrace();
        }
        blog.setImg("/img/" + nameImg);
        blog.setDate(java.time.LocalDateTime.now() + "");

        blogService.save(blog);
        return new ModelAndView("redirect:/live");
    }
    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam int id){
        ModelAndView modelAndView = new ModelAndView("redirect:/live");
        blogService.delete(id);
        return modelAndView;
    }

    @GetMapping("/edit")
    public ModelAndView showEdit(@RequestParam int id){
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("blog", blogService.findById(id));
        modelAndView.addObject("category", categoryService.getAll());
        return modelAndView;
    }

    @PostMapping ("/edit")
    public ModelAndView edit(@ModelAttribute("blog") Blog blog,
                             @RequestParam MultipartFile file,@RequestParam int id){
        Optional<Blog> optional = blogService.findById(id);
        String nameImg = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(), new File("C:\\Module_4\\App_Blog\\src\\main\\webapp\\WEB-INF\\views\\img\\" + nameImg));
        } catch (IOException e) {
            e.printStackTrace();
        }
        blog.setImg("/img/" + nameImg);
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("post");
        modelAndView.addObject("comment",commentService.getAll(optional.get()));
        modelAndView.addObject("category", categoryService.getAll());
        modelAndView.addObject("blogs", blog);
        return modelAndView;
    }
    @GetMapping("/live")
    public ModelAndView listCustomers(@RequestParam("search") Optional<String> search, Pageable pageable,@RequestParam(defaultValue = "0") int page){
        Page<Blog> blogs;
        if(search.isPresent()){
            blogs = blogService.findAllByTitleContaining(search.get(), pageable);
        } else {
            blogs = blogService.getAll((PageRequest.of(page,5, Sort.by(Sort.Direction.DESC,"date"))));
        }
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("likeservice",likeService);
        modelAndView.addObject("commentService",commentService);
        modelAndView.addObject("blogs",blogs);
        return modelAndView;
    }
    @GetMapping("/like/{id}")
    public ModelAndView like(Likes like, @PathVariable("id") int id) {
        Optional<Blog> optional = blogService.findById(id);
        like.setBlog(optional.get());
        likeService.save(like);
        return new ModelAndView("redirect:/blogs?id="+id);
    }
}
