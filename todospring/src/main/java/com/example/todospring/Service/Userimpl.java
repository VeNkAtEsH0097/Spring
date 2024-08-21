package com.example.todospring.Service;

import com.example.todospring.Entity.*;
import com.example.todospring.Repo.UserRepo;
import com.example.todospring.config.Jwtservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class Userimpl implements UserService {

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Autowired
    private Jwtservice jwtservice;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private  UserRepo userRepo;

    @Override
    public AuthenticationResponse createuser(UserModel userModel) {

        if(userRepo.existsByEmail(userModel.getEmail())){
            throw new RuntimeException("User Already Exists ");
        }

        var user = Users.builder()
                .name(userModel.getName())
                .email(userModel.getEmail())
                .age(userModel.getAge())
                .password(passwordEncoder.encode(userModel.getPassword()))
                .role(Role.USER)
                .build();
        userRepo.save(user);

        var jwttoken= jwtservice.Generatetoken(user);
        return AuthenticationResponse.builder()
                .token(jwttoken)
                .build();
    }

    @Override
    public AuthenticationResponse login(Authenticationrequest authenticationrequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationrequest.getEmail(),
                authenticationrequest.getPassword()
        ));
        var user = userRepo.findByEmail(authenticationrequest.getEmail()).orElseThrow();
        var jwttoken= jwtservice.Generatetoken(user);
        return AuthenticationResponse.builder()
                .token(jwttoken)
                .build();

    }

    @Override
    public Users Getloggedinuser() {
       Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
       String Email=authentication.getName();

      return userRepo.findByEmail(Email).orElseThrow(()-> new UsernameNotFoundException("User not found for the email" +Email));
    }
}
