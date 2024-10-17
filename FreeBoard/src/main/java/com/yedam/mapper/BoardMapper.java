package com.yedam.mapper;

import java.util.List;

import com.yedam.vo.BoardVO;

public interface BoardMapper {
	//글목록
	List<BoardVO> boardList();
	List<BoardVO> listWithPage(int page);
	//글등록
	int insertBoard(BoardVO board);
	//글수정
	int updateBoard(BoardVO board);
	//글삭제
	int deleteBoard(int boardNo);
	//상세조회
	BoardVO selectBoard(int boardNo);
	//조회수 증가
	int updateCount(int boardNo);
}
