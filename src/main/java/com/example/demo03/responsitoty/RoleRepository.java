package com.example.demo03.responsitoty;

import com.example.demo03.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> getByRoleName(String name);
}
