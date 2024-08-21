package com.example.todospring.Controler;

import com.example.todospring.Entity.AuthenticationResponse;
import com.example.todospring.Entity.Authenticationrequest;
import com.example.todospring.Entity.UserModel;
import com.example.todospring.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserControl {

    @Autowired
    private UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<AuthenticationResponse> createnewuser(@RequestBody UserModel userModel){
        return ResponseEntity.ok(userService.createuser(userModel));
    }

    @PostMapping("/login")
    public  ResponseEntity<AuthenticationResponse> login(@RequestBody Authenticationrequest authenticationrequest){
        return ResponseEntity.ok(userService.login(authenticationrequest));
    }
}
