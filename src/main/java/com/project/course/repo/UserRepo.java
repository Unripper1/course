package com.project.course.repo;

import com.project.course.models.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByLogin(String login);
    User findById(long id);

}