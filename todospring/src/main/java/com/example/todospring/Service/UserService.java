package com.example.todospring.Service;

import com.example.todospring.Entity.AuthenticationResponse;
import com.example.todospring.Entity.Authenticationrequest;
import com.example.todospring.Entity.UserModel;
import com.example.todospring.Entity.Users;


public interface UserService {

    AuthenticationResponse createuser(UserModel userModel);


    AuthenticationResponse login(Authenticationrequest authenticationrequest);

    Users Getloggedinuser();
}
