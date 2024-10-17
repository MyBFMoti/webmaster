package com.yedam.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;

public class RemoveBoardControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// GET : 삭제 확인화면, POST : 삭제처리
		
		req.setCharacterEncoding("UTF-8");		//post 방식일 때 필요
		String bno = req.getParameter("bno");
		BoardService svc = new BoardServiceImpl();
		
		if(req.getMethod().equals("GET")) {
			BoardVO board = svc.searchBoard(Integer.parseInt(bno));
			
			req.setAttribute("boardvo", board);
			req.getRequestDispatcher("WEB-INF/jsp/removeForm.jsp").forward(req, resp);
			
		}else if (req.getMethod().equals("POST")) {
			
			BoardVO board = new BoardVO();
			board.setBoardNo(Integer.parseInt(bno));
			if(svc.removeBoard(board.getBoardNo())) {
				//정상처리 - 목록
				resp.sendRedirect("boardList.do");
			} else {
				board = svc.searchBoard(Integer.parseInt(bno));
				
				req.setAttribute("boardvo", board);
				req.setAttribute("msg", "삭제할 게시글이 없습니다.");
				req.getRequestDispatcher("WEB-INF/jsp/removeForm.jsp").forward(req, resp);
			}
		}

	}

}