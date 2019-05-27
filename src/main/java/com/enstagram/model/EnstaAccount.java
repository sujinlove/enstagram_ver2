package com.enstagram.model;

public class EnstaAccount {
	
	private int accnt_num;
	private String id;
	private String name;
	private String email;
	private String passwd;
	private String profile = "/profile/default.jpg";
    private int role = 1;

	public EnstaAccount() {}
	
	public EnstaAccount(String id, String passwd) {
		this.id = id;
		this.passwd = passwd;
	}
	
	public int getAccnt_num() {
		return accnt_num;
	}

	public void setAccnt_num(int accnt_num) {
		this.accnt_num = accnt_num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}
	
	public int getRole() {
		return role;
	}
	
	public void setRole(Integer role) {
		this.role = role;
	}
}
