package com.yedam.service;


import java.util.List;

import com.yedam.vo.ReplyVO;

public interface ReplyService {
	//댓글 등록, 삭제, 단건 조회
	List<ReplyVO> replyList(int boardNo);
	List<ReplyVO> replyList(int boardNo, int page);
	boolean registerReply(ReplyVO reply);
	boolean removeReply(int replyNo);
	ReplyVO searchReply(int replyNo);
	
	//댓글 카운트
	int replyCount(int boardNo);
}
