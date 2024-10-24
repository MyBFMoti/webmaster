package com.yedam.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.mapper.EventMapper;



public class EventServiceImpl implements EventService{
	SqlSession sqlSession = DataSource.getInstance().openSession(true);
	EventMapper mapper = sqlSession.getMapper(EventMapper.class);
	@Override
	public List<Map<String, Object>> selectEvent() {
		// TODO Auto-generated method stub
		return mapper.selectEvent();
	}
	@Override
	public int registerEvent(Map<String, String> map) {
		// TODO Auto-generated method stub
		return mapper.insertEvent(map);
	}
	@Override
	public int removeEvent(String title) {
		// TODO Auto-generated method stub
		return mapper.deleteEventByTitle(title);
	}
	

}
