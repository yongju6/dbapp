<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="javascript">
// opener관련 오류가 발생하는 경우 아래 주석을 해지하고, 사용자의 도메인정보를 입력합니다. ("팝업API 호출 소스"도 동일하게 적용시켜야 합니다.)
//document.domain = "abc.go.kr";

function goPopup(){
	var pop = window.open("/juso/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
}


function jusoCallBack(roadFullAddr){
		document.form.address.value = roadFullAddr;
}

</script>
<title>주소 입력 샘플</title>
</head>
<body>
<form name="form" id="form" method="post">

	<input type="button" onClick="goPopup();" value="주소찾기"/>
	<div id="list"></div>
	<div id="callBackDiv">
		<table>
			<tr><td>도로명주소 전체(포멧)</td>
			<td><input type="text"  style="width:500px;" name="address" /></td></tr>
		</table>
	</div>

</form>
</body>
</html>