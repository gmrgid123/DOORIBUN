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
	#title {
		width:600px;
	}
	#content {
		resize: none;
		width: 600px;
		height: 280px;
	}
	#labelarea {
		text-align: center;
		height: 40px;
	}
	#inputarea {
		height: 320px;
	}
	#buttonarea {
		position: relative;
		left: 250px;
	}
</style>
</head>
<body>
	<jsp:include page="../../resources/form/header.jsp" />
	<div id="wrapper">
		<form action="../../inquiryregist" method="post">
			<div id="container">
					<div id="mypagetab">
						<jsp:include page="mypagetab.jsp" />
					</div>
					<div id="main">
						<div id="labelarea">
							<p style="font-size:20px;">1:1 문의</p>
						</div>
						<div id="inputarea">
							<input type="text" placeholder="제목을 입력하세요." name="title" id="title" required="required"><br>
							<textarea placeholder="내용을 입력하세요." name="content" id="content" required="required"></textarea>
						</div>
						<div id="submitarea">
							<div id="buttonarea">
								<input type="submit" value="확인">
								<input type="button" value="취소" onclick="location.href='<%=application.getContextPath()%>/myinquiry?p=1'">
							</div>
						</div>
					</div>
			</div>
		</form>
	</div>
	<jsp:include page="../../resources/form/footer.jsp" />
</body>
</html>