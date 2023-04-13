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

import com.mytest.coin.dto.BuyInfo;
import com.mytest.coin.dto.CoinKeyDto;
import com.mytest.coin.dto.TradeInfo;
import com.mytest.coin.mapper.CoinMapper;
import com.mytest.coin.service.CoinBuySellService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CoinBuySellController {
	
	@Autowired
	private CoinBuySellService coinBuySellService;
	
	@Autowired
	private CoinMapper coinMapper;
	
	//구매테스트
	@PostMapping("/buyTest")
	public int buyTest(@RequestBody BuyInfo buyInfo) throws IOException {
		System.out.println("controller buyTest");
		return coinBuySellService.buyTest(buyInfo);
	}
	
    //구매
    @PostMapping("/buy")
	public int buy(@RequestBody BuyInfo buyInfo) throws NoSuchAlgorithmException, UnsupportedEncodingException, InterruptedException {
    	System.out.println("controller");
    	return coinBuySellService.buy(buyInfo);
	}
    
    //구매내역 조회
    @GetMapping("/order/{uuid}")
    public String order(@PathVariable String uuid) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    	
    	System.out.println(uuid);
    	CoinKeyDto coinKey = coinMapper.getCoinKey(1);
		
		String accessKey = coinKey.getAccess_Key();
		String secretKey = coinKey.getSecret_Key();
		String serverUrl = "https://api.upbit.com";
    	return coinBuySellService.order(uuid, accessKey, secretKey, serverUrl).toString();
	}
    
    //Trade 이력 조회
    @GetMapping("/selectTradeInfo")
    public List<TradeInfo> selectTradeInfo() {
    	return coinBuySellService.selectTradeInfo();
	}
    
//    @GetMapping("/allcoinInfoUpdateTest")
//    public int allcoinInfoUpdateTest() {
//    	return coinBuySellService.UpdateBuyCoinInfo("KRW-BTC", 200, 0.99);
//	}
    
    
//이력 Insert
//  @PostMapping("/buyInfoInsert")
//  public List<TradeInfo> buyInfoInsert(@RequestBody TradeInfo tradeInfo) {
//  	System.out.println(tradeInfo.toString());
//  	return coinBuySellService.buyInfoInsert(tradeInfo);
//	}
    
}
