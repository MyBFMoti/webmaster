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
import com.yedam.control.CalendarCont;
import com.yedam.control.ChartControl;
import com.yedam.control.CountByWriterCont;
import com.yedam.control.EventCont;
import com.yedam.control.Exe1Cont;
import com.yedam.control.Exe2Cont;
import com.yedam.control.JavaScriptCont;
import com.yedam.control.board.AddBoardControl;
import com.yedam.control.board.AddBoardForm;
import com.yedam.control.board.BoardControl;
import com.yedam.control.board.BoardListControl;
import com.yedam.control.board.ModifyBoardControl;
import com.yedam.control.board.RemoveBoardControl;
import com.yedam.control.member.AddMemberJsonCont;
import com.yedam.control.member.LoginControl;
import com.yedam.control.member.LogoutControl;
import com.yedam.control.member.MemberAddControl;
import com.yedam.control.member.MemberAddFormControl;
import com.yedam.control.member.MemberJsonCont;
import com.yedam.control.member.MemberListControl;
import com.yedam.control.member.RemoveMemberJsonCont;
import com.yedam.control.reply.AddReplyCont;
import com.yedam.control.reply.RemoveReplyCont;
import com.yedam.control.reply.ReplyCountCont;
import com.yedam.control.reply.ReplyListCont;

//@WebServlet("*.do")		//web.xml에 추가했으므로 주석처리

public class FrontController extends HttpServlet {
	
	Map<String, Control> map;
	
	public FrontController() {
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
		
		//로그인 관련
		map.put("/loginForm.do", new LoginControl());
		
		//로그아웃 관련
		map.put("/logOut.do", new LogoutControl());
		
		//자바스크립트 관련
		map.put("/javascript.do", new JavaScriptCont());
		//json 관련
		map.put("/memberJson.do", new MemberJsonCont());
		map.put("/addMemberJson.do",  new AddMemberJsonCont());
		map.put("/removeMemberJson.do", new RemoveMemberJsonCont());
		
		//댓글(reply)관련
		map.put("/replyList.do", new ReplyListCont());
		map.put("/removeReply.do", new RemoveReplyCont());
		map.put("/addReply.do", new AddReplyCont());
		map.put("/replyCount.do", new ReplyCountCont());
		
		//차트 관련
		map.put("/chart.do", new ChartControl());
		map.put("/countByWriter.do", new CountByWriterCont());
		
		//event관련
		map.put("/calendar.do", new CalendarCont());
		map.put("/event.do", new EventCont());
		map.put("/addEvent.do", new EventCont());
		map.put("/removeEvent.do", new EventCont());
		
		
		//exe1연습
		map.put("/exe1.do", new Exe1Cont());
		map.put("/exe2.do", new Exe2Cont());
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
