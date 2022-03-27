package com.example.travelmanage_spring.controllers.customer;

import com.example.travelmanage_spring.domain.Comment;
import com.example.travelmanage_spring.domain.Post;
import com.example.travelmanage_spring.services.CommentService;
import com.example.travelmanage_spring.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("post")
public class PostController {
    @Autowired
    PostService postService;

    @Autowired
    CommentService commentService;

    @GetMapping("/")
    public ModelAndView get(){
        ModelAndView mv = new ModelAndView("public/list-post");
        List<Post> list = postService.findAll();
        mv.addObject("listPost",list);
        mv.addObject("activePost","activePost");
        return mv;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable int id, Model model){
        ModelAndView mv = new ModelAndView("public/detail-post");
        Post obj = postService.findPostById(id);
        List<Comment> listComment = commentService.findCommentByPost(obj);
        mv.addObject("post",obj);
        mv.addObject("listComment",listComment);
        return mv;
    }
}
