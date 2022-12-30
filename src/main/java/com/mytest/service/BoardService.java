package com.mytest.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytest.dto.Board;
import com.mytest.dto.BoardSearch;
import com.mytest.dto.User;
import com.mytest.mappers.BoardMapper;

@Service
public class BoardService {
	
	@Autowired
    private BoardMapper boardMapper;
	
	//allBoard
	//mybatis parameterType여러개 추가하고 싶을때는 map을 활용한다.
    public List<Board> getBoardList(int pageNum, int pageSize, String user_id) {
    	//Map<String, Integer> paging = new HashMap<>();
    	//paging.put("pageStartNum", (pageNum-1) * 5);
    	//paging.put("pageSize", pageSize);
    	//paging.put("user_id", user_id);
    	//System.out.println(paging.get("pageStartNum") + " " + paging.get("pageSize"));
    	
    	System.out.println("==================");
    	BoardSearch b = new BoardSearch(pageNum, pageSize, user_id);
    	
        return boardMapper.getBoardList(new BoardSearch((pageNum-1) * 5, pageSize, user_id));
    }
    
}
