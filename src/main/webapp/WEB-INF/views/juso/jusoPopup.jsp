<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8" />
<title>주소 API</title>
</head>

<body onload="init();">
	<form id="form" name="form" method="post">
		<input type="hidden" id="confmKey" name="confmKey" value="" /> 
		<input type="hidden" id="returnUrl" name="returnUrl" value="" /> 
		<input type="hidden" id="resultType" name="resultType" value="" />
	</form>

	<script>
		function init(){
		var url = location.href;
		var confmKey = "devU01TX0FVVEgyMDIxMDcwNTE3MjgyMzExMTM2MTE=";
		var resultType = "4"; 
		var inputYn= "${inputYn}";
		if(inputYn != "Y"){
			console.log("최초요청");
			document.form.confmKey.value = confmKey;
			document.form.returnUrl.value = url;
			document.form.resultType.value = resultType;
			document.form.action="https://www.juso.go.kr/addrlink/addrLinkUrl.do"; //인터넷망
			document.form.submit();
		}else{
			console.log("추후 응답");
			opener.jusoCallBack("${roadFullAddr}");
			window.close();
			}
		}
</script>

</body>
</html>