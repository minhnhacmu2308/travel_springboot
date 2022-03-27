package com.example.travelmanage_spring.controllers.admin;

import com.example.travelmanage_spring.domain.Booking;
import com.example.travelmanage_spring.domain.Comment;
import com.example.travelmanage_spring.domain.Post;
import com.example.travelmanage_spring.domain.Tour;
import com.example.travelmanage_spring.serviceImpl.BookingServiceImpl;
import com.example.travelmanage_spring.serviceImpl.CommentServiceImpl;
import com.example.travelmanage_spring.serviceImpl.PostServiceImpl;
import com.example.travelmanage_spring.serviceImpl.TourServiceImpl;
import com.example.travelmanage_spring.services.PostService;
import com.example.travelmanage_spring.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminPostController {
    @Autowired
    PostServiceImpl postService;

    @Autowired
    CommentServiceImpl commentService;

    @GetMapping({ "/post"})
    public ModelAndView index(String msg)
    {
        List<Post> list = postService.findAll();
        ModelAndView mv = new ModelAndView("admin/post");
        mv.addObject("msg",msg);
        mv.addObject("list",list);
        return mv;
    }

    @PostMapping(value = "/post-add")
    public ModelAndView add(HttpServletRequest request, @RequestParam("file") MultipartFile image){
        ModelAndView mv = new ModelAndView("redirect:post");
        String name = request.getParameter("name");
        String desciption = request.getParameter("description");
        Post post = new Post();
        post.setTitle(name);
        post.setDescription(desciption);
        post.setStatus(1);
        post.setCreatedAt(java.time.LocalDate.now().toString());
        String fileName = "";
        try {
            fileName = FileUtil.upload(image,request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        post.setImage(fileName);
        postService.save(post);
        mv.addObject("msg","success");
        return mv;
    }
    @PostMapping(value = "/post-update")
    public ModelAndView update(HttpServletRequest request,@RequestParam("file") MultipartFile image){
        ModelAndView mv = new ModelAndView("redirect:post");
        String name = request.getParameter("name");
        String desciption = request.getParameter("description");
        int id = Integer.parseInt(request.getParameter("id"));
        String fileName = "";
        if(image.isEmpty()) {
            Post Post = postService.findPostById(id);
            fileName = Post.getImage();
        } else {
            try {
                fileName = FileUtil.upload(image,request);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        postService.update(name,desciption,fileName,id);
        mv.addObject("msg","success");

        return mv;
    }
    @PostMapping(value = "/post-delete")
    public ModelAndView delete(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("redirect:post");
        String id = request.getParameter("id");
        int idc = Integer.parseInt(id);
        Post post = postService.findPostById(idc);
        List<Comment> listB = commentService.findCommentByPost(post);
        if(listB.size() > 0){
            mv.addObject("msg","error");
        }
        else {
            postService.delete(idc);
            mv.addObject("msg", "success");
        }
        return mv;
    }
}
