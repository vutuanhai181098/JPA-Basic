package com.example.JPABasic.mapper.impl;

import com.example.JPABasic.dto.UserDto;
import com.example.JPABasic.entities.User;
import com.example.JPABasic.mapper.UserMapper;
import com.example.JPABasic.request.UserRequest;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserDto mapToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .avatar(user.getAvatar())
                .build();
    }

    @Override
    public User mapToEntity(UserRequest userRequest) {
        return User.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .phone(userRequest.getPhone())
                .address(userRequest.getAddress())
                .password(userRequest.getPassword())
                .build();
    }

    @Override
    public void updateUser(UserRequest userRequest, User user) {
        user.setName(userRequest.getName());
        user.setPhone(userRequest.getPhone());
        user.setAddress(userRequest.getAddress());
    }
}
