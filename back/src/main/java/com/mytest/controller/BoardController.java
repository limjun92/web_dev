package com.mytest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mytest.dto.Board;
import com.mytest.dto.User;
import com.mytest.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
    @GetMapping("/BoardList/{pageNum}/{pageSize}/{user_id}")
    public User getBoardList(@PathVariable int pageNum, @PathVariable int pageSize, @PathVariable String user_id) {
    	System.out.println("Board_List " + pageSize + " " + pageNum + " " + user_id);
    	
        return boardService.getBoardList(pageNum, pageSize, user_id);
    }
    
    @GetMapping("/BoardDetail/{boardId}")
    public Board getBoardList(@PathVariable int boardId) {
        return boardService.getBoardDetail(boardId);
    }
    
    //등록
    @PostMapping("/BoardWrite")
	public Board BoardWrite(@RequestBody Board boardWrite) {
    	System.out.println(boardWrite.toString());
    	return boardService.setBoardWrite(boardWrite);
	}
    
    //수정
    @PostMapping("/BoardUpdate")
	public Board BoardUpdate(@RequestBody Board boardUpdate) {
    	System.out.println(boardUpdate.toString());
    	return boardService.setBoardUpdate(boardUpdate);
	}
}
