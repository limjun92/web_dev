package com.mytest.coin.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.mytest.coin.dto.CoinAllInfoDto;
import com.mytest.coin.dto.CoinKeyDto;
import com.mytest.coin.mapper.CoinMapper;

@Service
public class CoinService {

	@Autowired
	private CoinMapper coinMapper;
	
	private String serverUrl = "https://api.upbit.com";
	private String accessKey = "";
	private String secretKey = "";
	
	//내가 가진 코인 정보 조회
	public String getCoinDashBoard(int user_id) {
		System.out.println(coinMapper.getCoinKey(user_id));
		
		CoinKeyDto coinKey = coinMapper.getCoinKey(user_id);
		
		accessKey = coinKey.getAccess_Key();
        secretKey = coinKey.getSecret_Key();
        //String serverUrl = "https://api.upbit.com";

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        String jwtToken = JWT.create()
                .withClaim("access_key", accessKey)
                .withClaim("nonce", UUID.randomUUID().toString())
                .sign(algorithm);

        String authenticationToken = "Bearer " + jwtToken;

        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(serverUrl + "/v1/accounts");
            request.setHeader("Content-Type", "application/json");
            request.addHeader("Authorization", authenticationToken);
            
            
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            String data = EntityUtils.toString(entity, "UTF-8");
            		
            JSONArray jsonArray = new JSONArray(data);
            
            return jsonArray.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
	}

	public void getCoinOrder(int user_id) throws NoSuchAlgorithmException, UnsupportedEncodingException {

		long beforeTime = System.currentTimeMillis();
		
		CoinKeyDto coinKey = coinMapper.getCoinKey(user_id);
		
		accessKey = coinKey.getAccess_Key();
        secretKey = coinKey.getSecret_Key();
		
		HashMap<String, String> params = new HashMap<>();
        params.put("market", "KRW-BTC");

        ArrayList<String> queryElements = new ArrayList<>();
        for(Map.Entry<String, String> entity : params.entrySet()) {
            queryElements.add(entity.getKey() + "=" + entity.getValue());
        }
        
        String queryString = String.join("&", queryElements.toArray(new String[0]));

        System.out.println(queryString);
        
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
            HttpGet request = new HttpGet(serverUrl + "/v1/orders/chance?" + queryString);
            request.setHeader("Content-Type", "application/json");
            request.addHeader("Authorization", authenticationToken);

            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            
            long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
            long secDiffTime = afterTime - beforeTime; //두 시간에 차 계산
            System.out.println("시간차이(m) : "+secDiffTime);

            System.out.println(EntityUtils.toString(entity, "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	//DB 코인 정보 가져오기
	public List<CoinAllInfoDto> getCoinAllInfo(int user_id) {
		// TODO Auto-generated method stub
		return coinMapper.getCoinAllInfo(user_id);
	}
	
	//DB 코인 정보 업데이트
	public List<CoinAllInfoDto> coinInfoUpdate(CoinAllInfoDto coinInfoUpdate) {
		// TODO Auto-generated method stub
		System.out.println(coinInfoUpdate.toString());
		int flag = coinMapper.coinInfoUpdate(coinInfoUpdate);
		System.out.println(flag);
		return coinMapper.getCoinAllInfo(coinInfoUpdate.getUser_Id());
		
	}

}
