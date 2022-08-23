package net.softsociety.exam.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 리플 정보
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class Reply {
	int replynum;
	int boardnum;
	String memberid;
	String replytext;
	String inputdate;
}
