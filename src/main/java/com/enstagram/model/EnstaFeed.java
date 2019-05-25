package com.enstagram.model;

import java.util.Date;

public class EnstaFeed {

	private int feed_num;
	private String file_name;
	private String description;
	private int heart = 0;
	private Date regdate;
	private int accnt_num;

	public int getFeed_num() {
		return feed_num;
	}

	public void setFeed_num(int feed_num) {
		this.feed_num = feed_num;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getHeart() {
		return heart;
	}

	public void setHeart(int heart) {
		this.heart = heart;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public int getAccnt_num() {
		return accnt_num;
	}

	public void setAccnt_num(int accnt_num) {
		this.accnt_num = accnt_num;
	}
}
