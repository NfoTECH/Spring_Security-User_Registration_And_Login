package com.decagon.oxygen.controllers;

import com.decagon.oxygen.entities.User;
import com.decagon.oxygen.pojos.APIResponse;
import com.decagon.oxygen.services.UserService;
import com.decagon.oxygen.utils.Responder;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {


    private final UserService userService;


    @PostMapping("/api/user/signup")
    public ResponseEntity<APIResponse> createUser(@RequestBody User request){
        return  userService.createUser(request);
    }

    @GetMapping("/api/users")
    public ResponseEntity<APIResponse>  getUsers(){
        return userService.getUsers();
    }
}
