package com.yedam.mapper;

import java.util.List;
import java.util.Map;

import com.yedam.common.SearchDTO;
import com.yedam.vo.BoardVO;

public interface BoardMapper {
	//글목록(게시판 관련)
	List<BoardVO> boardList();
	List<BoardVO> listWithPage(SearchDTO search);
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
	//페이징 계산 건수 체크
	int selectCount(SearchDTO search);
	
	//사용자별 게시글 작성건수(차트)
	List<Map<String, Object>> countByWriter();
}
