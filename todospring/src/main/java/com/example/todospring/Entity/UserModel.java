package com.example.todospring.Entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    private Long id;


    private String name;

    private  String email;

    private String password;

    private Long age = 0L;

    private Role role;
}
