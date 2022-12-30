package com.mytest.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Board {
	private int Board_id;
	private String User_Id;
	private String Title;
	private String Content;
	private Timestamp Created_Dt;
	private Timestamp Updated_Dt;
}
