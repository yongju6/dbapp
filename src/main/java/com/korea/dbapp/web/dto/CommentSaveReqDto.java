package com.korea.dbapp.web.dto;

import lombok.Data;

// dto를 만드는 이유는 모델로 받을 수 없기 때문에
@Data
public class CommentSaveReqDto {
	private String text;
	private int postId; // post_Id로 하지 않은 이유는 post_id로 하면 jackson이 인식을 못함
}
 