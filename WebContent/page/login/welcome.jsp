<%@page import="web.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>    
<% response.setContentType("text/html; charset=UTF-8"); %>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>웰컴 페이지</title>

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
				<h2>메인 페이지</h2> <!-- 로그인이 된 채로 메인페이지로 넘어가개 -->
			</header>
				<p><% MemberDto dto = (MemberDto)session.getAttribute("loginMember");
				String name = dto.getM_id();%>
				<%=name %>님 회원가입 되셨습니다.</p>
			<p><%=name %>님 환영합니다.</p>
			<form action="/semi_temp/userModify" method="post"> 
				<div class="row gtr-uniform">
					<div class="col-12">
						<ul class="actions special">
							<li><input type="button" value="홈으로 " onclick="location.href='<%=application.getContextPath()%>/'" class="primary"></li>
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