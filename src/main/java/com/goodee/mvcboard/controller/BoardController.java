package com.goodee.mvcboard.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goodee.mvcboard.service.BoardService;
import com.goodee.mvcboard.vo.Board;
import com.goodee.mvcboard.vo.Boardfile;

import lombok.extern.slf4j.Slf4j;

@Slf4j // 디버깅 쓸때 필요함
@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	//board상세보기
	@GetMapping("/board/boardOne")
	public String boardOne(Model model, Board board) {
		Board boardOne = boardService.getBoardOne(board);
		model.addAttribute("boardOne", boardOne);
		return "/board/boardOne";
	}
	
	//게시판 수정
	@GetMapping("/board/modifyBoard")
	public String modifyBoard(Model model, HttpSession session, Board board) {
		model.addAttribute("boardNo", board.getBoardNo());
		model.addAttribute("loginMemberId", session.getAttribute("loginMemberId"));
		return "/board/modifyBoard";
	}
	@PostMapping("/board/modifyBoard")
	public String modifyBoard(Board board) {
		int row = boardService.modify(board);
		log.debug("\u001B[36m"+"row : "+row);
		return "redirect:/board/boardList"; 
	}
	//게시판 삭제 
		
	@PostMapping("/board/removeBoard")
	public String removeBoard(HttpServletRequest request, Boardfile boardfile) {
		String path = request.getServletContext().getRealPath("/upload/");
		int row = boardService.removeBoard(path, boardfile);
		log.debug("\u001B[36m"+"row : "+row);
		return "redirect:/board/boardList"; 
	}
	
	// 게시판 추가
	@GetMapping("/board/addBoard")
	public String addBoard() {
		return "/board/addBoard";
	}
	@PostMapping("/board/addBoard")
	public String addBoard(HttpServletRequest request ,Board board) { // 매개값을 request객체를 받는다 <- request api 직접호출
		String path = request.getServletContext().getRealPath("/upload/");
		int row = boardService.addBoard(board, path);
		log.debug("\u001B[36m"+"row : "+row);
		return "redirect:/board/boardList"; 
	}
	
	@GetMapping("/board/boardList")
	public String boardList(Model model, 
			HttpSession session,
			@RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
			@RequestParam(name = "rowPerPage", defaultValue = "10") int rowPerPage,
			@RequestParam(name = "localName", required = false) String localName) {
		Map<String,Object> resultMap = boardService.getBoardList(currentPage,rowPerPage, localName);
		
		// view로 넘길때는 다시 분리해서
		model.addAttribute("localNameList", resultMap.get("localNameList"));
		model.addAttribute("boardList", resultMap.get("boardList"));
		 
		model.addAttribute("lastPage", resultMap.get("lastPage"));
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("loginMemberId", session.getAttribute("loginMemberId"));
		model.addAttribute("localName", localName);
		
		
		return "/board/boardList";
	}
}
