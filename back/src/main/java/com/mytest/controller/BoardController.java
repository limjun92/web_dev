package com.mytest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mytest.dto.Board;
import com.mytest.dto.User;
import com.mytest.dto.UserLogin;
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
    
    @PostMapping("/BoardWrite")
	public Board BoardWrite(@RequestBody Board boardWrite) {
    	System.out.println(boardWrite.toString());
    	return boardService.setBoardWrite(boardWrite);
		//return ResponseEntity.status(HttpStatus.OK).body(userService.userLogin(UserIdPw));
	}
}
