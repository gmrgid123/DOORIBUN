<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>    
<% response.setContentType("text/html; charset=UTF-8"); %>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그아웃</title>

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
				<h2>로그아웃</h2> 
			</header>
			
			<c:choose>
				<c:when test="${empty sessionScope.loginMember }">
					<p>로그아웃에 성공하셨습니다.</p>
				</c:when>
				<c:otherwise>
					<p>로그아웃에 실패하셨습니다.</p>
				</c:otherwise>
			</c:choose>
			
				<div class="row gtr-uniform">
					<div class="col-12">
						<ul class="actions special">
							<li><input type="button" value="로그인 " onclick="location.href='<%=application.getContextPath()%>/page/login/login.jsp'" class="primary"></li>
						</ul>
					</div>
				</div>
		</div>
	</section>
 	<!-- footer include -->
	<jsp:include page="../../resources/form/footer.jsp" />	
	
</body>
</html>