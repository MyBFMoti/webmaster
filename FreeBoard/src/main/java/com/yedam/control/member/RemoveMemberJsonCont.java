package com.yedam.control.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.MemberService;
import com.yedam.service.MemberServiceImpl;

public class RemoveMemberJsonCont implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = req.getParameter("id");
		
		MemberService svc = new MemberServiceImpl();
		if(svc.retireMember(id)) {
			//{"retCode": "OK"}
			resp.getWriter().print("{\"retCode\": \"OK\"}");
		}else {
			//{"retCode": "FAIL"}
			resp.getWriter().print("{\"retCode\": \"FAIL\"}");
		}
	}

}
