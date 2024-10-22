package com.yedam.mapper;

import java.util.List;

import com.yedam.vo.ReplyVO;

public interface ReplyMapper {
	//댓글 출력
	List<ReplyVO> selectReplyList(int BoardNo);
	//댓글 삭제
	int deleteReply(int replyNo);
	//댓글 추가
	int insertReply(ReplyVO reply);
	//댓글 검색
	ReplyVO selectReply(int replyNo);
}
