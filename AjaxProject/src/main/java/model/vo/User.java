package model.vo;

public class User {
	private int userNo;
	private String userId;
	private String userName;
	private String userAddress;
	
	public User() {}

	public User(int userNo) {
		this.userNo = userNo;
	}

	public User(int userNo, String userId, String userName, String userAddress) {
		this.userNo = userNo;
		this.userId = userId;
		this.userName = userName;
		this.userAddress = userAddress;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	@Override
	public String toString() {
		return "User [userNo=" + userNo + ", userId=" + userId + ", userName=" + userName + ", userAddress="
				+ userAddress + "]";
	}

	
}
