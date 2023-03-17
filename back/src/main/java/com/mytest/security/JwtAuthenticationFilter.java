package com.mytest.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

public class JwtAuthenticationFilter extends GenericFilterBean{
//public class JwtAuthenticationFilter extends OncePerRequestFilter{

	
	public static final String AUTHORIZATION_HEADER = "Authorization";
	
	private final JwtTokenProvider jwtTokenProvider;
	
	public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String jwt = resolveToken(httpServletRequest);
		String requestURI = httpServletRequest.getRequestURI();
		
		if(StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)) {
			Authentication authentication = jwtTokenProvider.getAuthentication(jwt);
			SecurityContextHolder.getContext().setAuthentication(authentication);
//			System.out.println("토큰이 있습니다.");
		}else {
//			System.out.println("토큰이 없습니다.");
		}
		
		chain.doFilter(request, response);
	}
	
	private String resolveToken(HttpServletRequest request) {
		String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
		if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}
		
//	@Override
//	protected void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain)
//			throws ServletException, IOException {
//		System.out.println("filter");
//		String token = jwtTokenProvider.resolveToken(servletRequest);
//		
//		if(token!=null && jwtTokenProvider.validateToken(token)) {
//			Authentication authentication = jwtTokenProvider.getAuthentication(token);
//			SecurityContextHolder.getContext().setAuthentication(authentication);
//		}
//		filterChain.doFilter(servletRequest, servletResponse);
//	}
	

}
