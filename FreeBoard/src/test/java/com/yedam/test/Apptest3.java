package com.yedam.test;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.service.EventService;
import com.yedam.service.EventServiceImpl;

public class Apptest3 {
	public static void main(String[] args) {
//		BoardService svc = new BoardServiceImpl();
//		List<Map<String, Object>> result = svc.countByWriter();
//		
//		Gson gson = new GsonBuilder().setPrettyPrinting().create();
//		String json = gson.toJson(result);
//		
//		System.out.println(json);
		
		EventService evc = new EventServiceImpl();
		List<Map<String, Object>> result = evc.selectEvent();
		
		
		
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(result);
		
		System.out.println(json);
	}
}
