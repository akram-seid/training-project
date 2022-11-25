package com.example.trainingproject.controller;

import com.example.trainingproject.model.User;
import com.example.trainingproject.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/create")
    public ResponseEntity<?> saveUser(@Valid @RequestBody User user){
        userService.create(user);
        return new  ResponseEntity<>("saved Successfully", HttpStatus.CREATED);
    }

    @GetMapping("/read")
    public ResponseEntity<?> findAllUsers(){
        return new ResponseEntity<>(userService.read(),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        return new ResponseEntity<>(userService.update(user), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        userService.delete(id);
        return new ResponseEntity<>("Data Deleted Successfully",HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> findUser(@PathVariable("id") Long userId){

        return new ResponseEntity<>(userService.getById(userId), HttpStatus.OK);
    }
}
