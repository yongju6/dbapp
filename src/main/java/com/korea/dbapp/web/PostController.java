package com.korea.dbapp.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.korea.dbapp.domain.post.Post;
import com.korea.dbapp.domain.post.PostRepository;
import com.korea.dbapp.domain.user.User;
import com.korea.dbapp.util.Script;

@Controller
public class PostController {

	private final PostRepository postRepository;
	private final HttpSession session;

	public PostController(PostRepository postRepository, HttpSession session) {
		this.postRepository = postRepository;
		this.session = session;
	}

	@GetMapping({ "/", "/post" }) // 주소가 2개!
	public String list(Model model) { // model = request
		// 핵심 로직
		// post 여러건을 db에서 들고옴
		model.addAttribute("postsEntity", postRepository.findAll());

		return "post/list"; // ViewResolver의 도움 + RequestDispatcher ( request 유지 기법)
	}

	@GetMapping("/post/{id}")
	public String detail(@PathVariable int id, Model model) {
		Post postEntity = postRepository.findById(id).get();
		model.addAttribute("postEntity", postEntity);

		return "post/detail";
	}

	@PostMapping("/post/{id}")
	public @ResponseBody String deleteById(@PathVariable int id) {
		// 1. 권한 체크(post id를 통해 user id를 찾아서 session의 id 비교) - 생략

		// session의 user id 찾기(session 접근)

		// post의 user id 찾기 ({id})

		// 2. {id} 값으로 삭제
		Post postEntity = postRepository.findById(id).get(); // 어떻게 처리?
		int postUserId = postEntity.getUser().getId();
		User userEntity = (User) session.getAttribute("principal");
//		int sessionUserId =((User)session.getAttribute("principal")).getId();
		int userId = userEntity.getId();
		if (postUserId == userId) {
			postRepository.deleteById(id);
			return Script.href("/");
		} else {
			return Script.back("삭제 실패");
		} // end if-else
	}

	@GetMapping("/post/saveForm")
	public String saveForm() {
		// 1. 인증 체크

		return "post/saveForm"; // 파일 호출
	}

	@PostMapping("/post")
	public String save(Post post) {

		// principal에 password는 없는데 필요한건 id 값이기때문에 id값 이외에는 다 null이어도 무방
		User principal = (User) session.getAttribute("principal");

		if (principal == null) {
			return "redirect:/auth/loginForm"; // 주소 호출
		}
		post.setUser(principal);
		postRepository.save(post);
		return "redirect:/";
	}

	@GetMapping("/post/{id}/updateForm")
	public String updateForm(@PathVariable int id, Model model) {
		User principal = (User) session.getAttribute("principal");
		int loginId = principal.getId(); // 로그인 한 사람의 아이디

		Post postEntity = postRepository.findById(id).get();
		int postOwnerId = postEntity.getUser().getId();

		if (loginId == postOwnerId) {
			model.addAttribute("postEntity", postEntity);
			return "post/updateForm";
		} else {
			return "redirect:/auth/loginFrom";
		} // end if
	}

	@PostMapping("/post/{id}/update")
	public String update(@PathVariable int id, String title, String content) {
		User user = (User) session.getAttribute("principal");
		int loginId = user.getId();

		Post postEntity = postRepository.findById(id).get();
		int postOwnerId = postEntity.getUser().getId();
		
		if (loginId == postOwnerId) {
			postEntity.setTitle(title);
			postEntity.setContent(content);
			postRepository.save(postEntity);
			return "redirect:/post/" + id;
		}else {
			return "redirect:/auth/login";
		}

	}
}
