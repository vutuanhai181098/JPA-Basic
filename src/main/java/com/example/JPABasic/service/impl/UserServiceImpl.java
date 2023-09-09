package com.example.JPABasic.service.impl;

import com.example.JPABasic.dto.UserDto;
import com.example.JPABasic.entities.User;
import com.example.JPABasic.exception.ErrorPasswordException;
import com.example.JPABasic.exception.ResourceNotFoundException;
import com.example.JPABasic.mapper.UserMapper;
import com.example.JPABasic.repository.UserRepository;
import com.example.JPABasic.request.UpdateAvatarRequest;
import com.example.JPABasic.request.UpdatePasswordRequest;
import com.example.JPABasic.request.UserRequest;
import com.example.JPABasic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    // 1. Lấy danh sách users (có phân trang - pagination).
    @Override
    public List<UserDto> getAllUsers(Integer page, Integer limit, String sortBy) {
        Pageable pageable = (Pageable) PageRequest.of(page, limit, Sort.by(sortBy));
        Page<User> pageResult = userRepository.findAll(pageable);
        if(pageResult.hasContent()){
            List<User> users = pageResult.getContent();
            return users.stream().map(user -> {
                return userMapper.mapToDto(user);
            }).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    // 2. Tìm kiếm user theo tên.
    @Override
    public List<UserDto> getUserByName(String name) {
        List<User> users = userRepository.findAllByNameContainingIgnoreCase(name);
        return users.stream().map(user -> {
            return userMapper.mapToDto(user);
        }).collect(Collectors.toList());
    }


    // 3. Lấy chi tiết user theo id.
    @Override
    public UserDto getUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found user"));
        return userMapper.mapToDto(user);
    }


    // 4. Tạo mới user.
    @Override
    public UserDto createUser(UserRequest request) {
        User user = userMapper.mapToEntity(request);
        userRepository.save(user);
        return userMapper.mapToDto(user);
    }

    // 5. Cập nhật thông tin user.
    @Override
    public UserDto updateUser(Integer id, UserRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found user"));
        userMapper.updateUser(request, user);
        userRepository.save(user);
        return userMapper.mapToDto(user);
    }

    // 6. Xóa User.
    @Override
    public void deleteUser(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Not found user"));
        userRepository.delete(user);
    }


    // 7. Thay đổi ảnh avatar.
    @Override
    public void updateAvatar(Integer id, UpdateAvatarRequest request) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Not found user"));
        user.setAvatar(request.getAvatar());
        userRepository.save(user);
    }

    // 8. Thay đổi mật khẩu.
    @Override
    public void updatePassword(Integer id, UpdatePasswordRequest request) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Not found user"));
        if (!user.getPassword().equals(request.getOldPassword())) {
            throw new ErrorPasswordException("Incorrect password");
        }
        if (request.getOldPassword().equals(request.getNewPassword())) {
            throw new ErrorPasswordException("The new password must not be the same as the old password");
        }
        user.setPassword(request.getNewPassword());
        userRepository.save(user);
    }

    //9. Quên mật khẩu.
    @Override
    public String forgotPassword(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Not found user"));
        String newPass = randomPassword();
        user.setPassword(newPass);
        userRepository.save(user);
        return newPass;
    }

    private String randomPassword() {
        final String CHARACTERS =
                "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+[{]};:',<.>/?";
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*()-_=+[{]};:',<.>/?]).{8,21}$";
        Random random = new Random();
        int n = CHARACTERS.length();
        do {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 8; i++) {
                int idx = random.nextInt(n);
                sb.append(CHARACTERS.charAt(idx));
            }
            if (sb.toString().matches(regex)) {
                return sb.toString();
            }
        } while (true);
    }
}
