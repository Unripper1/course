package com.project.course.services;

import com.project.course.models.Role;
import com.project.course.models.SignUpRequest;
import com.project.course.models.Subscribe;
import com.project.course.models.User;
import com.project.course.repo.RoleRepo;
import com.project.course.repo.SubscribeRepo;
import com.project.course.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Component
@Transactional
public class UserService implements UserDetailsService {
    @Autowired
    SubscribeRepo subscribeRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;
    public void registerUser(UserDTO userDTO){

    }
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void addUser(SignUpRequest signUpRequest, Subscribe tariff, BCryptPasswordEncoder bCryptPasswordEncoder) {
        Role role = roleRepo.findRoleByRole("USER");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        User user = new User();
        user.setRoleList(roles);
        user.setLogin(signUpRequest.getLogin());
        user.setCardNumber(signUpRequest.getCardNumber());
        LocalDate today = LocalDate.now();
        LocalDate finalday;
        finalday=today.plusMonths(1);
        user.setFinalDate(finalday.toString());
        user.setName(signUpRequest.getName());
        user.setSubscribe(tariff);
        user.setPassword(bCryptPasswordEncoder.encode(signUpRequest.getPassword()));
        if (findUser(user.getLogin()) != null) {
            throw new UsernameNotFoundException("Exist");
        }
        else userRepo.save(user);
    }
    public  List <User> findAll(){
        return userRepo.findAll();
    }
    public void delUser(long id){
//        subscribeRepo.deleteSubscribeById(userRepo.findById(id).get().getId());
//        System.out.println((userRepo.findById(id).get().getId()));
        userRepo.findById(id).setSubscribe(null);
        userRepo.deleteById((id));
    }

    public User findUser(String name) {
        return userRepo.findByLogin(name);
    }

    @Override
    public UserDetails loadUserByUsername(String name) {
        User user = findUser(name);
        if (user == null) {
            throw new UsernameNotFoundException("Not found");
        }
        return new UserDTO(user);
    }
    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}