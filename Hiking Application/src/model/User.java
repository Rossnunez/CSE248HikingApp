package model;

public class User {
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String image;
	private AccountType accountType;
	private HikingHistory hikingHistory;
	
	
	public UserInfo(String username, String password, String firstName, String lastName, String phoneNumber,
			String image, AccountType accountType, HikingHistory hikingHistory) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.image = image;
		this.accountType = accountType;
		this.hikingHistory = hikingHistory;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public HikingHistory getHikingHistory() {
		return hikingHistory;
	}
	public void setHikingHistory(HikingHistory hikingHistory) {
		this.hikingHistory = hikingHistory;
	}


	public AccountType getAccountType() {
		return accountType;
	}
	
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;	
	}

	@Override
	public String toString() {
		return "UserInfo [username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", phoneNumber=" + phoneNumber + ", image=" + image + ", accountType=" + accountType
				+ ", hikingHistory=" + hikingHistory + "]";
	}
}
