package com.yedam.dao;

import java.util.List;

import com.yedam.vo.Member;

//interface 기능정의
//구현 클래스 기능실행
public interface MemberMapper {
	public List<Member> members();
	public int insertMember(Member member);
	public int updateMember(Member member);
	public int deleteMember(Member member);
}
