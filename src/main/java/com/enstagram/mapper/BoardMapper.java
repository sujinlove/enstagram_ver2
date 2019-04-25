package com.enstagram.mapper;

import java.util.List;

import com.enstagram.model.Board;

//@Repository
public interface BoardMapper {

//	@Select({ "SELECT * FROM board" })
//	List<Board> getUserName();
	
	public List<Board> getAll() throws Exception;
}
