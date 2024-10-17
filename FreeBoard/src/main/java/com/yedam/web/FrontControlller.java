package com.yedam.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;

@WebServlet("*.do")
public class FrontControlller extends HttpServlet {
	
	Map<String, Control> map;
	
	public FrontControlller() {
		//System.out.println("객체생성");
		
		map = new HashMap<>();
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		//System.out.println("init호출");
		
		map.put("/memberList.do", new MemberListControl());
		//회원등록 1.등록화면 2.등록처리
		map.put("/memberAddForm.do", new MemberAddFormControl());
		map.put("/memberAdd.do", new MemberAddControl());
		
		
		//게시판 관련
		map.put("/boardList.do", new BoardListControl());
		map.put("/board.do", new BoardControl());
		
		//글 등록(등록화면 -> 등록처리)
		map.put("/addBoardForm.do", new AddBoardForm());
		map.put("/addBoard.do", new AddBoardControl());
		
		//글 수정(수정화면 -> 변경처리)
		map.put("/modifyBoard.do", new ModifyBoardControl());
		
		//글 삭제(삭제화면 -> 삭제처리)
		map.put("/removeBoard.do", new RemoveBoardControl());
	}
	

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//System.out.println("service호출");
		
		//요청페이지?
		String uri = req.getRequestURI(); //                  /FreeBoard/add.do
		String context = req.getContextPath();//              FreeBoard
		String page = uri.substring(context.length());//       /add.do
		
		Control control = map.get(page);
		control.exec(req, resp);
		
	}
}
