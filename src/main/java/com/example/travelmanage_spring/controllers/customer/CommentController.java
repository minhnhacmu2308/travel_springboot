package com.example.travelmanage_spring.controllers.customer;

import com.example.travelmanage_spring.domain.Comment;
import com.example.travelmanage_spring.domain.Post;
import com.example.travelmanage_spring.domain.User;
import com.example.travelmanage_spring.services.CommentService;
import com.example.travelmanage_spring.services.PostService;
import com.example.travelmanage_spring.utils.Middleware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Controller
@RequestMapping("comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    PostService postService;

    @Autowired
    MessageSource messageSource;

    Middleware middleware = new Middleware();

    @PostMapping("/add")
    public ModelAndView add(HttpServletRequest request, RedirectAttributes rd){
        String rateString = request.getParameter("rate");
        int rate = 5;
        if (rateString != null){
            rate = Integer.parseInt(rateString);
        }
        int postId = Integer.parseInt(request.getParameter("postId"));
        String text = request.getParameter("text");
        User user = middleware.middleware(request);
        String url = "redirect:/post/detail/"+postId;
        ModelAndView mv = new ModelAndView(url);
        if(user == null){
            rd.addFlashAttribute("booking_error", messageSource.getMessage("booking_login", null, Locale.getDefault()));
        }else{
            Post post = postService.findPostById(postId);
            Comment comment = new Comment();
            comment.setText(text);
            comment.setPost(post);
            comment.setUser(user);
            comment.setCreatedAt(java.time.LocalDate.now().toString());
            comment.setNumberRate(rate);
            commentService.save(comment);
            rd.addFlashAttribute("booking_success", messageSource.getMessage("booking_success", null, Locale.getDefault()));
        }
        return mv;
    }

}
