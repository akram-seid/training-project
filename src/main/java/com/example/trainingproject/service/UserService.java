package com.example.trainingproject.service;

import com.example.trainingproject.exception.ResourceNotFoundException;
import com.example.trainingproject.model.User;
import com.example.trainingproject.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User User){
        Optional<User> optionalUser = userRepository.findUserByPhone(User.getPhone());

        if(optionalUser.isEmpty()){
            return userRepository.save(User);
        }
        throw new ResourceNotFoundException("User","Phone Number",User.getPhone());
    }

    public List<User> read(){
        List<User> users = userRepository.findAll();
        if(users.isEmpty()){
            throw new RuntimeException("Empty list Found!");
        }
        return users;
    }

    public User update(User User){
        User optionalUser = userRepository.findUserById(User.getId())
                .orElseThrow(()->new ResourceNotFoundException("User","id",User.getId()));
        return userRepository.save(User);
    }

    public void delete(Long id){
        User optionalUser = userRepository.findUserById(id)
                .orElseThrow(()->new ResourceNotFoundException("User","id",id));

        userRepository.deleteById(id);
    }

    public User getById(Long id){
        return userRepository.findUserById(id)
                .orElseThrow(()->new ResourceNotFoundException("User","id",id));
    }
}
