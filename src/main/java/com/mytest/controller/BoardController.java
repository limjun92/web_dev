package com.mytest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mytest.dto.Board;
import com.mytest.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
    @GetMapping("/BoardList/{pageNum}/{pageSize}")
    public List<Board> getBoardList(@PathVariable int pageNum, @PathVariable int pageSize) {
    	System.out.println("Board_List " + pageSize);
        return boardService.getBoardList(pageNum, pageSize);
    }
}
