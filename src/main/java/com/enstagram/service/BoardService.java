package com.enstagram.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.enstagram.mapper.BoardMapper;
import com.enstagram.model.Board;

@Service
public class BoardService {	
    @Autowired
    BoardMapper boardMapper;

    public List<Board> getAll() throws Exception{

        return boardMapper.getAll();

    }
	
}
