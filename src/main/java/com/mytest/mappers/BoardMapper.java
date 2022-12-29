package com.mytest.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mytest.dto.Board;

@Mapper
@Repository
public interface BoardMapper {
	List<Board> getBoardList();
}