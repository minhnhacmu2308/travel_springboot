package com.example.travelmanage_spring.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("admin")
public class AdminHomeController {


    @GetMapping({"/" , "/index"})
    public ModelAndView loadHomePage(HttpServletRequest request)
    {
        ModelAndView mv = new ModelAndView();
            mv = new ModelAndView("admin/home");
        return mv;
    }
}
