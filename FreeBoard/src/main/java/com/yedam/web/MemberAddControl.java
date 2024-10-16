package com.yedam.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.MemberService;
import com.yedam.service.MemberServiceImpl;
import com.yedam.vo.MemberVO;

public class MemberAddControl implements Control{

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("MemberAddControl");
		String id = req.getParameter("mid");
		String nm = req.getParameter("mname");
		String pw = req.getParameter("passwd");
		String ph = req.getParameter("phone");
		
		MemberVO mvo = new MemberVO();
		mvo.setMemberId(id);
		mvo.setMemberName(nm);
		mvo.setPassword(pw);
		mvo.setPhone(ph);
		
		MemberService svc = new MemberServiceImpl();
		try {
			//목록 페이지로 이동
			svc.addMember(mvo);
			System.out.println("회원 추가 성공");
			resp.sendRedirect("memberList.do");
		} catch (Exception e) {
			//등록화면으로 이동
			e.printStackTrace();
			System.out.println("회원 추가 실패");
			resp.sendRedirect("memberAddForm.do");
		}
		
	}

}
