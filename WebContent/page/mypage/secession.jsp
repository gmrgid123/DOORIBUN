<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 코스 목록</title>
<style type="text/css">
	#wrapper {
		height: 500px;
	}
	#container {
		display: grid;
		grid-template-columns: 1fr 5fr;
	}
	#mypagetab {
		position: relative;
		top: 100px;
	}
	#main {
		margin: auto;
		position: relative;
		top: 50px;
		height: 400px;
	}
	#textarea {
		position: relative;
		top: 150px;
	}
	#buttonarea{
		position: relative;
		top: 150px;
	}
</style>
<script type="text/javascript">
	function confirm(){
	    window.open("secessionconfirm.jsp", "_blank", "width=560, height=260, resizable=no, scrollbars=no");
	}
</script>
</head>
<body>
	<jsp:include page="../../resources/form/header.jsp" />
	<div id="wrapper">
		<div id="container">
				<div id="mypagetab">
					<jsp:include page="mypagetab.jsp" />
				</div>
				<div id="main">
					<div id="textarea">
						<p style="font-size: 17px;">정말로 탈퇴하시겠습니까?</p>
					</div>
					<div id="buttonarea">
						<input type="button" value="확인" onclick="confirm();">
						<input type="button" value="취소" onclick="location.href='mypagemain.jsp'">
					</div>
				</div>
		</div>
	</div>
	<jsp:include page="../../resources/form/footer.jsp" />
</body>
</html>