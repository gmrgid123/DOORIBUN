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
	<col width="50"><c:if test="${sessionScope.loginMember.m_type eq '관리자' ||  sessionScope.loginMember.m_type eq '일반회원'||sessionScope.loginMember.m_type eq '택시기사'}"><col width="100"></c:if>
	<col width="200"><col width="300"><col width="300"><col width="100"><col width="100"><col width="100"><col width="100"><col width="100">							
	<tr>				
		<th>번호</th>	
		<c:choose>
			<c:when test="${sessionScope.loginMember.m_type eq '관리자' ||  sessionScope.loginMember.m_type eq '일반회원'||sessionScope.loginMember.m_type eq '택시기사'}">
				<th>신청자</th>
			</c:when>
		</c:choose>		
		<th>예약날짜 및 시간 </th>			
		<th>출발지</th>			
		<th>도착지</th>
		<th>등록날짜</th>
		<th>예약현황</th>
		<th>택시기사</th>
		<th>수정</th>
		<th>취소</th>			
	</tr>				
	<tr>				
	<c:choose>				
		<c:when test="${empty list}">			
			<tr>		
				<c:choose>
					<c:when test="${sessionScope.loginMember.m_type eq '관리자' ||  sessionScope.loginMember.m_type eq '일반회원'||sessionScope.loginMember.m_type eq '택시기사'}">
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
						<c:if test="${sessionScope.loginMember.m_type eq '관리자' ||  sessionScope.loginMember.m_type eq '일반회원'||sessionScope.loginMember.m_type eq '택시기사'}">
							<td>${member_dao.getMember(dto.member_no).m_name }(${member_dao.getMember(dto.member_no).m_id })</td>
						</c:if>
					<td>${dto.getBook_dateString() }</td>
					<td>${dto.start_addr}</td>
					<td>${dto.end_addr}</td>
					<td>${dto.taxi_regdate}</td>
					<td>${dto.taxi_state}</td>
					<!--<c:set var="state" value="${dto.taxi_state }"></c:set>-->
					<c:choose>
						<c:when test="${state eq '매칭중' || state eq '예약취소'}">
							<td><a>미정</a></td>
						</c:when>
						<c:when test="${state eq '매칭완료' || state eq '일정종료'}">
							<td><a onclick="driverInfo(${dto.driver_no })">${member_dao.getMember(dto.driver_no).m_name }</a></td>
						</c:when>
					</c:choose>
					<td><input type="button" value="수정" onclick="location.href='taxi.do?command=updateform&seq=${dto.taxi_id}'"></td>
					
					<td><input type="button" value="취소" onclick="delete2(${dto.taxi_id});"></td>
			
				</tr>	
			</c:forEach>		
		</c:otherwise>			
	</c:choose>	
	</tr>			
				
</table>					
			<input type="button" value="신청하기" onclick="location.href='taxi.do?command=list_insert'">		
</div>
<script type="text/javascript">
function delete2(id){
	
	var message = confirm("정말로 삭제하시겠습니까?");
	console.log("함수 진입 ");
    if(message==true){
        document.write("삭제 되였습니다.");
	    location.href="taxi.do?command=delete&seq=" + id;
    }else{
        document.write("취소 되었습니다.");
        location.href="taxi.do?command=list";
    }
    
}

function driverInfo(data){
	
	if(data==0||data==null){
		alert("택시기사님이 매칭될 때까지 기다려주세요");
	}else{
		var url= 'taxi.do?command=driverinfo&seq='+data;
	window.open(url,"Taxi Driver info", "width=400px,height=300px");
	}
	
}


</script>
  <style type="text/css">
    a {
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  
  margin: 0;
  padding: 0.5rem 1rem;
  font-family: "Noto Sans KR", sans-serif;
  font-size: 1rem;
  font-weight: 400;
  text-align: center;
  text-decoration: none;

  display: inline-block;
  width: auto;

  border: none;
  border-radius: 4px;
  
}
   body{ text-align: center; }

 </style>


	<jsp:include page="../../resources/form/footer.jsp" />
</body>
</html>