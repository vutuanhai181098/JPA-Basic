package com.example.JPABasic.service;

import com.example.JPABasic.dto.UserDto;
import com.example.JPABasic.request.UpdateAvatarRequest;
import com.example.JPABasic.request.UpdatePasswordRequest;
import com.example.JPABasic.request.UserRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> getAllUsers(Integer page, Integer limit, String sortBy);

    List<UserDto> getUserByName(String name);

    UserDto getUserById(Integer id);

    UserDto createUser(UserRequest request);

    UserDto updateUser(Integer id, UserRequest request);

    void updateAvatar(Integer id, UpdateAvatarRequest request);

    void updatePassword(Integer id, UpdatePasswordRequest request);

    String forgotPassword(Integer id);

    void deleteUser(Integer id);
}
