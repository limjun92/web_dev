package com.mytest.security;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
//토큰의 생성과 토큰의 유효성 검증
public class JwtTokenProvider {
	private final UserDetailsService userDetailsService;
	
	private static final String AUTHORITIES_KEY = "auth";
	
	private Key key;
	private String secretKey = "webfirew1232tdsfg43r243r23r23r23r2fdsf12312312312312312312123123123123123123123123343423e2efdf436trhdgfhdr5yd5sfgdfg5sood";
	private final long tokenValidMillisecond = 1000L * 60 * 60;
	
	@PostConstruct
	protected void init(){
		System.out.println("초기화 시작");
		System.out.println("sercretKey :" +  secretKey);
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		System.out.println("Decoders sercretKey :" +  keyBytes.toString());
        this.key = Keys.hmacShaKeyFor(keyBytes);
        System.out.println("this.key :" +  this.key);
		//secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
		System.out.println("초기화 끝");
	}
	
//	public String creatdToken(String userUid, List<String> roles) {
//		System.out.println("토큰 생성");
//		Claims claims = Jwts.claims().setSubject(userUid);
//		claims.put("roles", roles);
//		Date now = new Date();
//		
//		String token = Jwts.builder()
//				.setClaims(claims)
//				.setIssuedAt(now)
//				.setExpiration(new Date(now.getTime()+tokenValidMillisecond))
//				.signWith(key, SignatureAlgorithm.HS256)
//				.compact();
//		
//		return token;
//	}
	
	public String creatdToken(Authentication authentication) {
		System.out.println("토큰 생성");
		String authorities = authentication.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));
		
		long now = (new Date()).getTime();
		Date validity = new Date(now + this.tokenValidMillisecond);
		System.out.println("토큰 생성 2");
		return Jwts.builder()
				.setSubject(authentication.getName())
				.claim(AUTHORITIES_KEY, authorities)
				.signWith(key, SignatureAlgorithm.HS512)
				.setExpiration(validity)
				.compact();
	}
	
	public Authentication getAuthentication(String token) {
		System.out.println("인증정보 조회 ");
		System.out.println(this.getUsername(token));
		UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUsername(token));
		
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}
	
	//수정 필요
	public String getUsername(String token) {
		String info = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
		return info;
	}
	
	public String resolveToken(HttpServletRequest request) {
		return request.getHeader("X-AUTH-TOKEN");
	}
	
	public boolean validateToken(String token) {
		try {
			Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		} catch(Exception e) {
			System.out.println("잘못했습니다");
		}
		return false;
	}
}
