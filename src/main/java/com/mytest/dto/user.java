package com.mytest.dto;

import lombok.Data;

@Data
public class user {
    private String id;
    private String user_id;
    private String user_nm;
    private String password;
    private String user_phone_num;
    private String user_email; 
    private String birth_dt;
}
