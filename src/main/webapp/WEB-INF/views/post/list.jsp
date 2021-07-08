<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp"%>

<div class="container">

	<!-- var : 내가 지금 사용할 변수 -> page scope에 들어감(tomcat 메모리 중)
 		items : 모델에 있는 데이터 -->
	<!-- for 문 한번 돌 때마다 postsEntityrk post에 들어감 -->
	<c:forEach var="post" items="${postsEntity}">
		<div class="card">
			<div class="card-body">
				<h4 class="card-title">${post.title}</h4>
				<a href="/post/${post.id}" class="btn btn-primary">상세보기</a>
			</div>
		</div>
		<br>
		<!-- End of Card -->
	</c:forEach>

</div>
<!-- End of Container -->

<%@include file="../layout/footer.jsp"%>S