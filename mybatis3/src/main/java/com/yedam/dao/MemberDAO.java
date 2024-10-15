package com.yedam.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.common.DAO;
import com.yedam.vo.Member;

public class MemberDAO extends DAO {
	//회원목록\
	public List<Member> memberList(){
		List<Member> list = new ArrayList<Member>();
		getConn();
		
		try {
			psmt = conn.prepareStatement("SELECT * FROM tbl_member");
			rs = psmt.executeQuery();
			while(rs.next()) {
				Member member = new Member();
				member.setMemberId(rs.getString("member_id"));
				member.setPassword(rs.getString("password"));
				member.setMemberName(rs.getString("member_name"));
				member.setPhone(rs.getString("phone"));
				member.setResponsibility(rs.getString("responsibility"));
				member.setCreationDate(rs.getDate("creation_date"));
				list.add(member);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
			
			
		
		
		
	
	}
}
