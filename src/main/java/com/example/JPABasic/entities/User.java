package com.example.JPABasic.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "name", nullable = false, columnDefinition = "varchar(50)")
    String name;
    @Column(name = "email", nullable = false, unique = true, columnDefinition = "varchar(100)")
    String email;
    @Column(name = "phone", nullable = false, columnDefinition = "varchar(50)")
    String phone;
    @Column(name = "address", nullable = false)
    String address;
    @Column(name = "avatar")
    String avatar;
    @Column(name = "password", nullable = false)
    String password;
}
