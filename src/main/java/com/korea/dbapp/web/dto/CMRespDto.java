package com.korea.dbapp.web.dto;
	// 데이터만 들고 있는 클래스 : bean class 
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // 빈생성자
@AllArgsConstructor // 전체 생성자 생성해줌
@Data
public class CMRespDto <T> {
	// 클래스 자료형 
	private int code;
	private String msg;
	private T data;
	
	// requestDTO는 여러개 필요하지만 responseDTO는 제네릭스를 활용해서 하나만 만들면 됨
	// 모델은 데이터베이스 케이블이랑만 통신
	// 모델은 데이터가 repository에서 만들어짐
	// 통신할 때 DTO가 만들어짐
	
}
