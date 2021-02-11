package model;

import java.io.Serializable;
import java.util.TreeSet;

public class User implements Serializable{
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String image;
	private AccountType accountType;
	private Status status;
	private TreeSet<HikingHistory> hikingHistorySet;
	private TreeSet<HikingUncompleted> hikingUncompletedSet;

	public User(String username, String password, String firstName, String lastName, String phoneNumber, String image,
			AccountType accountType, TreeSet<HikingHistory> hikingHistorySet,
			TreeSet<HikingUncompleted> hikingUncompletedSet, Status status) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.image = image;
		this.accountType = accountType;
		this.hikingHistorySet = hikingHistorySet;
		this.hikingUncompletedSet = hikingUncompletedSet;
		this.status = status;
	}

	public int getUncompletedHikeSize() {
		return hikingUncompletedSet.size();
	}
	
	public int getHikingHistorySize() {
		return hikingHistorySet.size();
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

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public TreeSet<HikingHistory> getHikingHistorySet() {
		return hikingHistorySet;
	}

	public void setHikingHistorySet(TreeSet<HikingHistory> hikingHistorySet) {
		this.hikingHistorySet = hikingHistorySet;
	}

	public TreeSet<HikingUncompleted> getHikingUncompletedSet() {
		return hikingUncompletedSet;
	}

	public void setHikingUncompleted(TreeSet<HikingUncompleted> hikingUncompletedSet) {
		this.hikingUncompletedSet = hikingUncompletedSet;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", phoneNumber=" + phoneNumber + ", image=" + image + ", accountType=" + accountType
				+ ", hikingHistorySet=" + hikingHistorySet + ", hikingUncompleted=" + hikingUncompletedSet + ", status= " + status + "]";
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
