package com.mytest.dto;

import lombok.Data;

@Data
public class BoardSearch {

	private int page_start_num;
	private int page_size;
	private String user_id;
	
	public BoardSearch(int page_start_num, int page_size, String user_id) {
		
		this.page_start_num = page_start_num;
		this.page_size = page_size;
		this.user_id = user_id;
	}
}
