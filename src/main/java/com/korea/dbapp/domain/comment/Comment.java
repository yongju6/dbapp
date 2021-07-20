package com.korea.dbapp.domain.comment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.korea.dbapp.domain.post.Post;
import com.korea.dbapp.domain.user.User;

import lombok.Data;

@Data	//Getter,Setter, ToString을 만들어줌
@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; // 프라이머리키 (기본키)
	private String text;
	
	@JsonIgnoreProperties({"posts"})
	@JoinColumn(name="user_id")  // foreign key 의 key 이름을 user_id로 설정
	@ManyToOne
	private User user;	// 1 : N에서 user가 1 이므로 오브젝트로 선언
	
	@JsonIgnoreProperties({"user"})
	@JoinColumn(name="post_id")
	@ManyToOne
	private Post post;
	
}
