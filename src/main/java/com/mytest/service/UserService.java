package com.mytest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytest.dto.user;
import com.mytest.mappers.userMapper;

@Service
public class UserService{
	
	@Autowired
    private userMapper userMapper;

    public List<user> getUserList() {
    	System.out.println("Service");
        return userMapper.getUserList();
    }
    
}