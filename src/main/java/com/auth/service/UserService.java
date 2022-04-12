package com.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth.exception.UserNotFoundException;
import com.auth.model.AuthResponse;
import com.auth.model.UserEntity;
import com.auth.model.UserLoginCredential;
import com.auth.model.UserToken;
import com.auth.repository.UserRepository;



@Service
public class UserService {

    @Autowired
    private JwtUtil jwtutil;
    @Autowired
    private CustomerDetailsService custDetailService;
    @Autowired
    private UserRepository userservice;

    public UserToken login(UserLoginCredential user) throws UserNotFoundException {
        UserEntity entity = this.userservice.findByUsername(user.getUsername());
        if (entity == null) {
            throw new UserNotFoundException(" Please check your credentials.");
        }
        UserDetails userDetails = this.custDetailService.loadUserByUsername(user.getUsername());
        String token = jwtutil.generateToken(userDetails);
        String userName = entity.getUsername();
        String userType = entity.getRole();
        UserToken userToken = new UserToken(userName, token, userType);
        return userToken;
    }

    public AuthResponse validate(String jwtToken) {
        String token = jwtToken.substring(7);
        AuthResponse res = new AuthResponse();
        String userName = jwtutil.extractUsername(token);
        UserEntity user = userservice.findById(userName).get();
        res.setRole(user.getRole());
        res.setValid(jwtutil.validateToken(token));
        return res;
    }

}
