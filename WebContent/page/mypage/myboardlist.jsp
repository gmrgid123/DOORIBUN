<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %> 
<% response.setContentType("text/html; charset=UTF-8"); %> 

<%@ page import = "java.util.List" %>  
<%@ page import = "web.dto.CommunityDto" %>
<%@ page import = "web.dao.CommunityDao" %>
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
<title>내 커뮤니티 글 목록</title>
<style type="text/css">
	
	#container {
		display: grid;
		grid-template-columns: 1fr 5fr;
	}
	#mypagetab {
		position: relative;
		float:left;
		top: 100px;
		left:20px;
	}
	
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
</head>
<body>
	<jsp:include page="../../resources/form/header.jsp" />
	<div id="wrapper">
		<div id="container" style=" margin-bottom: 100px;">
			<div id="mypagetab" style="margin-bottom: 200px;">
				<jsp:include page="mypagetab.jsp" />
			</div>
			
	<div style="margin-top: 50px; margin-right: 20%; margin-left: 5%;">
		<h1>나의 커뮤니티 글 목록</h1>
		<table class="table">
		<col width="100px"><col width="550px"><col width="150px">	
				<thead>
				<tr>
					<th>번호</th>
					<th>제목 </th>
					<th>날짜 </th>
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
								<td>${list.get(i).community_id }</td>
								<td><a href="CommunityController?command=detail&community_id=${list.get(i).community_id}">${list.get(i).community_title }</a></td>
								<td>${list.get(i).community_regdate }</td> 
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				</tr>
				
				
				<tr>
					<td colspan="5" align="right">
					<input type="button" value="글쓰기" onclick="logincheck();">
					</td>
				</tr>
			
				</tbody>
				</table>

		
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
								<a href="<%=application.getContextPath() %>/mycommunity?p=<%=startPage - 10%>">[이전]</a>	
					<%			
							}
							
							for(int i=startPage; i <= endPage; i++){ // 페이지 블록 번호
								if(i == currentPage){ // 현재 페이지에는 링크를 설정하지 않음
					%>
									[<%=i %>]
					<%									
								}else{ // 현재 페이지가 아닌 경우 링크 설정
					%>
									<a href="<%=application.getContextPath() %>/mycommunity?p=<%=i%>">[<%=i %>]</a>
					<%	
								}
							} // for end
							
							if(endPage < pageCount){ // 현재 블록의 마지막 페이지보다 페이지 전체 블록수가 클경우 다음 링크 생성
					%>
								<a href="<%=application.getContextPath() %>/mycommunity?p=<%=startPage + 10 %>">[다음]</a>
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
	
	
	
		</div>
	</div>

	<jsp:include page="../../resources/form/footer.jsp" />
</body>
</html>