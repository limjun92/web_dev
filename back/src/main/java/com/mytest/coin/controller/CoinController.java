package com.mytest.coin.controller;


import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mytest.coin.dto.CoinAllInfoDto;
import com.mytest.coin.service.CoinService;
import com.mytest.dto.Board;
import com.mytest.dto.User;
import com.mytest.service.BoardService;

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
    
    @GetMapping("/CoinOrder/{user_id}")
    public void getCoinOrder(@PathVariable int user_id) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    	coinService.getCoinOrder(user_id);
    }
    
    @GetMapping("/CoinAllInfo/{user_id}")
    public List<CoinAllInfoDto> CoinAllInfo(@PathVariable int user_id){
    	return coinService.getCoinAllInfo(user_id);
    }
    
    @PostMapping("/coinInfoUpdate")
	public List<CoinAllInfoDto> coinInfoUpdate(@RequestBody CoinAllInfoDto coinInfoUpdate) {
    	return coinService.coinInfoUpdate(coinInfoUpdate);
	}
}
