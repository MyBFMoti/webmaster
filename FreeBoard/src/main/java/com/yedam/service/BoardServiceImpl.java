package com.yedam.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.mapper.BoardMapper;
import com.yedam.vo.BoardVO;

public class BoardServiceImpl implements BoardService{

	SqlSession sqlSession = DataSource.getInstance().openSession(true);
	BoardMapper mapper =sqlSession.getMapper(BoardMapper.class);
	
	@Override
//	public List<BoardVO> boardList() {	//페이징 추가전
//		return mapper.boardList();
//	}
	public List<BoardVO> boardList(int page) {
		return mapper.listWithPage(page);
	}

	@Override
	public boolean registerBoard(BoardVO board) {
		return mapper.insertBoard(board) == 1;
	}

	@Override
	public boolean removeBoard(int boardNo) {
		return mapper.deleteBoard(boardNo) == 1;
	}

	@Override
	public boolean modifyBoard(BoardVO board) {
		return mapper.updateBoard(board) == 1;
	}

	@Override
	public BoardVO searchBoard(int boardNo) {
		// 조회 & 조회수 증가
		mapper.updateCount(boardNo);
		return mapper.selectBoard(boardNo);
	}
	
}
