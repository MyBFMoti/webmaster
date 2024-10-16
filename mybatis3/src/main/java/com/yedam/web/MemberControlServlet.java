package com.yedam.web;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.dao.MemberMapper;
import com.yedam.vo.Member;

//객체생성 -> init() => service() ->  destroy() 

//HttpServlet 상속받기
@WebServlet("/member.action")//("/member.html")
public class MemberControlServlet extends HttpServlet {
	
	public MemberControlServlet() {
		System.out.println("MemberControl 객체 생성");
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("최초요청이면 init 실행");
	}
	//새로고침할 때마다
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		
		//get:조회화면, post:삭제처리
		String id = req.getParameter("mid");	//http://http://localhost/mybatis3/member.action?mid=user01
		
		SqlSession sqlSession = DataSource.getInstance().openSession(true);
		MemberMapper dao = sqlSession.getMapper(MemberMapper.class);
		if(req.getMethod().equals("GET")) {
			
			Member member = dao.selectMember(id);
			System.out.println("서블릿을 요청할때마다 실행");
		
			
			if(member == null) {
				resp.getWriter().print("조회된 정보가 없습니다.");
				return;
			}
		
			String str ="<h3>회원목록</h3>";
			str +="<form action='member.action' method='post'";
			str +="<input type='hidden' name='mid' value='"+member.getMemberId()+"'>";
			str +=" <table border = '1'>";
		
			str += "<tr><td><b>아이디</b></td>";
			str +="<td>" + member.getMemberId() +"</td>";
			str +="</tr>";
		
			str += "<tr><td><b>이름</b></td>";
			str +="<td>" +member.getMemberName()+"</td>";
			str +="</tr>";
		
			str +="<tr>";
			str += "<td><b>비밀번호</b></td>";
			str +="<td>" +member.getPassword()+"</td>";
			str +="</tr>";
		
			str += "<tr><td><b>연락처</b></td>";
			str +="<td>" +member.getPhone()+"</td>";
			str +="</tr>";
		

			str+="<tr><td colspan='2'><input type='submit'></td><tr>";
		
			str +="</table>";
			str +="</form>";
			str += "<a href='MemberListServlet'>목록으로</a>";
			resp.getWriter().print(str);
		
		} 
//		else if (req.getMethod().equals("POST")) {		//삭제처리
//			if(dao.deleteMember(member)==1) {
//				resp.getWriter().print("<p>삭제완료</p>");
//			} else {
//				resp.getWriter().print("<p>삭제할 회원없음</p>");
//			}
//			resp.getWriter().print("<a href='MemberListServlet'>목록으로</a>");
//		}
			
	}
	
	
	@Override
	public void destroy() {
		System.out.println("서버가 종료될 때 한번 실행");
	}
}
