package com.mytest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytest.dto.Board;
import com.mytest.mappers.BoardMapper;

@Service
public class BoardService {
	
	@Autowired
    private BoardMapper boardMapper;
	
	//allBoard
    public List<Board> getBoardList() {
        return boardMapper.getBoardList();
    }
}
