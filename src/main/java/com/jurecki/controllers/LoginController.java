package com.jurecki.controllers;

import com.jurecki.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Author Dawid Jurecki on 02.12.2019
 **/

@Controller
public class LoginController {

    @RequestMapping(value = "/login_page", method = RequestMethod.GET)
    public ModelAndView loginPage(){
        return new ModelAndView("loginPage", "userKey", new User());
    }

    @RequestMapping(value = "/login_page", method = RequestMethod.POST)
    public String loginForm(@ModelAttribute("userKey") User user){
        if(user.getLogin().equals("admin") && user.getPassword().equals("admin123")){
            return "redirect:index";
        } else {
            return "loginPage";
        }
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String indexPage(){
        return "index";
    }
}

