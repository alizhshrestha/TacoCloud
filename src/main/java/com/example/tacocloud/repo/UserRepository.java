package com.example.tacocloud.repo;

import com.example.tacocloud.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
