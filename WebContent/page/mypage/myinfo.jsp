<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>    
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인정보 조회/변경</title>
<style type="text/css">
	#infowrapper1 {
		height: 500px;
	}
	#infocontainer1 {
		display: grid;
		margin: 15px;
		grid-template-columns: 1fr 5fr;
	}
	#mypagetab {	
		position: relative;
		top: 80px;
		height: 200px;
	}
	#infocontainer2 {
		display: grid;
		padding: 50px;
		padding-left: 300px;
		grid-template-columns: 150px 300px 50px;
		height: 500px;
		position: relative;
		top: 40px;
	}
	.infobox1 {
		text-align: center;
		height: 40px;
		border: 1px solid white;
	}
	.infobox2 {
		height: 40px;
		border: 1px solid white;
	}
	p {
		margin: 3px;
		top: 10px;
	}
	#submit {
		grid-column: 1/3;
	}
	#submitform {
		position: relative;
		left: 240px;
	}
</style>

</head>
<body>
	<jsp:include page="../../resources/form/header.jsp" />
		<div id="infowrapper1">
			<div id="infocontainer1">
				<div id="mypagetab">
					<jsp:include page="mypagetab.jsp" />
				</div>
				<div>
						<div id="infocontainer2">
							<div id="infocontainer3">
								<div class="infobox1">
									<p>회원번호</p>
								</div>					
								<div class="infobox1">
									<p>계정유형</p>
								</div>
								<div class="infobox1">
									<p>이름</p>
								</div>
								<div class="infobox1">
									<p>아이디</p>
								</div>
								
								<div class="infobox1">
									<p>이메일</p>
								</div>
								<div class="infobox1">
									<p>전화번호</p>
								</div>				
							</div>			
							<div id="infocontainer4">
								<div class="infobox1">
									<p>${sessionScope.loginMember.m_no}</p>
								</div>
								<div class="infobox1">
									<p>${sessionScope.loginMember.m_type}</p>
								</div>
								<div class="infobox1">
									<p>${sessionScope.loginMember.m_name}</p>
								</div>
								<div class="infobox1">
									<p>${sessionScope.loginMember.m_id}</p>
								</div>
								
								<div class="infobox1">
									<p id="email">${sessionScope.loginMember.m_email}</p>
								</div>
								<div class="infobox1">
									<p id="phone">${sessionScope.loginMember.m_phone}</p>
								</div>			
							</div>
							<div id="infocontainer5">
								<div class="infobox2">
								</div>
								<div class="infobox2">
								</div>
								<div class="infobox2">
								</div>
								<div class="infobox2">
								</div>
								<div class="infobox2">
								</div>
								<div class="infobox2">
								</div>
								<div class="infobox2">
								</div>	
							</div>	
							<div id="submit">
								<div id="submitform">
									<button onclick="location.href='<%=application.getContextPath() %>/myinfochange';">정보수정</button>
								</div>
							</div>
						</div>
					
				</div>
			</div>
		</div>
		<jsp:include page="../../resources/form/footer.jsp" />
</body>
</html>