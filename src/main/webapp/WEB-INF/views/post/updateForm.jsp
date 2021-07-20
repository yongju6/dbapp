<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp"%>
<div class="container">			<!-- onsubmit : onsubmit 먼저 실행하고 리턴이 true면 action 실행 / 체크할 때 용이(텍스트 길이, 비밀번호 확인 등) submit 실행되면 페이지가 새로고침됨=> 새로고침 막아야함-->
	<form action="#" onsubmit="updatePost()"> 	
		<div class="form-group">
			<label for="title">Title: </label> 
			<input id="title" value="${postEntity.title}" type="text" class="form-control" placeholder="Enter title" name="title" required="required" />
		</div>
		<div class="form-group">
			<textarea id="content" rows="10" class="form-control" name="content">
			${postEntity.content}
			</textarea>
		</div>

		<button type="submit" class="btn btn-primary">수정 완료</button>
	</form>
</div>


<script>


	async function updatePost(){  
		console.log(event); 	// event 라고 적으면 이벤트 정보를 가져올 수 있음
		event.preventDefault();	// submit 동작을 막음 -> 새로고침을 안함
		
		let title = document.querySelector("#title").value;
		let content = document.querySelector("#content").value;		// title, content 받아옴
		
		console.log(title);
		console.log(content); 
		
		let updateDto={
			title: title,
			content: content
		}; // object 변수
		
		let response = await fetch("/post/${postEntity.id}", {
			method:"put",
			body:JSON.stringify(updateDto),		//인텔리제이 유로버전 대학생 가능
			headers: {
				"Content-Type":"application/json; charset-utf-8"
			}
		}); // el표현식을 쓰면 톰켓에서 먼저 해석을 해버림
		
		let parseResponse = await response.text(); 	// 통신 끝남  / json() , text()
		
		console.log(parseResponse);
		
		if(parseResponse == "ok"){
			location.href="/post/${postEntity.id}";
		}
	}

	$('#content').summernote({
		placeholder : 'Hello Bootstrap 4',
		tabsize : 2,
		height : 500
	});
</script>
<%@include file="../layout/footer.jsp"%>