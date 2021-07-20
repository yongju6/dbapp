package com.korea.dbapp.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.korea.dbapp.domain.user.User;
import com.korea.dbapp.domain.user.UserRepository;
import com.korea.dbapp.util.Script;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {

	private final UserRepository userRepository;
	private final HttpSession session;
	
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "auth/joinForm";
	}
	
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "auth/loginForm";
	}
	
	@PostMapping("/auth/join") 
	public String join(User user) {
		userRepository.save(user);
		return "redirect:/auth/loginForm";
	}
	
	// RestController
	@PostMapping("/auth/login") 
	public @ResponseBody String login(User user) {
		User userEntity = userRepository.mLogin(user.getUsername(), user.getPassword());
		if(userEntity == null) {
			return Script.back("로그인 실패");		
		}else {
			session.setAttribute("principal", userEntity); // principal - 접근 주체, 인증 주체라는 뜻 
			// getAttribute 할때 user로 다운캐스팅 해줘야함 (오브젝트를 return하기 때문에)
			return Script.href("/");
		} // end if-else	
	}
	
	@GetMapping("/user/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	} 
	
	
	@GetMapping("/user/updateForm")
	public String updateForm() {
		// 1. 인증, 권한을 검사해야 함
		// 들고온 id가 session의 id와 같은지 확인 해야함
		// 2. 세션 값 사용
		return "user/updateForm";
	}
	
	@PostMapping("/user/{id}") // 원래는 PUT으로 해야함! 나중에 자바스크립트로 PUT 요청하기
	public String update(@PathVariable int id, String password, String address) { //pw, address만 수정 가능하므로 두 개 값만 들고옴 (원래는 두개 값을 받는 DTO를 만들어야함)
		// 업데이트 할 때 where 절에 걸리니까 {id} 넣어줌
		
		// 공통 관심사 -- 많은 페이지에서 계속 필요함! / 모든 컨트롤러에서 필요함 
		// --> AOP(Aspect Oriented Programming; 관점 지향 프로그래밍)로 공통 관심사를 분리시켜서 코드를 깔끔하게 만들고 자동으로 실행되도록 할 수 있음(OOP도 같이 쓰임)
		// 여러군데에서 쓰이는 것들 
		User principal = (User)session.getAttribute("principal");
		
		if(principal != null && id==principal.getId()) {
			User userEntity = userRepository.findById(id).get();
			userEntity.setPassword(password);
			userEntity.setAddress(address);
			userRepository.save(userEntity);
			session.setAttribute("principal", userEntity); // hashmap 데이터라서 키 값이 같으면 덮어씌워짐
			return "redirect:/";
		}
		return "redirect:/auth/loginForm";
	}
	
	@GetMapping("/juso")
	public String jusoRequest() {
		return "juso/jusoPopup";
	}
	
	@PostMapping("/juso")
	public String jusoResposne(String roadFullAddr, String inputYn, Model model) {
		// model --> request dispatcher
		model.addAttribute("roadFullAddr", roadFullAddr);
		model.addAttribute("inputYn", inputYn);
		return "juso/jusoPopup";
	}
}
