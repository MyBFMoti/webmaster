package com.yedam.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.common.SearchDTO;
import com.yedam.mapper.BoardMapper;
import com.yedam.vo.BoardVO;

public class BoardServiceImpl implements BoardService{

	SqlSession sqlSession = DataSource.getInstance().openSession(true);
	BoardMapper mapper =sqlSession.getMapper(BoardMapper.class);
	
	@Override
//	public List<BoardVO> boardList() {	//페이징 추가전
//		return mapper.boardList();
//	}
//	public List<BoardVO> boardList(int page) {	//검색 추가전
//		return mapper.listWithPage(page);
//	}
	public List<BoardVO> boardList(SearchDTO search) {
		return mapper.listWithPage(search);
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

	@Override
	public int getTotalCount(SearchDTO search) {
		// TODO Auto-generated method stub
		return mapper.selectCount(search);
	}

	@Override
	public List<Map<String, Object>> countByWriter() {
		// TODO Auto-generated method stub
		return mapper.countByWriter();
	}
	
}
