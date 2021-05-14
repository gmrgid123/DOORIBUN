<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %> 
<% response.setContentType("text/html; charset=UTF-8"); %> 

<%@ page import = "java.util.List" %>  
<%@ page import = "web.dto.NoticeDto" %>
<%@ page import = "web.dao.NoticeDao" %>
<%@ page import = "java.io.Console" %>
<%@ page import = "common.JDBCTemplate" %>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix ="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>


<%

	int pageSize = 5;
	int currentPage = Integer.parseInt(request.getAttribute("currentPage").toString());
	int count = Integer.parseInt(request.getAttribute("count").toString());

	
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Notice</title>

<style type="text/css">
table.table {
  border-collapse: collapse;
  text-align: center;
  line-height: 1.5;

}
table.table thead th {
  padding: 10px;
  font-weight: bold;
  vertical-align: top;
  color: #369;
  border-bottom: 3px solid #036;
}
table.table tbody th {
  width: 150px;
  padding: 10px;
  font-weight: bold;
  vertical-align: top;
  border-bottom: 1px solid #ccc;
  background: #f3f6f7;
}
table.table td {
  width: 350px;
  padding: 10px;
  vertical-align: top;
  border-bottom: 1px solid #ccc;
}

#table{
	text-align:right;

}

</style>
<c:choose>
	<c:when test="${ empty sessionScope.loginMember }">
		<c:set var="login_state" value="0"></c:set>
	</c:when>
	<c:otherwise>
		<c:set var="login_state" value="1"></c:set>
	</c:otherwise>
</c:choose>
<script type="text/javascript">

function logincheck(){
	console.log(login_data);
	var login_data = ${login_state};
	if(login_data==1){
		location.href="<%=application.getContextPath()%>/page/board/notice/boardwrite.jsp";
	}else{
		alert("로그인 후 이용해주세요.");
	}
}
</script>

</head>
<body>
	<!-- header include -->
	<jsp:include page="../../../resources/form/header.jsp" />


<div  style="margin-left: 10%; margin-right: 10%; margin-top: 50px;">	
	<p style="text-align: center;">
	<img src="/semi_temp/resources/images/A.JPG">
	<p>
	
	<table class="table">
		<col width="100px"> <col width="100px"> <col width="550px"> <col width="150px"> <col width="70px">
	
	<thead>
	<tr>
		<th>번호</th>
		<th>작성자</th>
		<th>제목 </th>
		<th>날짜 </th>
		<th>조회 </th>
	</tr>
	</thead>
	
	<tbody>
	<tr>
		<c:choose>
		<c:when test="${empty list }">
			<tr>
				<td colspan="5">===작성된 글이 존재하지 않습니다.===</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach var="i" begin="0" end="${pageSize }">
				<tr>
					<td>${list.get(i).notice_id }</td>
					<td>${member_dao.getMemberId(list.get(i).member_no) }</td>
					<td><a href="NoticeController?command=detail&notice_id=${list.get(i).notice_id}">${list.get(i).notice_title }</a></td>
					<td>${list.get(i).notice_regdate }</td> 
					<td>${list.get(i).notice_hit }</td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	</tr>
	
<c:choose>
	<c:when test="${sessionScope.loginMember.m_type eq '관리자' }">	
	<tr>
		<td colspan="5" align="right">
		<input type="button" value="글쓰기" onclick="logincheck();">
		</td>
	</tr>
	</c:when>
</c:choose>
	</tbody>
	</table>
	
	
		<!--  검색 시작  -->
		<!-- <form action="../../../NoticeController" method="get" name="searchform">
			<input type="hidden" name="searchflag" value="true">
			<table class="table" id="table" cellpadding="0" cellspacing="0" width="700">
				<tbody>
				<tr>
					<td align="right">
						<select id="searchCondition" name="search">
							<option value="TITLE">제목</option>
							<option value="CONTENT">내용</option>
						</select>
						<input id="searchKeyword" name="word" type="text">
						<input type="submit" value="검색 ">
					</td>
				</tr>	
				</tbody>	
			</table>
		</form> -->
		<!-- 검색 종료  -->	
		
		
		<!-- 페이징 시작 -->
		<tr>
				<td colspan="6" align="center">
					<%	// 페이징  처리
						if(count > 0){
							// 총 페이지의 수
							int pageCount = count / pageSize + (count%pageSize == 0 ? 0 : 1);
							// 한 페이지에 보여줄 페이지 블럭(링크) 수
							int pageBlock = 5;
							// 한 페이지에 보여줄 시작 및 끝 번호(예 : 1, 2, 3 ~ 10 / 11, 12, 13 ~ 20)
							int startPage = ((currentPage-1)/pageBlock)*pageBlock+1;
							int endPage = startPage + pageBlock - 1;
							
							// 마지막 페이지가 총 페이지 수 보다 크면 endPage를 pageCount로 할당
							if(endPage > pageCount){
								endPage = pageCount;
							}
							
							if(startPage > pageBlock){ // 페이지 블록수보다 startPage가 클경우 이전 링크 생성
					%>
								<a href="NoticeController?command=list&pageNum=<%=startPage - 10%>">[이전]</a>	
					<%			
							}
							
							for(int i=startPage; i <= endPage; i++){ // 페이지 블록 번호
								if(i == currentPage){ // 현재 페이지에는 링크를 설정하지 않음
					%>
									[<%=i %>]
					<%									
								}else{ // 현재 페이지가 아닌 경우 링크 설정
					%>
									<a href="NoticeController?command=list&pageNum=<%=i%>">[<%=i %>]</a>
					<%	
								}
							} // for end
							
							if(endPage < pageCount){ // 현재 블록의 마지막 페이지보다 페이지 전체 블록수가 클경우 다음 링크 생성
					%>
								<a href="NoticeController?command=list&pageNum=<%=startPage + 10 %>">[다음]</a>
					<%			
							}
						}
					%>
				</td>
			</tr>
			<!-- 페이징 끝 -->
			(총 게시글 수: <%=count %>)
			<br><br><br>
</div>
		
	<!-- footer include -->
	<jsp:include page="../../../resources/form/footer.jsp" />     
</body>
</html>