<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>여행지 선택</h1>
	<table border="1">
	<colgroup>
		<col style="width: 100px;">
		<col style="width: 200px;">
		<col style="width: 500px">
	</colgroup>
	
	<c:choose>
			<c:when test="${empty list }">
				<tr><td>리스트의 자료가 없다</td><td>꽝</td></tr>
			</c:when>
			<c:otherwise>
				<c:forEach var="dto" items="${list }">
					<tr >
					<!-- ${dto.tour_id},'${dto.tour_name } -->
						<td ><a onclick="send(this)" >${dto.tour_name }</a></td>
						<td ><span>${dto.tour_addr }</span></td>
						<td><span>${dto.tour_content }</span></td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>
	<script type="text/javascript">
	 function send(ele){
		 //alert(document.getElementById("addr").value);
    	 
		 //a 태그에 text값 가져오는 코드
		 var td = $(ele).parent().next().children();
		 console.log(td.text());
    	 
		 opener.document.getElementById("siteend").value = td.text() ;
    	 opener.document.getElementById("end1").value = td.text() ;

		
		window.close();
		
    }

	</script>
	<script type="text/javascript"  src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<style type="text/css">
	a{
	text-decoration: underline;
	font-weight: bolder;
	color: blue;
	cursor: pointer;
	}
	
	</style>
	
</body>
</html>