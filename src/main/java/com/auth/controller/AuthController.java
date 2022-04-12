package com.auth.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.auth.exception.UserNotFoundException;
import com.auth.model.AuthResponse;
import com.auth.model.UserLoginCredential;
import com.auth.model.UserToken;
import com.auth.service.UserService;


@Slf4j
@CrossOrigin(origins = "*")
@RestController
@EnableHystrix
public class AuthController {

    @Autowired
    private UserService service;

    @GetMapping("/health")
    @HystrixCommand(fallbackMethod = "check_fail", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000") })

    public int check() {
        log.info("Health method");
        return 200;
    }public int check_fail()  { return 404;}



    /* This is the Login Method */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginCredential userlogincredentials) throws UserNotFoundException {
        log.info("Login Method : login");
        return new ResponseEntity<UserToken>(this.service.login(userlogincredentials), HttpStatus.OK); }



    @GetMapping("/validate")
    public ResponseEntity<?> getValidity(@RequestHeader("Authorization") String jwtToken) {
        return new ResponseEntity<AuthResponse>(this.service.validate(jwtToken), HttpStatus.OK);}}



