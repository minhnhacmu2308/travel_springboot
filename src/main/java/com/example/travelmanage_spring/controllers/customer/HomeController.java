package com.example.travelmanage_spring.controllers.customer;

import com.example.travelmanage_spring.domain.Tour;
import com.example.travelmanage_spring.services.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @Autowired
    TourService tourService;

    @ModelAttribute
    public void addModel(Model model)
    {
        Sort sort = Sort.by("view").descending();
        List<Tour>  tourList = tourService.findAll(sort).stream().limit(5).collect(Collectors.toList());
        List<Tour>  tourList1 = tourService.findAll().stream().limit(5).collect(Collectors.toList());
        model.addAttribute("topTour", tourList);
        model.addAttribute("listTour", tourList);
    }

    @GetMapping({"/" , "/home"})
    public ModelAndView index( ){
        ModelAndView mv = new ModelAndView("public/home");
        mv.addObject("activeHome","activeHome");
        return mv;
    }

    @GetMapping({"/map"})
    public ModelAndView map( ){
        ModelAndView mv = new ModelAndView("public/map");
        return mv;
    }

    @GetMapping({"/video"})
    public ModelAndView video( ){
        ModelAndView mv = new ModelAndView("public/video");
        return mv;
    }
}
