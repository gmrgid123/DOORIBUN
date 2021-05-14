<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 코스 목록</title>
<style type="text/css">
	#wrapper {
		margin-bottom: 500px;
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
	
	#reply {
		resize: none;
		width: 600px;
		height: 180px;
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
							<h3>${inq_dto.inquiry_title }</h3>
							<hr>
							<div><span>작성자 : ${ member_dao.getMemberId(inq_dto.member_no) }</span><span style="float: right;">작성일자 :${ inq_dto.getInquiry_regdateDate() }  ${inq_dto.getInquiry_regdateTime() } </span></div>
							
							<textarea  id="content" readonly="readonly">${inq_dto.inquiry_content }</textarea>
						</div>
						<br><br><br>
						<hr>
						
						
						<div>
							<c:choose>
								<c:when test="${sessionScope.loginMember.m_type eq '관리자' &&  inq_dto.inquiry_state eq '미완료' }">
									<h3>문의 답변하기</h3>
									<form action="<%=application.getContextPath() %>/inquiry-reply-regist" method="post">
										<input type="hidden" name="inquiry_id" value="${inq_dto.inquiry_id }">
										<input type="text" placeholder="제목을 입력하세요." name="reply_title" id="title" required="required"><br>
										<textarea  name="reply_content" id="reply" required="required"></textarea>
										<br>
										<button type="submit">답변제출</button>
									</form>
								</c:when>
								<c:when test="${inq_dto.inquiry_state eq '완료' }">
									<h3>관리자 답변 - ${reply_dto.reply_title }</h3>
									
									<div><span style="float: right;">작성일자 :${ reply_dto.getReply_regdateDate() }  ${reply_dto.getReply_regdateTime() } </span></div>
							
									<textarea id="reply" readonly="readonly">${reply_dto.reply_content }</textarea>
								</c:when>
								
							</c:choose>
							
							
						</div>
					</div>
			</div>
		</form>
	</div>
	<jsp:include page="../../resources/form/footer.jsp" />
</body>
</html>