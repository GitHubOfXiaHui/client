package com.bupt.client.vo.post;

import java.util.Date;

import com.bupt.client.cipher.Encryptable;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Post implements Encryptable {
	
	@JsonIgnore
	private Long id;
	
	private String title;
	
	private String content;
	
	private String author;
	
	private long createTime = System.currentTimeMillis();
	
	@JsonIgnore
	public Date getCreateDate() {
		return new Date(createTime);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Post [title=" + title + ", content=" + content + ", author=" + author + ", createTime="
				+ getCreateDate() + "]";
	}
	
}
