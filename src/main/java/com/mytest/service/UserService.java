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
	
	//alluser
    public List<user> getUserList() {
    	System.out.println("Service");
        return userMapper.getUserList();
    }
    
    //loginCheck
    public user userLogin(user UserIdPw) {
    	System.out.println("loginCheck " + userMapper.userLoginCheck(UserIdPw));
    	if(userMapper.userLoginCheck(UserIdPw) == 1) {
    		return userMapper.userLogin(UserIdPw);
    	}
    	return null;
    }   
}