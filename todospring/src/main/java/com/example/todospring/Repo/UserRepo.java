package com.example.todospring.Repo;

import com.example.todospring.Entity.UserModel;
import com.example.todospring.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Users,Long> {

    Boolean existsByEmail(String email);



    Optional<Users> findByEmail(String email);


}
