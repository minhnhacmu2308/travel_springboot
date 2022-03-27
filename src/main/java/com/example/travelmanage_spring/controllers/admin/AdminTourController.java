package com.example.travelmanage_spring.controllers.admin;

import com.example.travelmanage_spring.domain.Tour;
import com.example.travelmanage_spring.serviceImpl.TourServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminTourController {
    @Autowired
    TourServiceImpl tourService;

    @GetMapping({ "/tour"})
    public ModelAndView index(String msg)
    {
        List<Tour> list = tourService.findAll();
        ModelAndView mv = new ModelAndView("admin/tour");
        mv.addObject("msg",msg);
        mv.addObject("list",list);
        return mv;
    }

    @PostMapping(value = "/tour-add")
    public ModelAndView add(HttpServletRequest request, @RequestParam("file") MultipartFile image){
        ModelAndView mv = new ModelAndView("redirect:tour");
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        String desciption = request.getParameter("description");
        String due_time = request.getParameter("due_time");
        Date start_date = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("start_date"));
        Tour tour = new Tour();
        tour.setName(name);
        tour.setPrice(price);
        tour.setDescription(desciption);
        String fileName = "";
        try {
            fileName = FileUtil.upload(image,request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        tour.setImage(fileName);
        categoryItemsService.save(categoryItems);
        mv.addObject("msg","success");
        return mv;
    }
    @PostMapping(value = "/category-update")
    public ModelAndView update(HttpServletRequest request,@RequestParam("file") MultipartFile image){
        ModelAndView mv = new ModelAndView("redirect:category");
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        String desciption = request.getParameter("description");
        int id = Integer.parseInt(request.getParameter("id"));
        String fileName = "";
        if(image.isEmpty()) {
            CategoryItems categoryItems = categoryItemsService.findCategoryItemsById(id);
            fileName = categoryItems.getImage();
        } else {
            try {
                fileName = FileUtil.upload(image,request);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        categoryItemsService.update(name,desciption,price,fileName,id);
        mv.addObject("msg","success");

        return mv;
    }
    @PostMapping(value = "/category-delete")
    public ModelAndView delete(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("redirect:category");
        String id = request.getParameter("id");
        int idc = Integer.parseInt(id);
        CategoryItems categoryItems = categoryItemsService.findCategoryItemsById(idc);
        List<Booking> listB = bookingService.findBookingByCategoryItems(categoryItems);
        if(listB.size() > 0){
            mv.addObject("msg","error");
        }
        else {
            categoryItemsService.delete(idc);
            mv.addObject("msg", "success");
        }
        return mv;
    }

}
