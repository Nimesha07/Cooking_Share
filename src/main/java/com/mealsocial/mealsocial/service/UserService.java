package com.mealsocial.mealsocial.service;


import com.mealsocial.mealsocial.domain.User;
import com.mealsocial.mealsocial.dto.response.UserResponseDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    UserResponseDto getUserById(Long id);
    List<User> getAllUsers();
    UserResponseDto getUserResponseById(Long id);

    User getUserEntityById(Long id);
}
