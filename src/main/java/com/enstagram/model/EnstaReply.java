package com.enstagram.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class EnstaReply {

	private int reply_num;
	private int accnt_num;
	private int feed_num;
	private String comment;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
	private Date regdate;
	private int parent_num;

	public int getReply_num() {
		return reply_num;
	}

	public void setReply_num(int reply_num) {
		this.reply_num = reply_num;
	}

	public int getAccnt_num() {
		return accnt_num;
	}

	public void setAccnt_num(int accnt_num) {
		this.accnt_num = accnt_num;
	}

	public int getFeed_num() {
		return feed_num;
	}

	public void setFeed_num(int feed_num) {
		this.feed_num = feed_num;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public int getParent_num() {
		return parent_num;
	}

	public void setParent_num(int parent_num) {
		this.parent_num = parent_num;
	}

}
