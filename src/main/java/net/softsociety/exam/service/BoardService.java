package net.softsociety.exam.service;

import java.util.ArrayList;

import net.softsociety.exam.domain.Board;
import net.softsociety.exam.domain.Reply;

public interface BoardService {

	ArrayList<Board> boardList();

	int insertBoard(Board board);

	Board read(int boardnum);

	ArrayList<Reply> listReply(int boardnum);

	int delete(Board board);

	int buy(Board board);

	int writeReply(Reply reply);

	ArrayList<Board> category(String category);

	ArrayList<Board> wordList(String searchWord);


}
