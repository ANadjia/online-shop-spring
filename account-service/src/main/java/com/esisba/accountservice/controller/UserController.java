package com.esisba.accountservice.controller;

import com.esisba.accountservice.entities.User;
import com.esisba.accountservice.entities.UserDto;
import com.esisba.accountservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("api-v1/users")
public class UserController {

    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public Collection<User> getAllUsers(){ return  userRepository.findAll(); }

    @GetMapping("/{email}")
    public User getUserByEmail(@PathVariable("email") String email){
        return userRepository.findUserByEmail(email);
    }

    @PostMapping("/signup")
    public User createUser(@Valid @RequestBody User user){
        User u = userRepository.findUserByEmail(user.getEmail());
        if(u != null){
            return null;
        }
        return userRepository.save(user);
    }

    @PostMapping("/login")
    public User auth(@RequestBody UserDto user){
        User u = userRepository.findUserByEmail(user.getEmail());
        if(u != null){
            if(u.getPassword().equals(user.getPassword()))
                return u;
        }
        return null;
    }

    @GetMapping("/user/{idUser}")
    public User getUserById(@PathVariable Long idUser) {
        return userRepository.findUserById(idUser);
    }
}