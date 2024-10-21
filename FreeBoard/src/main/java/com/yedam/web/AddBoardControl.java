package com.yedam.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yedam.common.Control;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;

public class AddBoardControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//문자형식
		req.setCharacterEncoding("UTF-8");
		String savePath = req.getServletContext().getRealPath("images");
		int maxSize = 1024 * 1025 * 5;
		
		//Multipart 요청에 대한 처리로 변경
		MultipartRequest mr = new MultipartRequest(
				req									//1.요청정보(req)
				,savePath							//2.저장정보(webapp/images)
				,maxSize							//3.최대 크기
				,"UTF-8"							//4.encoding 방식
				,new DefaultFileRenamePolicy()		//5.리네임정책(rename)
				);
		
		//title, content, writer 3개의 파라미터(boardForm.jsp) -> db 등록 - > 목록 보여주기
		//key=value&key=value text처리
		String title = mr.getParameter("title");
		String content = mr.getParameter("content");
		String writer = mr.getParameter("writer");
		String img = mr.getFilesystemName("img");
		
		BoardVO board = new BoardVO();
		board.setTitle(title);
		board.setContent(content);
		board.setWriter(writer);
		board.setImg(img);
		
		
		BoardService svc = new BoardServiceImpl();
		try {
			//정상등록 - 글목록 이동
			svc.registerBoard(board);
			resp.sendRedirect("boardList.do");
		} catch (Exception e) {
			//비정상처리 - 등록화면으로 다시 이동
			req.setAttribute("msg", "등록하는 중 오류가 발생했습니다");
			req.getRequestDispatcher("WEB-INF/jsp/boardForm.jsp").forward(req, resp);
		}
		


	}

}
