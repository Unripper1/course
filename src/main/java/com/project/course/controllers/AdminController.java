package com.project.course.controllers;

import com.project.course.services.SubscribeService;
import com.project.course.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adminpage")
public class AdminController {
    final
    UserService userService;
    final
    SubscribeService subscribeService;

    public AdminController(UserService userService, SubscribeService subscribeService) {
        this.userService = userService;
        this.subscribeService = subscribeService;
    }

    @GetMapping
    public  String info(Model model){
        model.addAttribute("users",userService.findAll());
        model.addAttribute("subscribes",subscribeService.findAll());
        return "admin";
    }
    @GetMapping("/del/{id}")
    public String del(@PathVariable("id") long id){
        userService.delUser(id);
        return "redirect:/adminpage";
    }
}
