package com.mytest.coin.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.gson.Gson;
import com.mytest.coin.dto.BuyInfo;
import com.mytest.coin.dto.CoinKeyDto;
import com.mytest.coin.dto.TradeInfo;
import com.mytest.coin.mapper.CoinBuySellMapper;
import com.mytest.coin.mapper.CoinMapper;


@Service
public class CoinBuySellService {
	
	@Autowired
	private CoinBuySellMapper coinBuySellMapper;
	
	@Autowired
	private CoinMapper coinMapper;
	
	@Autowired
	private CoinService coinService;
	
	//구매Test
	public int buyTest(BuyInfo buyInfo) throws IOException {
		System.out.println("service buyTest");
		
		String CurrentPrice = coinService.getCurrentPrice(buyInfo.getMarket());
		
		JSONArray jsonArray = new JSONArray(CurrentPrice);
				
		JSONObject jsonObject = (JSONObject) jsonArray.get(0);
		
		float price = Float.parseFloat(jsonObject.get("trade_price").toString());
		
		DateFormat dateFormat = new SimpleDateFormat("EEE yyyy MM dd HH:mm:ss z");
		String date = dateFormat.format(new Date());
		buyInfoInsert(
				new TradeInfo(
						"testId",
						buyInfo.getUserId(),
						buyInfo.getMarket(),
						"t",
						Double.parseDouble(new BigDecimal(buyInfo.getPay()/price).toString()),
						getSellPrice(buyInfo.getSellPrice()),
						(double) buyInfo.getNowPrice(),
						(float) (buyInfo.getPay() + buyInfo.getPay() * 0.0005),
						"N",
						date
						)
				);
		
		UpdateBuyCoinInfo(buyInfo.getMarket(), (float) (buyInfo.getPay() + buyInfo.getPay() * 0.0005), buyInfo.getBuyRatio());
		
		return 0;
	}
	
	//구매
	public int buy(BuyInfo buyInfo) throws NoSuchAlgorithmException, UnsupportedEncodingException, InterruptedException {
		System.out.println("service");
		
		// 1. 키값을 가져온다
		CoinKeyDto coinKey = coinMapper.getCoinKey(buyInfo.getUserId());
		
		String accessKey = coinKey.getAccess_Key();
		String secretKey = coinKey.getSecret_Key();
		String serverUrl = "https://api.upbit.com";
		
		// 2. 구매를 한다.
		JSONObject buyObject = buyMethod(buyInfo.getMarket(), buyInfo.getPay(), accessKey, secretKey, serverUrl);
		Thread.sleep(1000);
		// 3. TradeInfo에 저장을 한다.
		if(buyObject != null) {
			
			//볼륨값을 가져오기 위해서 한번 더 확인이 필요
			JSONObject orderInfo = order(buyObject.get("uuid").toString(), accessKey, secretKey, serverUrl);
			
			buyInfoInsert(
					new TradeInfo(
						buyObject.get("uuid").toString(),
						buyInfo.getUserId(),
						buyInfo.getMarket(),
						buyInfo.getType(),
						Double.parseDouble(orderInfo.get("executed_volume").toString()),
						getSellPrice(buyInfo.getSellPrice()),
						buyInfo.getNowPrice(),
						Float.parseFloat(buyObject.get("locked").toString()),
						"N",
						buyObject.get("created_at").toString())
					);
		}
		
		//4. CoinAllInfo에 신규 정보를 업데이트 한다.
		int flag = 0;
		flag = UpdateBuyCoinInfo(buyInfo.getMarket(), Float.parseFloat(buyObject.get("locked").toString()), buyInfo.getBuyRatio());
		
		//5.업데이트가 정상적으로 되었다면 trade의 모든 정보를 가져와서 return한다.
		return flag;
	}
	
	//구매 후 내용 Insert
	public List<TradeInfo> buyInfoInsert(TradeInfo tradeInfo) {
		// TODO Auto-generated method stub
		if(coinBuySellMapper.buyInfoInsert(tradeInfo) == 1) {
			return selectTradeInfo();
		}
		return null;
	}
	
	//trade모든 내용 가져오기
	public List<TradeInfo> selectTradeInfo(){
		return coinBuySellMapper.selectTradeInfo();
	}
	
	//================Function===================
	
	// 4. CoinAllInfo에 신규 정보를 업데이트 한다.
	public int UpdateBuyCoinInfo(String market, float pay, double buyRatio) {
		Map<String, Object> map = new HashMap<>();
		//where 조회용
		map.put("market", market);
		//사용한 돈을 추가해준다
		map.put("pay", pay);
		//FIX변경요 buyRatio를 전달
		map.put("buyRatio", buyRatio);
		return coinBuySellMapper.UpdateBuyCoinInfo(map);
	}
	
	//구매 함수
	public JSONObject buyMethod(String market, int price, String accessKey, String secretKey, String serverUrl) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
		//CoinKeyDto coinKey = coinMapper.getCoinKey(user_id);
		
		//String accessKey = coinKey.getAccess_Key();
		//String secretKey = coinKey.getSecret_Key();
		//String serverUrl = "https://api.upbit.com";
		
