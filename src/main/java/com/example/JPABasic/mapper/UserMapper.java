package com.example.JPABasic.mapper;

import com.example.JPABasic.dto.UserDto;
import com.example.JPABasic.entities.User;
import com.example.JPABasic.request.UserRequest;

public interface UserMapper {
    UserDto mapToDto(User user);

    User mapToEntity(UserRequest userRequest);

    void updateUser(UserRequest userRequest, User user);
}
