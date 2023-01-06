package com.mytest.security;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDtoMapper {
	UserDto getByUid(String user_id);
}
