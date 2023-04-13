package com.mytest.coin.controller;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mytest.coin.dto.CoinAllInfoDto;
import com.mytest.coin.service.CoinService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CoinController {
	
	@Autowired
	private CoinService coinService;
	
    @GetMapping("/CoinDashBoard/{user_id}")
    public String getCoinDashBoard(@PathVariable int user_id) {
    	return coinService.getCoinDashBoard(user_id);
    }
    
    //내가 가진 각 코인 정보 조회
    @GetMapping("/coinOrder/{user_id}/{coinNm}")
    public String getCoinOrder(@PathVariable int user_id, @PathVariable String coinNm) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    	System.out.println(user_id + " " +  coinNm);
    	return coinService.getCoinOrder(user_id, coinNm);
    }
    
    //DB정보 조회
    @GetMapping("/CoinAllInfo/{user_id}")
    public List<CoinAllInfoDto> CoinAllInfo(@PathVariable int user_id){
    	return coinService.getCoinAllInfo(user_id);
    }
    
    //DB업데이트
    @PostMapping("/coinInfoUpdate")
	public List<CoinAllInfoDto> coinInfoUpdate(@RequestBody CoinAllInfoDto coinInfoUpdate) {
    	return coinService.coinInfoUpdate(coinInfoUpdate);
	}
    
    //현재가 조회
    @GetMapping("/getCurrentPrice/{markets}")
    public String getCurrentPrice(@PathVariable String markets) throws IOException {
    	return coinService.getCurrentPrice(markets);
    }
}
