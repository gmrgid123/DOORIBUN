<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>코스 목록</title>
<style type="text/css">
.pagemenu{
	margin-top: 10px;
	margin-bottom: 30px;
}
.pagemenu-ul{
	list-style-type: none;
	text-align: center;
	margin: 0;
	padding: 0;
}
.pagemenu-ul li {
	display: inline-block;
	font-size: 15px;
	border: 1px solid;
}
.pagemenu-ul li a{
	padding: 15px;
	outline:0;
	display: block;
	
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
	<!-- header include -->
	<jsp:include page="../../resources/form/header.jsp" />
<div  style="margin-left: 10%; margin-right: 10%; margin-top: 50px;">
	<p style="text-align: center;">
	<img src="<%=application.getContextPath() %>/resources/images/COURSE.jpg">
	<p>
	<div>
		<table class="table">
			<col width="100px"> <col width="100px"> <col width="550px"> <col width="150px"> <col width="70px">
			<thead>
			<tr>
				<th>번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			</thead>
			<tbody>
			<c:choose>
				<c:when test="${empty list }">
					<tr>
						<td colspan="6">---작성한 코스가 존재하지 않습니다---</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="dto" items="${list }">
						<tr>
							<td>${dto.course_id }</td>
							<td>${member_dao.getMemberId(dto.member_no) }</td>
							<td><a href="<%=application.getContextPath() %>/DetailCourseController?course_id=${dto.course_id }">${dto.course_title }</a></td>
							<td>${dto.getCourse_regdateDate() }</td>
							<td>${dto.course_hit }</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			</tbody>
			<tr>
				<td colspan="6">
					<input type="button" value="코스 작성" onclick="location.href='<%=application.getContextPath() %>/coursewrite'" style="float: right;">
				</td>
			</tr>
		</table>
	</div>
	<% 
	int p = Integer.parseInt(request.getParameter("p"));
	int list_max = Integer.parseInt(request.getAttribute("list_max").toString());
	int max = list_max/10 + 1;
	int tmp=0;
	if((p%10)==0){
		tmp = p/10;
	}else{
		tmp = p/10 + 1;
	}
	int end_num = tmp*10;
	int start_num = end_num-9;
	
	%>
	<nav class="pagemenu">
		<ul class="pagemenu-ul">
			<%
			if(!(start_num==1)){
			%>
			<li><a href="<%=application.getContextPath()%>/courseList.view?p=<%=start_num-1%>">&lt;</a></li>
			<%
			}
			for(int i=start_num; i<=end_num; i++){
			if(i==p){
			%>
			<li style="background: #eee;"><a  href="<%=application.getContextPath()%>/courseList.view?p=<%=i%>"><%=i%></a></li>
			<%
			} else {
			%>
			<li><a href="<%=application.getContextPath()%>/courseList.view?p=<%=i%>"><%=i%></a></li>
			<%
			}
				if(i==max){
					end_num=max;
					break;
				}
			}
			if(!(end_num==max)){
			%>
			<li><a href="<%=application.getContextPath()%>/courseList.view?p=<%=end_num+1%>">&gt;</a></li>
			<%
			}
			%>
		</ul>
	</nav>
	
</div>	

	<!-- footer include -->
	<jsp:include page="../../resources/form/footer.jsp" />
</body>
</html>