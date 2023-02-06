package com.mytest.test.resilience4j;

import org.springframework.stereotype.Service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Resilience4jService {

//	@CircuitBreaker(
//            name = "A",
//            fallbackMethod = "Ba"
//    )
//	
//	public String A(boolean errYn) throws Exception {
//		System.out.println("===========================??");
//		if(errYn){
//            log.error("### call A exception");
//            throw new Exception();
//        }else{
//            log.error("### call A");
//        }
//        return "A";
//	}
//	
//    private String Ba(boolean errYn){
//        log.error("### call B");
//        return "B";
//    }
    @CircuitBreaker(name = Resilience4jCode.CIRCUIT_TEST_70000, fallbackMethod = "getCircuitBreakerFallback")
	public String getCircuitBreaker() {
    	System.out.println("controller??");
    	runtimeException();
		return "hello world!";
	}
    
    private void runtimeException() {
      throw new RuntimeException("failed");
    }
    private String getCircuitBreakerFallback(Throwable t) {
      return "getCircuitBreakerFallback! exception type: " + t.getClass() + "exception, message: " + t.getMessage();
    }
    

}
