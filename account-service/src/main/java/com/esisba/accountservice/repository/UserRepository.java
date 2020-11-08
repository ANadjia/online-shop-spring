package com.esisba.accountservice.repository;

import com.esisba.accountservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByEmail(String email);
    User findUserById(Long id);
}