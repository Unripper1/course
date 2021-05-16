//package com.project.course;
//
//import com.project.course.models.User;
//import com.project.course.repo.UserRepo;
//import com.project.course.services.SubscribeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//@Component
//public class DataLoader {
//    @Autowired
//    UserRepo userRepo;
//    @Autowired
//    SubscribeService subscribeService;
//    @Autowired
//    BCryptPasswordEncoder bCryptPasswordEncoder;
//    @PostConstruct
//    public void loadData(){
//        User user=new User();
//        user.setName("admin2");
//        user.setFinalDate("0000-00-00");
//        user.setLogin("admin2@admin.com");
//        user.setCardNumber(1);
//        user.setSubscribe(subscribeService.findSubscribe(3));
//        user.setPassword(bCryptPasswordEncoder.encode("0000"));
//        userRepo.save(user);
//    }
//}
