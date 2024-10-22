package com.yedam.control.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.common.PageDTO;
import com.yedam.common.SearchDTO;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;

public class BoardListControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String page = req.getParameter("page");
		String sc   = req.getParameter("searchCondition");
		String kw   = req.getParameter("keyword");
		//삼항연산자: 페이지가 null이면 1페이지로 있으면 해당 페이지로
		page = page==null ? "1" :page;
		
		
		//검색 조건
		SearchDTO search = new SearchDTO();
		search.setKeyword(kw);
		search.setSearchCondition(sc);
		search.setPage(Integer.parseInt(page));
		
		//글 목록 보여주기, 조회 후 jsp에 전달
		BoardService svc = new BoardServiceImpl();
		List<BoardVO> list = svc.boardList(search);
		//총 항목 수
		int totalCnt = svc.getTotalCount(search);
		
		
		req.setAttribute("boardList", list);
		req.setAttribute("page", new PageDTO(Integer.parseInt(page), totalCnt));
		req.setAttribute("searchCondition", sc);
		req.setAttribute("keyword", kw);
		
		//jsp페이지
		req.getRequestDispatcher("board/boardList.tiles").forward(req, resp);
		//req.getRequestDispatcher("WEB-INF/jsp/boardList.jsp").forward(req, resp);
		
	}

}
