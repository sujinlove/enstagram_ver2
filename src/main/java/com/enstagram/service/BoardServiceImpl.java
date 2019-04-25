package com.enstagram.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enstagram.mapper.BoardMapper;
import com.enstagram.model.Board;

@Service
//@Service("boardMapper")
public class BoardServiceImpl {	
//	@Override
//	public List<Board> getBoardList() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
//	@Override
//	public String print() {
//		return "Board test";
//	}
    @Autowired
    BoardMapper boardMapper;

    public List<Board> getAll() throws Exception{

        return boardMapper.getAll();

    }
	
}
