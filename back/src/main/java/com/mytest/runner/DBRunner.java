package com.mytest.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.sql.Connection;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class DBRunner implements ApplicationRunner{
	@Autowired
	private DataSource dataSource;
	
	//Logger 생성
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void run(ApplicationArguments args) throws Exception{
		System.out.println("??");
		System.out.println(dataSource.getClass().getName());
		Connection connection = dataSource.getConnection();
		logger.info("DB URL " + connection.getMetaData().getURL());
		System.out.println(connection.getMetaData().getURL());
	}
}
