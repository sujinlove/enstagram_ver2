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

	@RequestMapping("/api")
    public List<Board> query() throws Exception{
        return boardService.getAll();
    }

//	@RequestMapping("/test")
//	public void testList() {
//		List<Board> list = boardMapper.getUserName();
//		for (Board board : list) {
//			System.out.println("======>" + board);
//		}
//	}

//	@RequestMapping("/")
//	public String index(Model model) {
//		model.addAttribute("eventName", "2019");
//		// String currentDir = System.getProperty("user.dir");
//		return "index";
//	}


}
