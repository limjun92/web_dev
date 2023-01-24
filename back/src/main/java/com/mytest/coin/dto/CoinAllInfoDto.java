package com.mytest.coin.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class CoinAllInfoDto {

	private int Coin_Id;
	private int User_Id;
	private String Coin_Nm;
	private String Type;
	private int Buy_Cnt;
	private int Sell_Cnt;
	private String Use_Yn;
	private int Price;
	private int Fix;
	private int Lock_Top;
	private int Lock_Bottom;
	private int Use_Krw;
	private int Back_Krw;
	private int Re_Get;
	private Timestamp Created_dt;
	private Timestamp Updated_dt;
	
}
