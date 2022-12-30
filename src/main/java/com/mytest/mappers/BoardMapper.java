package com.mytest.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mytest.dto.Board;
import com.mytest.dto.User;

@Mapper
@Repository
public interface BoardMapper {
	List<Board> getBoardList(Map paging);
	User getBoardSearchList(Map paging);
	Board getSelectBoardList();
}
