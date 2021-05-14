<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html;charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<table border="1">
		<tr>
			<th>Driver ID</th>
			<td>${dto.m_id }</td>
		</tr>
		<tr>
			<th>Driver NAME</th>
			<td>${dto.m_name }</td>
		</tr>
		<tr>
			<th>Driver PHONE</th>
			<td>${dto.m_phone }</td>
		</tr>
		<tr>
			<th>Driver EMAIL</th>
			<td>${dto.m_email }</td>
		</tr>
	</table>
</body>
</html>