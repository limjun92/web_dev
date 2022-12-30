package com.mytest.dto;

import lombok.Data;

@Data
public class BoardSearch {
	private String Writer_id;
	private String Title;
	private String Content;
	private int BoardNum;
	private Board boardList;
}
