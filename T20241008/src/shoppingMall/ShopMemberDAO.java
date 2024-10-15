package shoppingMall;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShopMemberDAO extends DAO{

	private static ShopMemberDAO instance = new ShopMemberDAO();
	private ShopMemberDAO() {}
	public static ShopMemberDAO getInstance() {
		return instance;
	}
	
	//회원 조회(관리자 전용)
	public List<ShopMember> members(){
		String sql ="SELECT member_id, "
				+ "         login_id, "
				+ "         password, "
				+ "         member_name, "
				+ "         member_address, "
				+ "         phone, "
				+ "         responsibility, "
				+ "         TO_CHAR(registration_date, 'YYYY-MM-DD') AS registration_date"
				+ "  FROM   shop_member "
				+ "  ORDER BY member_id ";
		getConn();
		List<ShopMember> result = new ArrayList<ShopMember>();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				ShopMember member = new ShopMember();
				member.setMemberId(rs.getInt("member_id"));
				member.setLoginId(rs.getString("login_id"));
				member.setPassword(rs.getString("password"));
				member.setMemberName(rs.getString("member_name"));
				member.setMemberAddress(rs.getString("member_address"));
				member.setPhone(rs.getString("phone"));
				member.setResponsibility(rs.getString("responsibility"));
				member.setCreatedDate(rs.getString("registration_date"));
				result.add(member);
			}
			psmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getClose();
		return result;
	}
	
	//회원 추가(회원 가입)
	public int insert(ShopMember member) {
		String sql = ""
			+ "INSERT INTO shop_member (member_id, login_id, password, member_name,  member_address, phone) "
			+ "VALUES (seq_member_no.NEXTVAL, ?, ?, ?, ?, ?) ";
				
		getConn();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, member.getLoginId());
			psmt.setString(2, member.getPassword());
			psmt.setString(3, member.getMemberName());
			psmt.setString(4, member.getMemberAddress());
			psmt.setString(5, member.getPhone());
				
			int row = psmt.executeUpdate();
			psmt.close();
			getClose();
			
			return row;
			
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("유효하지 않는 입력으로 인한 가입 실패");
		}
		getClose();
		return 0;
	}
	
	//로그인 중인 회원 정보 수정
	public int memberInfoUpdate(ShopMember member, String currentId) {
		String sql = 
				"UPDATE shop_member "+
				"SET    login_id=?, password=?, member_name=?, phone=? "+
				"WHERE  login_id=? ";
		getConn();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, member.getLoginId());
			psmt.setString(2, member.getPassword());
			psmt.setString(3, member.getMemberName());
			psmt.setString(4, member.getPhone());
			psmt.setString(5, currentId);
			
			int row = psmt.executeUpdate();
			psmt.close();
			getClose();
			return row;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getClose();
		return 0;
	}
	
	//회원 삭제(탈퇴)
	public int delete(String id) {
		String sql = "DELETE "+
					 "FROM   shop_member "+
					 "WHERE  login_id=?";
		getConn();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			int row = psmt.executeUpdate();
			
			getClose();
			
			return row;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getClose();
		return 0;
	}
	
	//수정할 때 아이디 중복 확인
	public boolean checkId(String id) {
		String sql = "SELECT COUNT(*) "+
					 "FROM shop_member "+
					 "WHERE login_id = ?";
		getConn();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if(rs.next() && rs.getInt(1) > 0) {
				return true;
			}
			psmt.close();
			getClose();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getClose();
		return false;
	}
	
	//로그인 체크 및 현재 로그인 한 회원 정보담기
	ShopMember checkMember(String id, String pw) {
		String sql = "SELECT member_id, "+
				     "       login_id, "+
					 "		 password, "+
					 "       member_name, "+
					 "       member_address, "+
					 "		 phone, "+
				 	 "       responsibility "+
				 	 "FROM   shop_member " +
				 	 "WHERE  login_id = ? " +
				 	 "  AND  password  = ? ";
		getConn();
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				ShopMember member = new ShopMember();
				member.setMemberId(rs.getInt("member_id"));
				member.setLoginId(rs.getString("login_id"));
				member.setPassword(rs.getString("password"));
				member.setMemberName(rs.getString("member_name"));
				member.setMemberAddress(rs.getString("member_address"));
				member.setPhone(rs.getString("phone"));
				member.setResponsibility(rs.getString("responsibility"));
				
				psmt.close();
				getClose();
				return member;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getClose();
		return null;
	}
	
}
