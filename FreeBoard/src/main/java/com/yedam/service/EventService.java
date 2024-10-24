package com.yedam.service;

import java.util.List;
import java.util.Map;

public interface EventService {
	List<Map<String, Object>> selectEvent();

	int registerEvent(Map<String, String> map);
	
	int removeEvent(String title);
}
