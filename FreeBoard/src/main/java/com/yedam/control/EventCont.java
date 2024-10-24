package com.yedam.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.Control;
import com.yedam.service.EventService;
import com.yedam.service.EventServiceImpl;

public class EventCont implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		resp.setContentType("text/json;charset=UTF-8");
		
		String job = req.getParameter("job");
		
		EventService evc = new EventServiceImpl();
		
		
		
		String json;
		if(job.equals("list")) {
			
			List<Map<String, Object>> list = evc.selectEvent();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			json = gson.toJson(list);
			
			resp.getWriter().print(json);
		}else if(job.equals("add")) {
			String title = req.getParameter("title");
			String start = req.getParameter("start");
			String end = req.getParameter("end");
			Map<String, String> map = new HashMap<>();
			map.put("title", title);
			map.put("start", start);
			map.put("end", end);
			
			try {
				if(evc.registerEvent(map) == 1) {
					json= "{\"retCode\": \"OK\"}";
				} else {
					json= "{\"retCode\": \"FAIL\"}";
				}
				resp.getWriter().print(json);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}else if(job.equals("remove")) {
			String title = req.getParameter("title");
			
			try {
				if(evc.removeEvent(title) != 0) {
					json= "{\"retCode\": \"OK\"}";
				} else {
					json= "{\"retCode\": \"FAIL\"}";
				}
				resp.getWriter().print(json);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
		}
		
		
		
		
		
	}

}
