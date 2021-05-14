<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<style type="text/css">
	.header {
		position:absolute;
		top: 0px;
		left: 0px;
	}
	.wrappernew {
		height: 500px;
	}
	.innerwrapper{
		position: relative;
		top: 100px;
		display: grid;
		grid-template-columns: 200px 200px 200px;
		grid-template-rows: 150px 150px;
		place-content: space-between center;
	}
	.box2 {	
		text-align: center;
		border: 1px solid black;
	}
	.a {
		position: relative;
		top: 65px;
		text-decoration: none;
		color: gray;
	}
</style>
</head>
<body>
	<div id="header">
		<jsp:include page="../../resources/form/header.jsp" />
	</div>
	<form action="" method="post">
		<div class="wrappernew">
			<div class="innerwrapper">
				<div class="box2" id="myinfo">
					<a href="myinfocheck.jsp" class="a">개인정보 조회/변경</a>
				</div>
				<div class="box2" id="mycourselist">
					<a href="<%=application.getContextPath() %>/mycourse?p=1" class="a">나의 코스 목록</a>
				</div>
				<div class="box2" id="taxi">
					<a href="<%=application.getContextPath() %>/taxi.do?command=list" class="a">택시 예약 현황</a>
				</div>
				<div class="box2" id="myboardlist">
					<a href="<%=application.getContextPath() %>/mycommunity?p=1" class="a">나의 커뮤니티 글</a>
				</div>
				<div class="box2" id="question">
					<a href="<%=application.getContextPath() %>/myinquiry?p=1" class="a">1:1 문의</a>
				</div>
				<div class="box2" id="secession">
					<a href="secession.jsp" class="a">회원탈퇴</a>
				</div>		
			</div>
		</div>
	</form>
	
	<jsp:include page="../../resources/form/footer.jsp" />
</body>
</html>