package com.project.course.controllers;

import com.project.course.models.User;
import com.project.course.services.SubscribeService;
import com.project.course.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
public class IndexController {
    final
    UserService userService;
    @Autowired
    SubscribeService subscribeService;
    public IndexController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String greeting(Model model) {
        if(userService.findUser(userService.getCurrentUsername())!=null){
            model.addAttribute("status",false);
        }
        else model.addAttribute("status",true);
        return "index";
    }
    @GetMapping("/info")
    public  String info(Model model){
       // System.out.println(userService.findUser(userService.getCurrentUsername()).getRoleList().get(0));
        //if(userService.getCurrentUsername().equals("admin@admin.com")){
        if(userService.findUser(userService.getCurrentUsername()).getRoleList().get(0).getRole().equals("ADMIN")){
            return "redirect:/adminpage";
        }
        else {
            User user=userService.findUser(userService.getCurrentUsername());
            if(user.getSubscribe().getId()==1) model.addAttribute("tariff", "Базовый пакет");
            if(user.getSubscribe().getId()==2) model.addAttribute("tariff", "Продвинутый пакет");
            if(user.getSubscribe().getId()==3) model.addAttribute("tariff", "Премиум пакет");
            model.addAttribute("name",user.getName());
            model.addAttribute("final","Подписка действительна до: "+user.getFinalDate());
            return "info";
        }
    }
    @GetMapping("/change")
    public String change(Model model){
        User user=userService.findUser(userService.getCurrentUsername());
        model.addAttribute("status",user.getSubscribe().getId());
        return "change";
    }
    @GetMapping("/pay")
    public String pay(){
        User user=userService.findUser(userService.getCurrentUsername());
        int year=Integer.valueOf(user.getFinalDate().substring(0,4));
        int month=Integer.valueOf(user.getFinalDate().substring(5,7));
        int day=Integer.valueOf(user.getFinalDate().substring(8));
        LocalDate dt=LocalDate.of(year,month,day);
        user.setFinalDate(dt.plusMonths(1).toString());
        return "redirect:/info";
    }
//    @GetMapping("/logintest")
//    public String logintest(){
//        if(userService.findAll()==null){
//            User user=new User();
//            user.setName("admin");
//            user.setFinalDate("0000-00-00");
//            user.setLogin("admin@admin.com");
//            user.setCardNumber(1);
//            user.setSubscribe(subscribeService.findSubscribe(3));
//        }
//    }
}
