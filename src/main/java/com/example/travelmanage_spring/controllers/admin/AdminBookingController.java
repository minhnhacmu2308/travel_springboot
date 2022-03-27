package com.example.travelmanage_spring.controllers.admin;

import com.example.travelmanage_spring.domain.Booking;
import com.example.travelmanage_spring.domain.User;
import com.example.travelmanage_spring.serviceImpl.BookingServiceImpl;
import com.example.travelmanage_spring.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminBookingController {
    @Autowired
    BookingServiceImpl bookingService;

    @GetMapping({ "/booking"})
    public ModelAndView index(String msg)
    {
        List<Booking> list = bookingService.findAll();
        ModelAndView mv = new ModelAndView("admin/booking");
        mv.addObject("msg",msg);
        mv.addObject("list",list);
        return mv;
    }
}
