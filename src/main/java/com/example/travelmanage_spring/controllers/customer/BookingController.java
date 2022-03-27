package com.example.travelmanage_spring.controllers.customer;

import com.example.travelmanage_spring.domain.Booking;
import com.example.travelmanage_spring.domain.Tour;
import com.example.travelmanage_spring.domain.User;
import com.example.travelmanage_spring.services.BookingService;
import com.example.travelmanage_spring.services.TourService;
import com.example.travelmanage_spring.utils.Middleware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

@Controller
public class BookingController {
    @Autowired
    MessageSource messageSource;

    @Autowired
    BookingService bookingService;

    @Autowired
    TourService tourService;

    Middleware middleware = new Middleware();
    @PostMapping("booking")
    public ModelAndView booking(@ModelAttribute("booking") Booking booking, HttpServletRequest request, RedirectAttributes rd){
        int tourId = Integer.parseInt(request.getParameter("tour_id"));
        int price = Integer.parseInt(request.getParameter("priceTour"));
        String url = "redirect:/tour/detail/"+tourId;
        ModelAndView mv = new ModelAndView(url);
        User user = middleware.middleware(request);
        if(user == null){
            rd.addFlashAttribute("booking_error", messageSource.getMessage("booking_login", null, Locale.getDefault()));
        }else{
            booking.setUser_b(user);
            booking.setCreatedAt(java.time.LocalDate.now().toString());
            int total = booking.getQuantityAdults() * 400000 + booking.getQuantityChirld() * 200000 + price;
            booking.setTotal(total);
            Tour tour = tourService.findTourById(tourId);
            booking.setTour(tour);
            bookingService.save(booking);
            rd.addFlashAttribute("booking_success", messageSource.getMessage("booking_success", null, Locale.getDefault()));
        }
        return mv;
    }

    @GetMapping("booking-user")
    public ModelAndView getBookingUser(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("public/list-booking");
        User user = middleware.middleware(request);
        List<Booking> list = bookingService.findBookingByUserId(user.getId());
        mv.addObject("list",list);
        return mv;
    }
}