		//		String accessKey = System.getenv(accessKey);
		//	    String secretKey = System.getenv(secretKey);
		//	    String serverUrl = System.getenv("https://api.upbit.com/v1/order");
	
	    HashMap<String, String> params = new HashMap<>();
	    params.put("market", market);
	    params.put("side", "bid");
	    //params.put("volume", "0.01");
	    params.put("price", Integer.toString(price));
	    params.put("ord_type", "price");
	
	    ArrayList<String> queryElements = new ArrayList<>();
	    for(Map.Entry<String, String> entity : params.entrySet()) {
	        queryElements.add(entity.getKey() + "=" + entity.getValue());
	    }
	
	    String queryString = String.join("&", queryElements.toArray(new String[0]));
	
	    MessageDigest md = MessageDigest.getInstance("SHA-512");
	    md.update(queryString.getBytes("UTF-8"));
	
	    String queryHash = String.format("%0128x", new BigInteger(1, md.digest()));
	
	    Algorithm algorithm = Algorithm.HMAC256(secretKey);
	    String jwtToken = JWT.create()
	            .withClaim("access_key", accessKey)
	            .withClaim("nonce", UUID.randomUUID().toString())
	            .withClaim("query_hash", queryHash)
	            .withClaim("query_hash_alg", "SHA512")
	            .sign(algorithm);
	
	    String authenticationToken = "Bearer " + jwtToken;
	
	    try {
	        HttpClient client = HttpClientBuilder.create().build();
	        HttpPost request = new HttpPost(serverUrl + "/v1/orders");
	        request.setHeader("Content-Type", "application/json");
	        request.addHeader("Authorization", authenticationToken);
	        request.setEntity(new StringEntity(new Gson().toJson(params)));
	        
	        System.out.println(params.toString());
	        System.out.println("request");
	        
	        HttpResponse response = client.execute(request);
	        HttpEntity entity = response.getEntity();
	
	        //System.out.println(EntityUtils.toString(entity, "UTF-8"));
	        
	        String data = EntityUtils.toString(entity, "UTF-8");   
            
	        JSONObject jsonObject = new JSONObject(data);
	        
            
            System.out.println("구매 :" +  jsonObject);
            
            return jsonObject;
	        
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	//===========조회 함수======================
	public JSONObject order(String uuid, String accessKey, String secretKey, String serverUrl) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		//String accessKey = System.getenv("UPBIT_OPEN_API_ACCESS_KEY");
        //String secretKey = System.getenv("UPBIT_OPEN_API_SECRET_KEY");
        //String serverUrl = System.getenv("UPBIT_OPEN_API_SERVER_URL");

        HashMap<String, String> params = new HashMap<>();
        params.put("uuid", uuid);

        ArrayList<String> queryElements = new ArrayList<>();
        for(Map.Entry<String, String> entity : params.entrySet()) {
            queryElements.add(entity.getKey() + "=" + entity.getValue());
        }

        String queryString = String.join("&", queryElements.toArray(new String[0]));

        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(queryString.getBytes("UTF-8"));

        String queryHash = String.format("%0128x", new BigInteger(1, md.digest()));

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        String jwtToken = JWT.create()
                .withClaim("access_key", accessKey)
                .withClaim("nonce", UUID.randomUUID().toString())
                .withClaim("query_hash", queryHash)
                .withClaim("query_hash_alg", "SHA512")
                .sign(algorithm);

        String authenticationToken = "Bearer " + jwtToken;

        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(serverUrl + "/v1/order?" + queryString);
            request.setHeader("Content-Type", "application/json");
            request.addHeader("Authorization", authenticationToken);

            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            
            String data = EntityUtils.toString(entity, "UTF-8");   
            
	        JSONObject jsonObject = new JSONObject(data);
	        
	        System.out.println("오더 :" +  jsonObject);

            return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
	}
	
	//===========원화 마켓 주문 가격 단위===========
	public double getSellPrice(double curPrice) {
		if(curPrice >= 2000000) {
			curPrice = curPrice - curPrice % 1000;
		}else if(curPrice >= 1000000) {
			curPrice = curPrice - curPrice % 500;
		}else if(curPrice >= 500000) {
			curPrice = curPrice - curPrice %100;
		}else if(curPrice >= 100000) {
			curPrice = curPrice - curPrice %50;
		}else if(curPrice >= 10000) {
			curPrice = curPrice - curPrice %10;
		}else if(curPrice >= 1000) {
			curPrice = curPrice - curPrice %5;
		}else if(curPrice >= 100) {
			curPrice = curPrice - curPrice %1;
		}else if(curPrice >= 10) {
			curPrice = ((curPrice*10) - (curPrice*10) % 1)/10;
		}else if(curPrice >= 1) {
			curPrice = ((curPrice*100) - (curPrice*100) % 1)/100;
		}else if(curPrice >= 0.1) {
			curPrice = ((curPrice*1000) - (curPrice*1000) % 1)/1000;
		}else{
			curPrice = ((curPrice*10000) - (curPrice*10000) % 1)/10000;
		}
		return curPrice;
	}




}
