package com.yedam.vo;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {
	private int 	boardNo; //board_no = boardNo
	private String 	title;
	private String 	content;
	private String 	writer;
	private String 	writerName;
	private int    	viewCnt;
	private Date   	writeDate;
	private Date   	updateDate;
	
}
