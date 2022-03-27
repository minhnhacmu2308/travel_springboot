package com.example.travelmanage_spring.controllers.admin;

import com.example.travelmanage_spring.domain.Role;
import com.example.travelmanage_spring.domain.Tour;
import com.example.travelmanage_spring.domain.User;
import com.example.travelmanage_spring.serviceImpl.TourServiceImpl;
import com.example.travelmanage_spring.serviceImpl.UserServiceImpl;
import com.example.travelmanage_spring.utils.EncrytedPasswordUtils;
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
public class AdminUserController {
    @Autowired
    UserServiceImpl userService;

    EncrytedPasswordUtils encrytedPasswordUtils = new EncrytedPasswordUtils();

    @GetMapping({ "/employee"})
    public ModelAndView index(String msg)
    {
        List<User> list = userService.listEmployee();
        ModelAndView mv = new ModelAndView("admin/employee");
        mv.addObject("msg",msg);
        mv.addObject("list",list);
        return mv;
    }

    @GetMapping({ "/customer"})
    public ModelAndView indexcus(String msg)
    {
        List<User> list = userService.listCustomer();
        ModelAndView mv = new ModelAndView("admin/customer");
        mv.addObject("msg",msg);
        mv.addObject("list",list);
        return mv;
    }

    @PostMapping(value = "/employee-add")
    public ModelAndView add(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("redirect:employee");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordMD5 = encrytedPasswordUtils.md5(password);
        String phoneNumber = request.getParameter("phoneNumber");
        String userName = request.getParameter("userName");
        Role role = userService.findRoleById(2);
        User user = new User();
        user.setFullName(name);
        user.setEmail(email);
        user.setPassword(passwordMD5);
        user.setPhoneNumber(phoneNumber);
        user.setUserName(userName);
        user.setRole(role);
        user.setStatus(1);
        userService.save(user);
        mv.addObject("msg","success");
        return mv;
    }
}
