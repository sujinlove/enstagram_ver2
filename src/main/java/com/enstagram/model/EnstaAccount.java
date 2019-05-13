package com.enstagram.model;

public class EnstaAccount {
	
	private int accnt_num;
	private String id;
	private String name;
	private String nickname;
	private String passwd;
    private int role = 1;
	private String loginError;

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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	public int getRole() {
		return role;
	}
	
	public void setRole(Integer role) {
		this.role = role;
	}
	
	public void setLoginError(String loginError) {
		this.loginError = loginError;
	}

}
