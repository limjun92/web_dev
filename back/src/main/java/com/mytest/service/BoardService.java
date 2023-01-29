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
    public User getBoardList(int pageNum, int pageSize, String user_id) {
    	//Map<String, Integer> paging = new HashMap<>();
    	//paging.put("pageStartNum", (pageNum-1) * 5);
    	//paging.put("pageSize", pageSize);
    	//paging.put("user_id", user_id);
    	//System.out.println(paging.get("pageStartNum") + " " + paging.get("pageSize"));
    	
    	System.out.println("==================");
    	User BoardList = boardMapper.getBoardList(new BoardSearch((pageNum-1) * 5, pageSize, user_id));
    	
        return BoardList;
    }

	public Board getBoardDetail(int boardId) {
		// TODO Auto-generated method stub
		
		//Board BoardDetail = boardMapper.getBoardDetail(boardId);
		
		return boardMapper.getBoardDetail(boardId);
	}

	public Board setBoardWrite(Board boardWrite) {
		// TODO Auto-generated method stub
		boardMapper.setBoardWrite(boardWrite);
		//방금 등록한 게시글을 조회하기 위한 마지막 게시글Id를 가져온다.
		int boardId = boardMapper.getBoardLastId();
		return boardMapper.getBoardDetail(boardId);
	}

	public Board setBoardUpdate(Board boardUpdate) {
		// TODO Auto-generated method stub
		System.out.println("Update");
		boardMapper.setBoardUpdate(boardUpdate);
		
		return boardMapper.getBoardDetail(boardUpdate.getBoard_id());
	}

    
}
