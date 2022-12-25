package com.mytest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	
    @GetMapping("/BoardList")
    public List<Board> getBoardList() {
    	System.out.println("Board_List ");
        return boardService.getBoardList();
    }
}
