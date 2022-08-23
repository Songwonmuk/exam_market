package net.softsociety.exam.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.exam.domain.Board;
import net.softsociety.exam.domain.Reply;
import net.softsociety.exam.service.BoardService;

/**
 * 상품게시판 관련 콘트롤러
 */
@Slf4j
@RequestMapping("board")
@Controller
public class BoardController {
	
	@Autowired
	BoardService service;
	
	/**
	 * 게시판 글목록으로 이동
	 * @param page 현재 페이지
	 * @param type 검색 대상
	 * @param searchWord 검색어
	 */
	@GetMapping("list")
	public String list(Model model) {
		
		ArrayList<Board> boardlist = service.boardList();
		
		model.addAttribute("boardlist", boardlist);
		
		return "/boardView/list";
	}
	
	/**
	 * 글쓰기 폼으로 이동
	 */
	@GetMapping("write")
	public String write() {
		return "/boardView/writeForm";
	}
	
	/**
	 * 글 저장 
	 * @param board 사용자가 폼에 입력한 게시글 정보
	 * @param user 인증정보
	 * @param upload 첨부 파일
	 */
	@PostMapping("write")
	public String write(
			Board board
			, @AuthenticationPrincipal UserDetails user
			) {
		
		log.debug("저장할 글정보 : {}", board);
		
		board.setMemberid(user.getUsername());
		
		int result = service.insertBoard(board);
		return "redirect:/board/list";
	}
	
	/**
	 * 글 읽기 
	 * @param boardnum 읽을 글번호
	 */
	@GetMapping("read")
	public String read(
			Model model
			, @RequestParam(name="boardnum", defaultValue = "0") int boardnum) { 

		Board board = service.read(boardnum);
		if (board == null) {
			return "redirect:/board/list"; //글이 없으면 목록으로
		}
		
		//현재 글에 달린 리플들
		ArrayList<Reply> replylist = service.listReply(boardnum);
		
		//결과를 모델에 담아서 HTML에서 출력
		model.addAttribute("board", board);
		model.addAttribute("replylist", replylist);
		return "/boardView/read";
	}	
	
	/**
	 * 글 삭제
	 * @param boardnum 삭제할 글 번호
	 * @param user 인증정보
	 */
	@GetMapping ("delete")
	public String delete(int boardnum, @AuthenticationPrincipal UserDetails user) {
		
		//해당 번호의 글 정보 조회
		Board board = service.read(boardnum);
		
		if (board == null) {
			return "redirect:/board/list";
		}
		
		//로그인 아이디를 board객체에 저장
		board.setMemberid(user.getUsername());
		
		//글 삭제
		int result = service.delete(board);
		
		return "redirect:/board/list";
	}
	
	/**
	 * 글 삭제
	 * @param boardnum 삭제할 글 번호
	 * @param user 인증정보
	 */
	@GetMapping ("buy")
	public String buy(int boardnum, @AuthenticationPrincipal UserDetails user) {
		
		//해당 번호의 글 정보 조회
		Board board = service.read(boardnum);
		
		if (board == null) {
			return "redirect:/board/list";
		}
		
		//구매자 아이디를 board객체에 저장
		board.setBuyerid(user.getUsername());
		
		//상품 구매
		int result = service.buy(board);
		
		return "redirect:/board/list";
	}
	
	/**
	 * 리플 저장
	 * @param reply 저장할 리플 정보
	 */
	@PostMapping("writeReply")
	public String writeReply(
		Reply reply
		, @AuthenticationPrincipal UserDetails user) {
		
		reply.setMemberid(user.getUsername());
		
		log.debug("저장할 리플 정보 : {}", reply);
		
		int result = service.writeReply(reply);
		
		return "redirect:/board/read?boardnum=" + reply.getBoardnum();
	}
	
	
	@GetMapping("search")
	public String list() {
		
		return "boardView/search";
	}
	
	@ResponseBody
	@GetMapping("categorySearch")
	public ArrayList<Board> searchedList(String category){
		
		ArrayList<Board> searchedList = service.category(category);
		
		return searchedList;
	}
	
	@ResponseBody
	@GetMapping("wordSearch")
	public ArrayList<Board> wordList(String searchWord, String category){
		
		log.debug("받은 값 {}:" ,searchWord);
		log.debug("카테고리{}:", category);
		
		//현재 카테고리 리스트
		ArrayList<Board> searchedList = service.category(category);
		//새로운 리스트
		ArrayList<Board> newList = new ArrayList<>();
		//이름으로 찾은 리스트
		ArrayList<Board> wordList = service.wordList(searchWord);
		for(Board board : searchedList) {
			if(wordList.contains(board)) {
				newList.add(board);
			}
		}
		return newList;
	}
}
