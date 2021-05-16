package com.project.course.services;

import com.project.course.models.Subscribe;
import com.project.course.repo.SubscribeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Component
@Transactional
public class SubscribeService {
    @Autowired
    SubscribeRepo subscribeRepo;
    public Subscribe findSubscribe(long n){
        return subscribeRepo.findById(n);
    }
    public List <Subscribe> findAll(){
        return subscribeRepo.findAll();
    }
}
