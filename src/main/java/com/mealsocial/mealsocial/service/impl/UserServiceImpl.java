package com.mealsocial.mealsocial.service.impl;


import com.mealsocial.mealsocial.domain.User;
import com.mealsocial.mealsocial.dto.response.UserResponseDto;
import com.mealsocial.mealsocial.repo.UserRepository;
import com.mealsocial.mealsocial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;


    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        UserResponseDto userResponseDto = null;
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()) {
            userResponseDto = new UserResponseDto();
            userResponseDto.setUserName(userOptional.get().getUserName());
            userResponseDto.setUserId(userOptional.get().getUserId());
            userResponseDto.setFeedbacks(userOptional.get().getFeedbacks());
            userResponseDto.setPosts(userOptional.get().getPosts());
            userResponseDto.setRecipes(userOptional.get().getRecipes());
            userResponseDto.setMeals(userOptional.get().getMeals());
        }
        return userResponseDto;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserResponseDto getUserResponseById(Long id) {
        UserResponseDto userResponseDto = null;
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()) {
            userResponseDto = new UserResponseDto();
            userResponseDto.setUserName(userOptional.get().getUserName());
            userResponseDto.setUserId(userOptional.get().getUserId());
        }
        return userResponseDto;
    }

    @Override
    public User getUserEntityById(Long id) {
        try {
            Optional<User> userOptional = userRepository.findById(id);
            return userOptional.orElse(null);
        } catch (InvalidDataAccessApiUsageException e) {
            return null;
        }
    }



}
