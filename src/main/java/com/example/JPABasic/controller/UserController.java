package com.example.JPABasic.controller;

import com.example.JPABasic.request.UpdateAvatarRequest;
import com.example.JPABasic.request.UpdatePasswordRequest;
import com.example.JPABasic.request.UserRequest;
import com.example.JPABasic.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    // 1. Lấy danh sách users (có phân trang - pagination).
    @GetMapping("")
    public ResponseEntity<?> getAllUsers(@RequestParam (defaultValue = "0") Integer page,
                                         @RequestParam (defaultValue = "10") Integer limit,
                                         @RequestParam(defaultValue = "id") String sortBy){
        return new ResponseEntity<>(userService.getAllUsers(page, limit, sortBy), HttpStatus.OK);
    }

    // 2. Tìm kiếm user theo tên.
    @GetMapping("/search")
    public ResponseEntity<?> getUserByName(@RequestParam String name){
        return new ResponseEntity<>(userService.getUserByName(name), HttpStatus.OK);
    }

    // 3. Lấy chi tiết user theo id.
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id){
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    // 4. Tạo mới user.
    @PostMapping("")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest request){
        return new ResponseEntity<>(userService.createUser(request), HttpStatus.CREATED);
    }

    // 5. Cập nhật thông tin user.
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id,
                                        @Valid @RequestBody UserRequest request){
        return new ResponseEntity<>(userService.updateUser(id, request), HttpStatus.OK);
    }

    // 6. Xóa User.
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // 7. Thay đổi ảnh avatar.
    @PutMapping("/{id}/update-avatar")
    public ResponseEntity<?> updateAvatar(@PathVariable Integer id,@RequestBody UpdateAvatarRequest request){
        userService.updateAvatar(id, request);
        return ResponseEntity.noContent().build();
    }

    // 8. Thay đổi mật khẩu.
    @PutMapping("/users/{id}/update-password")
    public ResponseEntity<?> updatePassword(@PathVariable Integer id,@Valid @RequestBody UpdatePasswordRequest request){
        userService.updatePassword(id, request);
        return ResponseEntity.noContent().build();
    }

    //9. Quên mật khẩu.
    @PostMapping("/users/{id}/forgot-password")
    public ResponseEntity<?> forgotPassword(@PathVariable Integer id){
        return new ResponseEntity<>(userService.forgotPassword(id), HttpStatus.OK);
    }
}
