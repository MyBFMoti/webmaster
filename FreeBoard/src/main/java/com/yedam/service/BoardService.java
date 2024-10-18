package com.yedam.service;

import java.util.List;

import com.yedam.common.SearchDTO;
import com.yedam.vo.BoardVO;

public interface BoardService {
	//목록 변경, 등록, 삭제, 단건 조회
	//List<BoardVO> boardList();	//페이징 추가전
	//List<BoardVO> boardList(int page);	//검색 추가 전
	List<BoardVO> boardList(SearchDTO search);
	boolean registerBoard(BoardVO board);
	boolean removeBoard(int boardNo);
	boolean modifyBoard(BoardVO board);
	BoardVO searchBoard(int boardNo);
	
	//페이징 카운트
	int getTotalCount(SearchDTO search);
	
}
