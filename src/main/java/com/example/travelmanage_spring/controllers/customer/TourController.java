package com.example.travelmanage_spring.controllers.customer;

import com.example.travelmanage_spring.domain.Tour;
import com.example.travelmanage_spring.services.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/search/{keySearch}")
    public ModelAndView search(@PathVariable String keySearch){

        List<Tour> tour = tourService.findTourByTitleOrPrice(keySearch);
        ModelAndView mv = new ModelAndView("public/result-search");
        mv.addObject("listTour",tour);
        mv.addObject("keySearch",keySearch);
        return  mv;
    }

    @PostMapping("/search")
    public ModelAndView search(HttpServletRequest request){
        String keySearch = request.getParameter("keySearch");
        String url = "redirect:/tour/search/"+keySearch;
        ModelAndView mv = new ModelAndView(url);
        return  mv;
    }

    @GetMapping("/search-date/{keySearch}")
    public ModelAndView searchDate(@PathVariable String keySearch){

        List<Tour> tour = tourService.searchDate(keySearch);
        ModelAndView mv = new ModelAndView("public/result-search");
        mv.addObject("listTour",tour);
        mv.addObject("keySearch",keySearch);
        return  mv;
    }

    @PostMapping("/search-date")
    public ModelAndView searchDate(HttpServletRequest request){
        String keySearch = request.getParameter("keySearch");
        String url = "redirect:/tour/search-date/"+keySearch;
        ModelAndView mv = new ModelAndView(url);
        return  mv;
    }

    @GetMapping("/")
    public ModelAndView get(){
        ModelAndView mv = new ModelAndView("public/list-tour");
        List<Tour> list = tourService.findAll();
        mv.addObject("listTour",list);
        mv.addObject("activeTour","activeTour");
        return mv;
    }
}
