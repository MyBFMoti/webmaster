package com.yedam.control.reply;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.Control;
import com.yedam.service.ReplyService;
import com.yedam.service.ReplyServiceImpl;
import com.yedam.vo.ReplyVO;

public class AddReplyCont implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/json;charset=UTF-8");
		
		// parameter: bno, reply, replyer
		String bno= req.getParameter("bno");
		String reply= req.getParameter("reply");
		String replyer= req.getParameter("replyer");
		
		ReplyVO rvo = new ReplyVO();
		rvo.setBoardNo(Integer.parseInt(bno));
		rvo.setReply(reply);
		rvo.setReplyer(replyer);
		
		ReplyService svc = new ReplyServiceImpl();
		//반환값을 선언
		Map<String, Object> result = new HashMap<>();
		
		
		try {
			// {reCode:OK, retVal:rvo}
			//성공
			svc.registerReply(rvo);
			
			result.put("retCode", "OK");
			result.put("retVal", rvo);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			//실패
			result.put("retCode", "FAIL");
			result.put("retVal", null);
		}
		
		Gson gson = new GsonBuilder().create();
		resp.getWriter().print(gson.toJson(result));

	}

}
