package com.example.travelmanage_spring.controllers.admin;

import com.example.travelmanage_spring.domain.Booking;
import com.example.travelmanage_spring.domain.Tour;
import com.example.travelmanage_spring.serviceImpl.BookingServiceImpl;
import com.example.travelmanage_spring.serviceImpl.TourServiceImpl;
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
public class AdminTourController {
    @Autowired
    TourServiceImpl tourService;

    @Autowired
    BookingServiceImpl bookingService;

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
        String start_date = request.getParameter("start_date");
        String address = request.getParameter("address");
        Tour tour = new Tour();
        tour.setTitle(name);
        tour.setPrice(price);
        tour.setDescription(desciption);
        tour.setAddress(address);
        tour.setDueTime(due_time);
        tour.setStartDate(start_date);
        tour.setStatus(1);
        tour.setView(0);
        String fileName = "";
        try {
            fileName = FileUtil.upload(image,request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        tour.setImage(fileName);
        tourService.save(tour);
        mv.addObject("msg","success");
        return mv;
    }
    @PostMapping(value = "/tour-update")
    public ModelAndView update(HttpServletRequest request,@RequestParam("file") MultipartFile image){
        ModelAndView mv = new ModelAndView("redirect:tour");
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        String desciption = request.getParameter("description");
        String due_time = request.getParameter("due_time");
        String start_date = request.getParameter("start_date");
        String address = request.getParameter("address");
        int id = Integer.parseInt(request.getParameter("id"));
        String fileName = "";
        if(image.isEmpty()) {
            Tour tour = tourService.findTourById(id);
            fileName = tour.getImage();
        } else {
            try {
                fileName = FileUtil.upload(image,request);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        tourService.update(name,desciption,price,fileName,due_time,start_date,address,id);
        mv.addObject("msg","success");

        return mv;
    }
    @PostMapping(value = "/tour-delete")
    public ModelAndView delete(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("redirect:tour");
        String id = request.getParameter("id");
        int idc = Integer.parseInt(id);
        Tour tour = tourService.findTourById(idc);
        List<Booking> listB = bookingService.findBookingByTour(tour);
        if(listB.size() > 0){
            mv.addObject("msg","error");
        }
        else {
            tourService.delete(idc);
            mv.addObject("msg", "success");
        }
        return mv;
    }
}
