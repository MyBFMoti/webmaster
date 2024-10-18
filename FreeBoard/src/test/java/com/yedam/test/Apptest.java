package com.yedam.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.common.SearchDTO;
import com.yedam.mapper.BoardMapper;
import com.yedam.vo.BoardVO;

public class Apptest {
	public static void main(String[] args) {
		SqlSession sqlSession = DataSource.getInstance().openSession();
		BoardMapper mapper =sqlSession.getMapper(BoardMapper.class);
		
		
		SearchDTO search = new SearchDTO();
		search.setKeyword("JSP");
		search.setSearchCondition("T");
		search.setPage(2);
//		BoardVO bvo = new BoardVO();
//		bvo.setTitle("mapper테스트");
//		bvo.setContent("정상 작동합니다");
//		bvo.setWriter("user01");
//		
//		
//		
//		
//		bvo.setContent("정상 작동합니다[수정]");
//		bvo.setBoardNo(7);
		
		//수정
//		if(mapper.updateBoard(bvo) == 1) {
//			sqlSession.commit();
//		}
		
		//삭제
//		if(mapper.deleteBoard(bvo.getBoardNo()) == 1) {
//			sqlSession.commit();
//		}
		
		
		
		
		
		//검색
		
		
//		List<BoardVO> list = mapper.boardList();
//		for(BoardVO bvo2 : list) {
//			System.out.println(bvo2.toString());
//		}
		
		//페이징- 5개씩 출력 listWithPage(페이지)
		List<BoardVO> list2 = mapper.listWithPage(search);
		for(BoardVO bvo3 : list2) {
			System.out.println(bvo3.toString());
		}
		
		
		
	}
}
