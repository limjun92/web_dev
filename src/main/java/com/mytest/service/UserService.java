package com.mytest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytest.dto.User;
import com.mytest.mappers.UserMapper;

@Service
public class UserService{
	
	@Autowired
    private UserMapper userMapper;
	
	//alluser
    public List<User> getUserList() {
    	System.out.println("Service");
        return userMapper.getUserList();
    }
    
    //loginCheck
    public User userLogin(User UserIdPw) {
    	if(userMapper.userLoginCheck(UserIdPw) == 1) {
    		System.out.println("??");
    		System.out.println(UserIdPw.toString());
    		System.out.println(userMapper.userLogin(UserIdPw));
    		return userMapper.userLogin(UserIdPw);
    	}
    	System.out.println("????");
    	return null;
    }   
}