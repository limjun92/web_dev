package com.mytest.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mytest.dto.User;
import com.mytest.dto.UserLogin;

@Mapper
@Repository
public interface UserMapper {
	List<User> getUserList();
	int userLoginCheck(UserLogin UserIdPw);
	User userLogin(UserLogin UserIdPw);
}
