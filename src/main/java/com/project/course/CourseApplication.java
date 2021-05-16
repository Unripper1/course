package com.project.course;

import com.project.course.models.Subscribe;
import com.project.course.models.User;
import com.project.course.repo.UserRepo;
import com.project.course.services.SubscribeService;
import com.project.course.services.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class CourseApplication {
    @Autowired
    UserRepo userRepo;
    @Autowired
    SubscribeService subscribeService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    public static void main(String[] args) {

        SpringApplication.run(CourseApplication.class, args);
    }
//    @Bean
//    ApplicationRunner init(){
//        return(ApplicationArguments args)->{
//            User user=new User();
//            user.setName("admin2");
//            user.setFinalDate("0000-00-00");
//            user.setLogin("admin2@admin.com");
//            user.setCardNumber(1);
//            user.setSubscribe(subscribeService.findSubscribe(3));
//            user.setPassword(bCryptPasswordEncoder.encode("0000"));
//            userRepo.save(user);
//        };
//    }

}
