package com.example.travelmanage_spring.controllers.customer;

import com.example.travelmanage_spring.domain.Tour;
import com.example.travelmanage_spring.services.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("tour")
public class TourController {
    @Autowired
    TourService tourService;

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable int id,Model model){
        ModelAndView mv = new ModelAndView("public/detail");
        Sort sort = Sort.by("view").descending();
        List<Tour> tourList = tourService.findAll(sort).stream().limit(5).collect(Collectors.toList());
        Tour obj = tourService.findTourById(id);
        obj.setView(obj.getView()+1);
        tourService.save(obj);
        mv.addObject("tour",obj);
        model.addAttribute("topTour", tourList);
        return mv;
    }
}
