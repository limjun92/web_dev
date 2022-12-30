package com.mytest.dto;

import java.util.List;

import lombok.Data;

@Data
public class User {
    private String row_id;
    private String user_id;
    private String user_nm;
    private String password;
    private String user_phone_num;
    private String user_email; 
    private String birth_dt;
    private int boardNum;
    private List<Board> board;
}