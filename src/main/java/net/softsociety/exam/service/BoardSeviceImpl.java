package net.softsociety.exam.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.exam.dao.BoardDAO;
import net.softsociety.exam.domain.Board;
import net.softsociety.exam.domain.Reply;

@Transactional
@Service
@Slf4j
public class BoardSeviceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDAO;

	@Override
	public ArrayList<Board> boardList() {

		ArrayList<Board> list = boardDAO.boardList();
		
		return list;
	}

	@Override
	public int insertBoard(Board board) {

		int result = boardDAO.insertBoard(board);
		
		return result;
	}

	@Override
	public Board read(int boardnum) {
		
		Board board = boardDAO.selectBoard(boardnum);
		
		return board;
	}

	@Override
	public ArrayList<Reply> listReply(int boardnum) {
		
		ArrayList<Reply> replylist = boardDAO.replyList(boardnum);
		
		return replylist;
	}

	@Override
	public int delete(Board board) {

		int result = boardDAO.deleteBoard(board);
		
		return result;
	}

	@Override
	public int buy(Board board) {

		int result = boardDAO.buyBoard(board);
		return result;
	}

	@Override
	public int writeReply(Reply reply) {

		int result = boardDAO.writeReply(reply);
		return result;
	}

	@Override
	public ArrayList<Board> category(String category) {

		ArrayList<Board> list = boardDAO.category(category);
		return list;
	}

	@Override
	public ArrayList<Board> wordList(String searchWord) {

		ArrayList<Board> wordList = boardDAO.wordList(searchWord);
		
		return wordList;
	}

	

}
