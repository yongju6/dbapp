package com.korea.dbapp.domain.user;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.korea.dbapp.domain.post.Post;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Identity로 하면 mariadb / mysql / oracle 알아서 바뀜
	private int id; // 프라이머리키 (기본키)
	
	@Column(unique = true, length = 20)
	private String username; // 로그인할 때 쓰는 아이디
	private String password;
	private String email;
	private String address;
	
	// 여기에 post 적으려면 컬렉션으로 들어가야함
	//user - one , post - many
	@JsonIgnoreProperties({"user", "title"}) // user만 json으로 파싱하지 말라는 뜻
	@OneToMany(mappedBy = "user") // mappedBy = "post의 User 타입의 변수 이름" ==> posts가 외래키가 아니라고 알려줌
	private List<Post> posts;
	// id가 1일때 post에 user_id가 1인 것들을 조인해서 다 들고와줌 
	// 하지만 @OneToMany때문에 외래키가 만들어지는데 그렇게 되면 안됨!
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", address=" + address + "posts=" + posts + "]";
	}
	
	public List<Post> getPosts() {
		return posts;
	}
	
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
