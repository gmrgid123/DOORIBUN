<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("UTF-8"); %>    
<% response.setContentType("text/html; charset=UTF-8"); %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정 페이지</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="<%=application.getContextPath() %>/resources/css/main.css">

<script type="text/javascript" src="<%=application.getContextPath() %>/resources/js/members.js"></script>

<script>
 var m_phone = ${sessionScope.loginMember.m_phone}
 //내 번호랑 같을때는 중복체크 누르라고 할 필요 없게
 function modifyPhoneCheck(){
	 if ($("#m_phone").val() ==  m_phone){
		 
		 phoneChecking = true;
		 
		 alert("사용할 수 있는 연락처입니다.");
		 return;
		 
	 }else{
		 phoneCheck();
		 
	 }
 }


</script>


</head>
<body>
	<!-- header include -->
	<jsp:include page="../../resources/form/header.jsp" />
<section id="footer">
		<div class="container">
			<header class="major">
				<h2>정보 수정 페이지</h2>
			</header>
			<form action="/semi_temp/userModify" method="post" name="reg_frm">
			
				<div class="row gtr-uniform">
					<div class="col-12">
						ID:
						${sessionScope.loginMember.m_id }
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Name:
						${sessionScope.loginMember.m_name}</div>
					<div class="col-12">
						<input type="password" name="m_pw" placeholder="비밀번호" />
					</div>
					<div class="col-12">
						<input type="password" name="pw_check" placeholder="비밀번호 확인" />
					</div>
					<div class="col-12">
						<input type="text" name="m_email" value="${sessionScope.loginMember.m_email }" placeholder="이메일" />
					</div>
					<div class="col-12">
						<table>
						<tr>
						<td style= "width: 90%;"><input type="text" name="m_phone" id="m_phone" value="${sessionScope.loginMember.m_phone }" placeholder="연락처" /></td>
						<td style= "width:10%;"><button style="font-size: 15px;" type= "button" onclick="modifyPhoneCheck();">중복체크</button></td>
						</tr><!--name이 서브릿 콘트롤러에서 폼에해당하는거 네임으로 학인/ 자바스크립츠의 $("#~")는 무조건 id를 기준  -->
						</table>
					</div>
					<div class="col-12">
						<ul class="actions special">
							<li><input type="button" value="수정"
								onclick="updateInfoConfirm()" class="primary"></li>
							<li><input type="reset" value="취소"
								onclick="location.href='<%=application.getContextPath() %>/page/login/login.jsp'" class="primary"></li>
						</ul>
					</div>
				</div>
			</form>
		</div>
	</section>
 	<!-- footer include -->
	<jsp:include page="../../resources/form/footer.jsp" />	
</body>
</html>