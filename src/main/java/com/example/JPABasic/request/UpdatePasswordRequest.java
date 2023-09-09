package com.example.JPABasic.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePasswordRequest {
    @NotBlank(message = "Password cannot be left blank")
    private String oldPassword;
    @Size(min = 8, max = 21, message = "Password must be between 8 and 21 characters")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()-_=+[{]};:',<.>/?]).*$",
            message = "Invalid password. Password must contain at least one uppercase letter, one lowercase letter, one number and one special character.")
    private String newPassword;
}
