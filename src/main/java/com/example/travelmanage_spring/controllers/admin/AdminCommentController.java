package com.example.travelmanage_spring.controllers.admin;

import com.example.travelmanage_spring.domain.Booking;
import com.example.travelmanage_spring.domain.Comment;
import com.example.travelmanage_spring.serviceImpl.BookingServiceImpl;
import com.example.travelmanage_spring.serviceImpl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminCommentController {
    @Autowired
    CommentServiceImpl commentService;

    @GetMapping({ "/comment"})
    public ModelAndView index(String msg)
    {
        List<Comment> list = commentService.findAll();
        ModelAndView mv = new ModelAndView("admin/comment");
        mv.addObject("msg",msg);
        mv.addObject("list",list);
        return mv;
    }
}
