package com.korea.dbapp.test;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.korea.dbapp.domain.user.User;
import com.korea.dbapp.domain.user.UserRepository;

@RestController
public class UserAPIControllerTest {

	private final UserRepository userRepository;

	// 의존성 주입(DI; Dependency Injection)
	public UserAPIControllerTest(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@PostMapping("/test/user") // /user/insert 하지 않아도 annotation이 잡아줘서 괜찮음
	public String save(User user) {
		userRepository.save(user);
		return "save ok";
	}
	
	@GetMapping("/test/user")
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	// http://localhost:8000/user/2
	@GetMapping("/test/user/{id}")
	public String findById(@PathVariable int id) {  //@PathVariable 이 위에 {} 안에 있는 id 값에 넣어주는 역할
		User userEntity = userRepository.findById(id).get();
		userEntity.getPosts().get(0).getTitle();
		System.out.println(userEntity.toString());
		return "ok"; // 무조건 값이 존재한다는 뜻(.get)
	}
	
	@GetMapping("/test/user/username/{username}")
	public User findByUsername(@PathVariable String username) {
		
		return userRepository.mFindByUsername(username);
	}
	
	@PostMapping("/test/login")
	public String login(@RequestBody User user) {
		User userEntity = userRepository.mLogin(user.getUsername(), user.getPassword());
		
		if(userEntity != null) {
			return "Login Success";
		}else {
			return "Login Fail";
		}
		
	}
	
	@DeleteMapping("/test/user/{id}")
	public String deleteById(@PathVariable int id) {
		userRepository.deleteById(id);
		return "delete ok";
	}
	
	@PutMapping("/test/user/{id}")
	public String updateOne(@PathVariable int id, User user) {  // user - password, email 담겨있음
		// 1. id로 select 하기
		User userEntity = userRepository.findById(id).get(); // entity -> DB에서 가져온 것  / 얘를 수정
		
		// 2. 받은 body 데이터로 수정하기
		userEntity.setPassword(user.getPassword());
		userEntity.setEmail(user.getEmail());
		
		// 3. save하면 됨.(이때 꼭 id값이 db에 존재해야 update가 됨)
		userRepository.save(userEntity); // save - id 값이 들어오면 수정, id값이 안들어오면 저장
		
		return "update Ok";
	}
}
