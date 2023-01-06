package com.mytest.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mytest.dto.UserLogin;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserDtoController {
	
	//@Autowired
	//private UserDetailsServiceImpl userDetailsServiceImpl;

	private final JwtTokenProvider jwtTokenProvider;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	
//	public UserDtoController(JwtTokenProvider jwtTokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
//		this.authenticationManagerBuilder = authenticationManagerBuilder;
//		this.jwtTokenProvider = jwtTokenProvider;
//	}
	
    @PostMapping("/login")
	public ResponseEntity<TokenDto> login(@RequestBody UserLogin UserIdPw) {
    	System.out.println("보안 : Controller" + UserIdPw.toString());
    	
    	UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(UserIdPw.getUser_id(), UserIdPw.getPassword());
    	
    	System.out.println("C authenticationToken "+ authenticationToken);
    	//loaUserByUsername이 실행된다.
    	System.out.println("C authenticationManagerBuilder" + authenticationManagerBuilder);
    	System.out.println("??");
    	Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
    	System.out.println("??2");
    	System.out.println(authentication);
    	
    	SecurityContextHolder.getContext().setAuthentication(authentication);
    	
    	String jwt = jwtTokenProvider.creatdToken(authentication);
    	
    	HttpHeaders httpHeaders = new HttpHeaders();
    	httpHeaders.add(JwtAuthenticationFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
    	
    	System.out.println(jwt);
    	System.out.println();
    	
    	return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
		//return ResponseEntity.status(HttpStatus.OK).body(userDetailsServiceImpl.loadUserByUsername(UserIdPw.getUser_id()));
	}
}
