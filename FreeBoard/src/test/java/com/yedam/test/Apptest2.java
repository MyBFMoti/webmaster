package com.yedam.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.mapper.ReplyMapper;
import com.yedam.vo.ReplyVO;
public class Apptest2 {
	public static void main(String[] args) {
		SqlSession sqlSession = DataSource.getInstance().openSession();
		ReplyMapper mapper =sqlSession.getMapper(ReplyMapper.class);
		
		ReplyVO reply = new ReplyVO();
		reply.setReplyer("user01");
		reply.setBoardNo(93);
		reply.setReply("댓글 테스트");
		
//		if(mapper.insertReply(reply) == 1) {
//			sqlSession.commit();
//		}
		
//		reply.setReplyNo(4);
//		if(mapper.deleteReply(reply.getReplyNo())==1) {
//			sqlSession.commit();
//		}
		List<ReplyVO> list = mapper.selectReplyList(93);
		for(ReplyVO rvo : list) {
			System.out.println(rvo.toString());
		}
		
		
		
		ReplyVO rvoSelected = mapper.selectReply(1);
		if(mapper.selectReply(1)!=null) {
			System.out.println(rvoSelected.toString());
			
		}
	}
}	
