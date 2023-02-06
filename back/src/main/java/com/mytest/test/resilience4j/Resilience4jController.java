package com.mytest.test.resilience4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Resilience4jController {
	
	@Autowired
	public Resilience4jService resilience4jService;
	
	@GetMapping("/resilience4j")
    public String test() throws Exception{
		System.out.println("==================controller");
        return resilience4jService.getCircuitBreaker();
    }
}
