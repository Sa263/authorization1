package com.auth.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.auth.model.UserEntity;
import com.auth.repository.UserRepository;


@Service
public class CustomerDetailsService implements UserDetailsService {

    @Autowired(required = true)
    private UserRepository userDao;


    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity user = userDao.findByUsername(username);
        return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }

}
