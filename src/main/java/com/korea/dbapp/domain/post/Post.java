package com.korea.dbapp.domain.post;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.korea.dbapp.domain.user.User;

@Entity
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Identity로 하면 mariadb / mysql / oracle 알아서 바뀜
	private int id; // 프라이머리키 (기본키)
	private String title; // varchar(255) - 255 글자수 
	@Lob // longText로 해줌 오라클은 다른걸로 들어감 , 알아서 자기 타입에 맞게 들어감(jpa 문법임)
	private String content; 
	
	@JoinColumn(name="user_id")
	@ManyToOne(fetch = FetchType.LAZY) // 연관관계 설정 / 연관관계를 설정해야 ORM이 됨 
	private User user; // ORM 사용
	// post 만 select 하면 user도 매핑해줌
	
	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", content=" + content + ", user=" + user + "]";
	}

	public User getUser() {
		return user;
	}

	

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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


	
	
}
