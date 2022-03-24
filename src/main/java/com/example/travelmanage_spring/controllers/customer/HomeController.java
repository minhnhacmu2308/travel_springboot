package com.example.travelmanage_spring.controllers.customer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @GetMapping({"/" , "/home"})
    public ModelAndView index( ){
        ModelAndView mv = new ModelAndView("public/home");
        return mv;
    }
}
