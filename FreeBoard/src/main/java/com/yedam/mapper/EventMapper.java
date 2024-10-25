package com.yedam.mapper;

import java.util.List;
import java.util.Map;

public interface EventMapper {
	List<Map<String, Object>> selectEvent();
	
	int insertEvent(Map<String, String> map);
	
	int deleteEventByTitle(String title);
	
	int updateEvent(Map<String, String> map);
}
