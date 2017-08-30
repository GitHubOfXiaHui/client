package com.bupt.client.vo.post;

import java.util.Date;

import com.bupt.client.cipher.EncryptIgnore;
import com.bupt.client.cipher.Encryptable;

public class Post implements Encryptable {
	
	private String title;
	
	private String content;
	
	private String author;
	
	private long createTime = System.currentTimeMillis();
	
	@EncryptIgnore
	public Date getCreateDate() {
		return new Date(createTime);
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
