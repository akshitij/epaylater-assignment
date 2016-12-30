package com.epaylater.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Profile {
	
	private String firstName;
	private String lastName;
	private String phoneNo;
	private String emailId;
	private String alternateEmailId;
	
	public Profile() {
		super();
	}

	public Profile(String firstName, String lastName, String phoneNo, String emailId, String alternateEmailId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNo = phoneNo;
		this.emailId = emailId;
		this.alternateEmailId = alternateEmailId;
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
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getAlternateEmailId() {
		return alternateEmailId;
	}
	public void setAlternateEmailId(String alternateEmailId) {
		this.alternateEmailId = alternateEmailId;
	}
	
	public int updateProfile(Profile p){
		DatabaseHandler dbhandle = new DatabaseHandler();
		int status = dbhandle.performUpdate(p);
		return status;
	}
	
	public Profile getProfile(String key){
		DatabaseHandler dbhandle = new DatabaseHandler();
		Profile p = dbhandle.retrieve(key);
		return p;
	}
}
