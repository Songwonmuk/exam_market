package net.softsociety.exam.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 게시글 정보
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Data
public class Board {
	int boardnum;
	String memberid; 
	String title;
	String contents;
	String inputdate;
	String category; 
	String soldout;
	String buyerid;
}
