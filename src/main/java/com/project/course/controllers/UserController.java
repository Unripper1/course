package com.project.course.controllers;


import com.project.course.models.SignUpRequest;
import com.project.course.models.Subscribe;
import com.project.course.models.User;
import com.project.course.repo.SubscribeRepo;
import com.project.course.repo.UserRepo;
import com.project.course.services.SubscribeService;
import com.project.course.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Controller @RequestMapping("/registration")
public class UserController {
    @Autowired
    SubscribeRepo subscribeRepo;
    @Autowired
    UserRepo userRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserService userService;
    private SubscribeService subscribeService;

    @Autowired
    public UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder, SubscribeService subscribeService){
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.subscribeService=subscribeService;
    }
    @GetMapping
    public String registration(){
        return "registration";
    }
    @GetMapping("/1")
    public String registration1(){
        return "registration";
    }
    @GetMapping("/2")
    public String registration2(){
        System.out.println("!"+userService.getCurrentUsername()+"!");
        return "registration";
    }
    @GetMapping("/3")
    public String registration3(){
        return "registration";
    }
    @PostMapping("/1")
    public String registration1(@Valid SignUpRequest signUpRequest, BindingResult result, Model model) {
        try {
            testSub();
            userService.addUser(signUpRequest,subscribeService.findSubscribe(1),bCryptPasswordEncoder);
            return "redirect:/home";
        } catch (Exception e) {
            model.addAttribute("status","Ошибка");
            return "registration";
        }
    }

    private void testSub() {
        if(subscribeService.findSubscribe(1)==null){
            userRepo.deleteAll();
            Subscribe subscribe=new Subscribe();
            subscribe.setId(1);
            subscribe.setS1(true);
            subscribe.setS2(true);
            subscribe.setS3(true);
            subscribeRepo.save(subscribe);
            Subscribe subscribe2=new Subscribe();
            subscribe2.setId(2);
            subscribe2.setS1(true);
            subscribe2.setS2(true);
            subscribe2.setS3(true);
            subscribeRepo.save(subscribe2);
            Subscribe subscribe3=new Subscribe();
            subscribe3.setId(3);
            subscribe3.setS1(true);
            subscribe3.setS2(true);
            subscribe3.setS3(true);
            subscribeRepo.save(subscribe3);

        }
    }

    @PostMapping("/2")
    public String registration2(@Valid SignUpRequest signUpRequest, BindingResult result, Model model) {
        testSub();
        try {
        userService.addUser(signUpRequest,subscribeService.findSubscribe(2),bCryptPasswordEncoder);
        return "redirect:/home";

    } catch (Exception e) {
        model.addAttribute("status","Ошибка");
        return "registration";
    }
    }
    @PostMapping("/3")
    public String registration3(@Valid SignUpRequest signUpRequest, BindingResult result, Model model) {
        testSub();
        try{
        userService.addUser(signUpRequest,subscribeService.findSubscribe(3),bCryptPasswordEncoder);
        return "redirect:/home";
    } catch (Exception e) {
        model.addAttribute("status","Ошибка");
        return "registration";
    }

    }
    @GetMapping("/1ch")
    public String change1() {
        User user=userService.findUser(userService.getCurrentUsername());
        user.setSubscribe(subscribeService.findSubscribe(1));
        LocalDate today = LocalDate.now();
        LocalDate finalday;
        finalday=today.plusMonths(1);
        user.setFinalDate(finalday.toString());
        return "redirect:/info";
    }
    @GetMapping("/2ch")
    public String change2() {
        User user=userService.findUser(userService.getCurrentUsername());
        user.setSubscribe(subscribeService.findSubscribe(2));
        LocalDate today = LocalDate.now();
        LocalDate finalday;
        finalday=today.plusMonths(1);
        user.setFinalDate(finalday.toString());
        return "redirect:/info";
    }
    @GetMapping("/3ch")
    public String change3() {
        User user=userService.findUser(userService.getCurrentUsername());
        user.setSubscribe(subscribeService.findSubscribe(3));
        LocalDate today = LocalDate.now();
        LocalDate finalday;
        finalday=today.plusMonths(1);
        user.setFinalDate(finalday.toString());
        return "redirect:/info";
    }
}
