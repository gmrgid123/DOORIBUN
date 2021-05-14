<%@page import="web.dto.MemberDto"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>    
<% response.setContentType("text/html; charset=UTF-8"); %>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정 완료 페이지</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="<%=application.getContextPath() %>/resources/css/main.css">

</head>
<body>
	<!-- header include -->
	<jsp:include page="../../resources/form/header.jsp" />
	<section id="footer">
		<div class="container">
			<header class="major">
				<h2>수정 완료 페이지</h2>
			</header>
			
			<p><%MemberDto dto = (MemberDto)session.getAttribute("loginMember");
			String name = dto.getM_id();%>
			<%=name %>님의 회원정보 수정이 완료되었습니다.
			
			<p><%=name %>님 환영합니다.</p>
			
			<form action="/semi_temp/userLogin" method="post"> <!-- action 부분 -->
				<div class="row gtr-uniform">
					<div class="col-12">
						<ul class="actions special">
							<li><input type="button" value="홈으로" onclick="location.href='<%=application.getContextPath()%>'" class="primary"></li>
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