package com.wdd.test.bean;

import java.util.Date;

public class User {
	
	private Long id;
	private String username;
	private String pw;
	private String state;
	private Date createTime;
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public User(Long id, String usernamel, String password, String state, Date createTime) {
		super();
		this.id = id;
		this.username = usernamel;
		this.pw = password;
		this.state = state;
		this.createTime = createTime;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPw() {
		return pw;
	}


	public void setPw(String pw) {
		this.pw = pw;
	}


	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
