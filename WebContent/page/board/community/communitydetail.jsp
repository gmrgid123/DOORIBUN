<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>  

<%@ page import="web.dao.CommunityDao" %>
<%@ page import="web.dto.CommunityDto" %>
   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Community</title>



<style type="text/css">

table.table {
  border-collapse: collapse;
  text-align: left;
  line-height: 1.5;
  border-top: 1px solid #ccc;
  border-left: 3px solid #369;
  margin : 20px 10px;
}
table.table th {
  width: 147px;
  padding: 10px;
  font-weight: bold;
  vertical-align: top;
  color: #153d73;
  border-right: 1px solid #ccc;
  border-bottom: 1px solid #ccc;

}
table.table td {
  width: 349px;
  padding: 10px;
  vertical-align: top;
  border-right: 1px solid #ccc;
  border-bottom: 1px solid #ccc;
}

</style>



</head>
<body>
	<!-- header include -->
	<jsp:include page="../../../resources/form/header.jsp" />

<div  style="margin-left: 20%; margin-right: 20%; margin-top: 50px;">	
<h1>Community</h1>

<table class="table">
	
	<tr>
		<th>NO</th>
		<td>${dto.community_id }</td>
	</tr>
	<tr>
		<th>WRITER</th>
		<td>${member_dao.getMemberId(dto.member_no) }</td>
		</tr>
	<tr>
		<th>DATE</th>
		<td>${dto.community_regdate }</td>
	</tr>
	<tr>
		<th>HIT</th> 
		<td>${dto.community_hit }</td>
	</tr>
	<tr>
		<th>TITLE</th>
		<td>${dto.community_title }</td>
	</tr>
	<tr>
		<th>CONTENT</th>
		<td>${dto.community_content }
		<br>
		<img src="${dto.community_oringin_filename }">
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<c:choose>
				<c:when test="${sessionScope.loginMember.m_no eq dto.member_no || sessionScope.loginMember.m_type eq '관리자' }">
					<input type="button" value="수정" onclick="location.href='CommunityController?command=updateform&community_id=${dto.community_id}'">
					<input type="button" value="삭제" name="com_button" id="com_button" onclick="location.href='CommunityController?command=boarddelete&community_id=${dto.community_id}'">
				<script>
					$(function(){
						$('#com_button').click(function(){
							self.window.alert("삭제 완료");
							location.href='CommunityController?command=boarddelete&community_id=${dto.community_id}';
						});
					});
				</script>
				
				
				</c:when>
			</c:choose>
			<input type="button" value="목록" onclick="location.href='<%=application.getContextPath()%>/CommunityController?command=list'">
		
		</td>
	</tr>
</table>
</div>
		
	<!-- footer include -->
	<jsp:include page="../../../resources/form/footer.jsp" />
</body>
</html>
