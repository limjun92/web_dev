package com.mytest.coin.dto;

import java.sql.Timestamp;

import lombok.Data;

//DB정보 관련 내용 가져오는 DTO
@Data
public class CoinAllInfoDto {

	private int Coin_Id;
	private int User_Id;
	private String Coin_Nm;
	private String Type;
	private int Buy_Cnt;
	private int Sell_Cnt;
	private String Use_Yn;
	private double Price;
	private double Fix;
	private double Lock_Top;
	private double Lock_Bottom;
	private float Use_Krw;
	private float Back_Krw;
	private float Re_Get;
	private Timestamp Created_dt;
	private Timestamp Updated_dt;
	
}
