package com.example.travelmanage_spring.utils;


import com.example.travelmanage_spring.constans.CommonConstants;
import com.example.travelmanage_spring.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class Middleware {

    public static User middleware( HttpServletRequest request){;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(CommonConstants.SESSION_USER);
        if (Objects.nonNull(user)) {
            return user;
        } else {
            return null;
        }
    }

    public  static boolean middlewareAdmin( HttpServletRequest request){;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(CommonConstants.SESSION_ADMIN);
        if (Objects.nonNull(user)) {
            return true;
        } else {
            return false;
        }
    }
}
