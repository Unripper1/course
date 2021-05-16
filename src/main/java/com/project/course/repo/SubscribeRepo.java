package com.project.course.repo;

import com.project.course.models.Subscribe;
import com.project.course.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscribeRepo extends JpaRepository<Subscribe, Long> {
    Subscribe findById(long id);
    void deleteSubscribeById(long id);
}
