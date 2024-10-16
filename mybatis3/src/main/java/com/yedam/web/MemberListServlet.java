package com.yedam.web;

import java.io.IOException;
import java.io.PrintWriter;
//import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.ibatis.session.SqlSession;


import com.yedam.common.DataSource;
import com.yedam.dao.MemberMapper;
import com.yedam.vo.Member;

//IOC (제어의 역전)
//객체생성 -> init() => service() ->  destroy() :  서블릿의 생명 주기
@WebServlet("/MemberListServlet")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public MemberListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		
		SqlSession sqlSession = DataSource.getInstance().openSession();
		MemberMapper dao = sqlSession.getMapper(MemberMapper.class);
		
		PrintWriter out = response.getWriter();
		List<Member> result = dao.members();
		String str ="<h3>회원목록</h3>";
		str +="<table border = 1 cellpadding=1 width=70% height='300'> ";
		str +="<tr>";
		str += "<th width=10%>아이디</th>";
		str += "<th width=10%>이름</th>";
		str += "<th width=10%>비밀번호</th>";
		str += "<th width=10%>연락처</th>";
		str += "<th width=10%>권한</th>";
		str += "<th width=20%>생성일</th>";
		str +="</tr>";
		for(Member member : result) {
			str +="<tr>";
			str +="<td width=10%><a href=member.action?mid=" + member.getMemberId()+">" +  member.getMemberId()+"</a></td>";
			str +="<td width=10%>" +member.getMemberName()+"</td>";
			str +="<td width=10%>" +member.getPassword()+"</td>";
			str +="<td width=10%>" +member.getPhone()+"</td>";
			str +="<td width=10%>" +member.getResponsibility()+"</td>";
			str +="<td width=20%>" +member.getCreationDate()+"</td>>";
			str +="</tr>";
			
			
		}
		str +="</table>";
		str +="<a href='./'>첫페이지</a>";
		out.print(str);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		doGet(request, response);
	}

}
