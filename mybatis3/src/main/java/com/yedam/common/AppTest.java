package com.yedam.common;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yedam.dao.MemberMapper;
import com.yedam.vo.Member;

public class AppTest {
	public static void main(String[] args) {
		//MemberDAO dao = new MemberDAO();
		SqlSession sqlSession = DataSource.getInstance().openSession();
		MemberMapper dao = sqlSession.getMapper(MemberMapper.class);
		
		//등록
		
//		Member mbr = new Member();
//		mbr.setMemberId("test99");
//		mbr.setMemberName("연습99");
//		mbr.setPhone("010-9999-9999");
//		mbr.setPassword("999");
//		
//		if(dao.insertMember(mbr) == 1) {
//			 sqlSession.commit();
//		}
		
		//수정
		Member mbr2 = new Member();
		mbr2.setMemberId("test99");
		//mbr2.setMemberName("연습9999");
		mbr2.setPhone("010-8989-5559");
		mbr2.setPassword("5959");
		if(dao.updateMember(mbr2) > 0) {
			 sqlSession.commit();
		}
		
		//삭제
//		Member mbr = new Member();
//		mbr.setMemberId("test99");
//		if(dao.deleteMember(mbr)!= 0) {
//			sqlSession.commit();
//		}
		
		//List<Member> result = dao.memberList();
		List<Member> result = dao.members();
		for(Member member : result) {
			System.out.println(member.toString());
		}
	}
}
