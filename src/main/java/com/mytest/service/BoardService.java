package com.mytest.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytest.dto.Board;
import com.mytest.mappers.BoardMapper;

@Service
public class BoardService {
	
	@Autowired
    private BoardMapper boardMapper;
	
	//allBoard
	//mybatis parameterType여러개 추가하고 싶을때는 map을 활용한다.
    public List<Board> getBoardList(int pageNum, int pageSize) {
    	Map<String, Integer> paging = new HashMap<>();
    	paging.put("pageStartNum", (pageNum-1) * 5);
    	paging.put("pageSize", pageSize);
    	System.out.println(paging.get("pageStartNum") + " " + paging.get("pageSize"));
        return boardMapper.getBoardList(paging);
    }
}
