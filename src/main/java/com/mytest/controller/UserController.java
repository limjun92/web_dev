package com.mytest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mytest.dto.user;
import com.mytest.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
	
	@Autowired
    private UserService userService;
    
    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/alluser")
    public List<user> allUsers() {
    	System.out.println("??");
    	System.out.println("Controller");
        return userService.getUserList();
    }
    
    @PostMapping("/user")
   // public ResponseEntity<?> userLogin(@RequestBody user uservo) {
	public ResponseEntity<?> userLogin(@RequestBody user uservo) {
		//System.out.println(uservo);
		//user re = userService.loginCheck(uservo);
		System.out.println(uservo.toString());
		//return ResponseEntity.status(HttpStatus.OK).body(re);
		
		return ResponseEntity.status(HttpStatus.OK).body(uservo);
	}

}
