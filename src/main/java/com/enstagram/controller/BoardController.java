package com.enstagram.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.enstagram.model.Board;
import com.enstagram.service.BoardService;

@RestController
public class BoardController {
	/*
	 * Get board Table DB
	 */
	@Autowired
	BoardService boardService;

	@RequestMapping("/api/board")
    public List<Board> query() throws Exception{
        return boardService.getAll();
    }

}
