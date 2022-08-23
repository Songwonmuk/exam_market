package net.softsociety.exam.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import net.softsociety.exam.domain.Board;
import net.softsociety.exam.domain.Reply;

/**
 * 게시판 관련 매퍼
 */
@Mapper
public interface BoardDAO {

	ArrayList<Board> boardList();

	int insertBoard(Board board);

	Board selectBoard(int boardnum);

	ArrayList<Reply> replyList(int boardnum);

	int deleteBoard(Board board);

	int buyBoard(Board board);

	int writeReply(Reply reply);

	ArrayList<Board> searchedList(HashMap<String, String> map);

	ArrayList<Board> category(String category);

	ArrayList<Board> wordList(String searchWord);

}
