package com.mytest.coin.dto;

import lombok.Data;

@Data
public class CoinAccounts {
	private String currency;
	private String balance;
	private String locked;
	private String avg_buy_price;
	private String avg_buy_price_modified;
	private String unit_currency;
}
