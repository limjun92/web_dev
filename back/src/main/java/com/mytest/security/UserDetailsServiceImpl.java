package com.mytest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{

	//private final PasswordEncoder passwordEncoder; //추가
	//private final Logger LOGGER = (Logger) LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	@Autowired
	private UserDtoMapper userDtoMapper;
	
	@Override
	public UserDetails loadUserByUsername(String user_id) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		//LOGGER.info("[loadUserByUsername] loadUserByUsername 수행. username : {}", username);
		//System.out.println("보안 : Serviec :" + user_id);
		return userDtoMapper.getByUid(user_id);
	}

}
