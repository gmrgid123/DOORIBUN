<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html;charset=UTF-8"); %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="web.dto.TaxiDto" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="header">
		<jsp:include page="../../resources/form/header.jsp" />
</div>
<div  style="margin-left: 10%; margin-right: 10%; margin-top: 50px;">



<table border="1">					
	<col width="50"><c:if test="${sessionScope.loginMember.m_type eq '관리자' ||  sessionScope.loginMember.m_type eq '택시기사'||sessionScope.loginMember.m_type eq '일반회원'}"><col width="100"></c:if>
	<col width="200"><col width="300"><col width="300"><col width="100"><col width="100"><col width="100"><col width="100"><col width="100">							
	<tr>				
		<th>번호</th>	
		<c:choose>
			<c:when test="${sessionScope.loginMember.m_type eq '관리자' ||  sessionScope.loginMember.m_type eq '택시기사'||sessionScope.loginMember.m_type eq '일반회원'}">
				<th>신청자</th>
			</c:when>
		</c:choose>	
		<th>예약날짜 및 시간 </th>			
		<th>출발지</th>			
		<th>도착지</th>
		<th>등록날짜</th>
		<th>예약현황</th>
		<th>신청</th>
		<th>취소</th>			
	</tr>				
	<tr>				
	<c:choose>				
		<c:when test="${empty list}">			
			<tr>
				<c:choose>
					<c:when test="${sessionScope.loginMember.m_type eq '관리자' ||  sessionScope.loginMember.m_type eq '택시기사'||sessionScope.loginMember.m_type eq '일반회원'}">
						<td colspan="9">---신청내역이 존재하지 않습니다---</td>
					</c:when>
					<c:otherwise>
						<td colspan="8">---신청내역이 존재하지 않습니다---</td>
					</c:otherwise>
				</c:choose>		
			</tr>		
		</c:when>			
		<c:otherwise>			
			<c:forEach var="dto" items="${list}">		
				<tr>	
					<td>${dto.taxi_id}</td>
						<c:if test="${sessionScope.loginMember.m_type eq '관리자' ||sessionScope.loginMember.m_type eq '택시기사'||sessionScope.loginMember.m_type eq '일반회원'}">
							<td>${member_dao.getMemberId(dto.member_no) }</td>
						</c:if>
					<td >${dto.getBook_dateString()} </td>
					<td>${dto.start_addr }</td>
					<td>${dto.end_addr }</td>
					<td>${dto.taxi_regdate}</td>
					<td>${dto.taxi_state}</td>
					<td><input type="button" value="신청" onclick="location.href='/semi_temp/taxi.do?command=driverConfirm&seq=${dto.taxi_id}'" ></td>
					<td><input type="button" value="취소" onclick="location.href='/semi_temp/taxi.do?command=driverCancel&seq=${dto.taxi_id}'"></td>
				
				</tr>	
			</c:forEach>		
		</c:otherwise>			
	</c:choose>	
	</tr>						
</table>		
</div>
<script type="text/javascript">


</script>


<jsp:include page="../../resources/form/footer.jsp" />

</body>
</html>