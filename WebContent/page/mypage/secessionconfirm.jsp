<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴 확인</title>
<style type="text/css">
	.textcontainer1 {
		position: relative;
		top: 80px;
	}
	.textcontainer2 {
		position: relative;
		top: 80px;
	}
	.text {
		text-align: center;
	}
</style>
<script type="text/javascript">
	function submitClick(){
		var text = document.getElementById("confirm").value;
		if(text === "탈퇴합니다.") {
			opener.location.href='<%=application.getContextPath()%>/myinfo-secession';
			this.close();
		} else {
			window.alert("정확히 입력해주세요.");
		}
	}
</script>
</head>
<body>
	<div id="wrapper">
		<div id="container">
			<div class="textcontainer1">
				<div class="text">탈퇴를 원하시면 아래에 '탈퇴합니다.'라고 정확히 기입해 주세요.</div>
			</div>
			<div class="textcontainer2">
				<div class="text">
					<input type="text" id="confirm">
					<input type="button" value="확인" id="submitbtn" onclick="submitClick();">
				</div>
			</div>
		</div>
	</div>
</body>
</html>