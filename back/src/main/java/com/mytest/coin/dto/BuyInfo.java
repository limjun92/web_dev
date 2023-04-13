package com.mytest.coin.dto;

import lombok.Data;

@Data
//살때 넘겨주는 값 저장 DTO
public class BuyInfo {
	private int userId;
	private int pay;
	private String market;
	private String type;
	private Float sellPrice;
	private Double nowPrice;
	private Float buyRatio; 
}
