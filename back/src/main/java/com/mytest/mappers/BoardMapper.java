package com.mytest.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mytest.dto.Board;
import com.mytest.dto.BoardSearch;
import com.mytest.dto.User;

@Mapper
@Repository
public interface BoardMapper {
	User getBoardList(BoardSearch boardSearch);
	Board getBoardDetail(int boardId);
	void setBoardWrite(Board boardWrite);
	int getBoardLastId();
	void setBoardUpdate(Board boardWrite);
}
