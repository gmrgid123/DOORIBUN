<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.boxtab1 {
		border: 1px solid black;
		text-align: center;
		width: 185px;
		height: 50px;
		cursor: pointer;
		left: 15px;
	}
	
	.a{
		position: relative;
		top: 12.5px;
		text-decoration: none;
		color: gray;
	}
</style>	
</head>
<body>
	<form action="" method="post">
		<div class="wrappertab1">
			<div class="boxtab1" id="myinfo">
				<a class="a"href="<%=application.getContextPath()%>/page/mypage/myinfocheck.jsp">개인정보 조회/변경</a>
			</div>
			<div class="boxtab1" id="mycourselist">
				<a class="a" href="<%=application.getContextPath() %>/mycourse?p=1">나의 코스 목록</a>
			</div>
			<div class="boxtab1" id="myboardlist">
				<a class="a" href="<%=application.getContextPath() %>/mycommunity?p=1">나의 커뮤니티 글</a>
			</div>
			<div class="boxtab1" id="question">
				<a class="a" href="<%=application.getContextPath()%>/myinquiry?p=1">1:1 문의</a>
			</div>
			<div class="boxtab1" id="secession">
				<a class="a" href="<%=application.getContextPath()%>/page/mypage/secession.jsp">회원탈퇴</a>
			</div>		
		</div>
	</form>
</body>
</html>