package shoppingMall;

public class ShopMember {
	private int memberId;
	private String loginId;
	private String password;
	private String memberName;
	private String memberAddress;
	private String phone;
	private String responsibility;
	private String createdDate;
	
	

	public ShopMember() {}
	
	public ShopMember(String loginId, String password, String memberName, String memberAddress, String phone) {
		this.loginId = loginId;
		this.password = password;
		this.memberName = memberName;
		this.memberAddress  = memberAddress;
		this.phone = phone;
		
	}
	public int getMemberId() {
		return memberId;
	}
	
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberAddress() {
		return memberAddress;
	}
	
	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getResponsibility() {
		return responsibility;
	}

	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
	@Override
	public String toString() {
		String loginIdS = String.format("%-10s", loginId);
		String passwordS = String.format("%-15s", password);
		String memberNameS = String.format("%-10s", memberName);
		if(memberAddress == null) {
			memberAddress = "null";
		}
		String addressS = String.format("%-40s", memberAddress);
		if(phone == null) {
			phone = "null";
		}
		String phoneS = String.format("%-15s", phone);

//		responsibility;
//		createdDate;
		
		String result = "[" + memberId + "]"+ "\t" + loginIdS + "\t" + passwordS + "\t" + memberNameS + 
				           "\t" + addressS +"\t" +phoneS +"\t" + responsibility + "\t" + createdDate;
		return result;
	}
	
}
