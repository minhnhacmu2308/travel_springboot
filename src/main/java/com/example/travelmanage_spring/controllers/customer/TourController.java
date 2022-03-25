package com.example.travelmanage_spring.controllers.customer;

import com.example.travelmanage_spring.domain.Tour;
import com.example.travelmanage_spring.services.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TourController {
    @Autowired
    TourService tourService;

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable int id){
        ModelAndView mv = new ModelAndView("public/detail");
        Tour obj = tourService.findTourById(id);
        obj.setView(obj.getView()+1);
        tourService.save(obj);
        mv.addObject("tour",obj);
        return mv;
    }
}
