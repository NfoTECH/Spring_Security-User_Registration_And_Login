package com.decagon.oxygen.services;

import com.decagon.oxygen.entities.User;
import com.decagon.oxygen.pojos.APIResponse;
import com.decagon.oxygen.pojos.AuthRequest;
import com.decagon.oxygen.repositories.UserRepository;
import com.decagon.oxygen.security.JwtService;
import com.decagon.oxygen.utils.Responder;
import com.decagon.oxygen.utils.Utility;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class UserService {


    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;
    private final UserRepository userRepository;

    private  final Utility util;
    private final Responder responder;

    private final PasswordEncoder passwordEncoder;


    public ResponseEntity<APIResponse> createUser(User user) {

        User existingUser = userRepository.findByUsername(user.getUsername()).orElse(null);
        if(existingUser==null) { // if user does not exist
            user.setUuid(util.generateUniqueId()); //generate unique id of user
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User result = userRepository.save(user);
            return responder.Okay(result);
        }else{
            return responder.AlreadyExist("User Already Exist");
        }
    }

    public ResponseEntity<APIResponse> authenticate(AuthRequest request){
        System.out.println("I am here !!!!!!!!!!!!!!!!!!");
        Authentication auth= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        if(auth.isAuthenticated()){
           String token="Bearer "+jwtService.generateToken(new org.springframework.security.core.userdetails.User(request.getUsername(),request.getPassword(),new ArrayList<>()));
           return  responder.Okay(token);
        }else{
            return  responder.UnAuthorize("Authentication Failed");
        }
    }

    public ResponseEntity<APIResponse> getUsers(){
        return responder.Okay("#####This is the list of the users............");
    }

}
