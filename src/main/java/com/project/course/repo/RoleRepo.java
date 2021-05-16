package com.project.course.repo;

import com.project.course.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findRoleByRole(String role);
}
