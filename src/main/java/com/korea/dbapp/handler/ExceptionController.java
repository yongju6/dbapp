package com.korea.dbapp.handler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice	// 사용자의 요청을 받는게 아니라 exception이 발생하면 이 클래스로 들어옴
@RestController
public class ExceptionController {
	
	@ExceptionHandler(Exception.class) // Exception이라는 예외가 발생하면 무조건 이 메서드를 실행함
	public String test1(Exception e, Model model) {
		model.addAttribute("msg", e.getMessage());
		return "error";
	}
}
