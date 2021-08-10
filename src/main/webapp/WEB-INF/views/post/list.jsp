<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../layout/header.jsp"%>

<div class="container">

	<!-- var : 내가 지금 사용할 변수 -> page scope에 들어감(tomcat 메모리 중)
 		items : 모델에 있는 데이터 -->
	<!-- for 문 한번 돌 때마다 postsEntityrk post에 들어감 -->
	<c:forEach var="post" items="${postsEntity.content}">
		<div class="card">
			<div class="card-body">
				<h4 class="card-title">${post.title}</h4>
				<a href="/post/${post.id}" class="btn btn-primary">상세보기</a>
			</div>
		</div>
		<br>
		<!-- End of Card -->
	</c:forEach>
<!-- 데이터 분석해보면 첫번째 페이지 : first, 마지막 페이지 : last로 값이 있음--> 
	<ul class="pagination">
		<li class="page-item disabled"><a class="page-link" href="?page=${postsEntity.number-1}">Previous</a></li>

 		<li class="page-item"><a class="page-link" href="?page=${postsEntity.number+1}">Next</a></li>
	</ul>

</div>
<!-- End of Container -->

<%@include file="../layout/footer.jsp"%>S
