package com.korea.dbapp.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.korea.dbapp.domain.comment.Comment;
import com.korea.dbapp.domain.comment.CommentRepository;
import com.korea.dbapp.domain.post.Post;
import com.korea.dbapp.domain.post.PostRepository;
import com.korea.dbapp.domain.user.User;
import com.korea.dbapp.web.dto.CMRespDto;
import com.korea.dbapp.web.dto.CommentSaveReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class CommentController {

	private final CommentRepository commentRepository;
	private final HttpSession session;
	private final PostRepository postRepository;

	// 3. findByAllPostId - Get - 여기서 하는게 아니라 상세보기 페이지를 갈 때 사용해야함 -->
	// PostController에 만들어야함

	// 2. delete - Delete - Data Return
	@DeleteMapping("/comment/{id}")
	public @ResponseBody String deleteComment(@PathVariable int id) { // return 타입이 String 이므로 mimetype은 text-plain
		User userEntity = (User) session.getAttribute("principal");
		int userId = userEntity.getId();

		Comment commentEntity = commentRepository.findById(id).get();
		int commentUserId = commentEntity.getUser().getId();

		if (userId == commentUserId) {
			commentRepository.deleteById(id);
			return "ok";
		} else {
			return "fail";
		}
	}

	// 1. save - Post - Data Return
	@PostMapping("/comment")
	public @ResponseBody CMRespDto<Comment> save(@RequestBody CommentSaveReqDto dto) {
		Comment comment = new Comment();
		comment.setText(dto.getText());

		Post postEntity = postRepository.findById(dto.getPostId()).get();
		postEntity.setId(dto.getPostId());
		comment.setPost(postEntity);

		User principal = (User) session.getAttribute("principal");
		comment.setUser(principal);

		int userId = principal.getId();
		int commentUserId = comment.getUser().getId();
		Comment commentEntity = null;

		if (userId == commentUserId) {
			commentEntity = commentRepository.save(comment);
			return new CMRespDto<>(1, "댓글쓰기 성공", commentEntity); // DMRespDto에 생성자 없으면 오류남
		} else {
			return new CMRespDto<>(-1, "댓글 쓰기 실패", commentEntity);
		}

	}
}
