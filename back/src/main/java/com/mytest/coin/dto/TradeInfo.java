package com.mytest.coin.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class TradeInfo {

	private String tradeId;
	private int userId;
	private String market;
	private String type;
	private double volume;
	private double sellPrice;
	private double buyPrice;
	private float pay;
	private String sellYn;
	private String createdDt;
	
	public TradeInfo(String tradeId, int userId, String market, String type, double volume, double sellPrice,
			double buyPrice, float pay, String sellYn, String createdDt) {
		this.tradeId = tradeId;
		this.userId = userId;
		this.market = market;
		this.type = type;
		this.volume = volume;
		this.sellPrice = sellPrice;
		this.buyPrice = buyPrice;
		this.pay = pay;
		this.sellYn = sellYn;
		this.createdDt = createdDt;
	}
}
