package com.example.trainingproject.repository;

import com.example.trainingproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByPhone(String phone);
    Optional<User> findUserById(Long id);

}
