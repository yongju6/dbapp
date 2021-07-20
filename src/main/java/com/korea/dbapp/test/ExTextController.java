package com.korea.dbapp.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ExTextController {
	
	@GetMapping("/ex/test1/{no}")
	public String exTest1(@PathVariable int no) throws Exception {
		
		if(no == 1) {
			throw new Exception("가짜 에러");
		}else {
			return "test/Sameple";
		} // end if-else
	} // end exTest1
}
