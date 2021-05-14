<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>    
<% response.setContentType("text/html; charset=UTF-8"); %>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에러 페이지</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="<%=application.getContextPath() %>/resources/css/main.css">

</head>
<body>
	<!-- header include -->
	<jsp:include page="../../resources/form/header.jsp" />
	
	<section id="footer">
		<div class="container">
			
	<% int errorcode = (int) request.getAttribute("fail");
	
	if(errorcode == 0) {%>
		<h3>비밀번호가 일치하지 않습니다.</h3>
		
	<%}else if(errorcode == -1){%>
		<h3>아이디가 존재하지 않습니다.</h3>
	
	
	<%}else if(errorcode == -3){%>
		<h3>회원가입이 제대로 되지 않았습니다.</h3>

	<%}else if(errorcode == -4){%>
		<h3>정보 수정이 제대로 되지 않았습니다.</h3>
		
	<%}else if(errorcode == 404){%>
		<h3>탈퇴한 계정입니다!</h3>
	
	<%}else {%>
		<h3>내부적 오류로 서버 담당자에게 연락바랍니다.</h3>
	<%}
	
	%>
				<div class="row gtr-uniform">
					<div class="col-12">
						<ul class="actions special">
							<li><input type="button" value="로그인페이지로" onclick="location.href='<%=application.getContextPath() %>/page/login/login.jsp'" class="primary"></li>
						</ul>
					</div>
				</div>
		</div>
	</section>
	
 	<!-- footer include -->
	<jsp:include page="../../resources/form/footer.jsp" />	
</body>
</html>