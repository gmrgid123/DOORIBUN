<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html;charset=UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<form action="/semi_temp/taxi.do" method="post">
<input type="hidden" name="command" value="taxi_update">
<input type="hidden" name="seq" value="${dto.taxi_id }">
	<table border="1">
		<tr>
			<h3>출발지/도착지 선택</h3>
		</tr>
					<tr>
						<th>주소 직접 선택</th>
						<th>관광지에서 선택</th>
					</tr>
					<tr>
						<td >출발지<input type="text" size="40" placeholder="주소선택 클릭!" onclick="upPopup1()" readonly="readonly" id="start" name="place"
								value="${dto.start_addr }"></td>
						<td><input type="text" size="40" placeholder="관광지선택 클릭!" onclick="" readonly="readonly" id="" name=""></td>
					</tr>
					<tr>
						<td>도착지<input type="text" size="40" placeholder="주소선택 클릭!" onclick="upPopup2()" readonly="readonly" id="end" name="place"
						 		value="${dto.end_addr }"></td>
						<td><input type="text"  size="40" placeholder="관광지선택 클릭!" onclick="" readonly="readonly" id="" name=""></td>
											
					</tr>
	</table>
		<br>
		<br>
		<br>
				
	<table border="1" style="width:100%; margin:auto;">
					
					
	<tr >
		<td colspan="3">
		출발지<input type="text" readonly="readonly" id="start1" name="start_addr" value="${dto.start_addr }"> 
		도착지<input type="text" readonly="readonly" id="end1" name="end_addr" value="${dto.end_addr }">
		</td>						
	</tr>
	<tr>
		<td ><input id="someDate" name="date" type="date" value="${dto.getBook_dateDate()}" >
			<input id="someTime" name="time" type="time" value="${dto.getBook_dateTime()}">
		</td>
	</tr>
	<tr>	
		<td colspan="3"><input type="submit" value="수정">
		<input type="button" value="취소" onclick="location.href='/semi_temp/taxi.do?command=list'"></td><br>
	</tr>
					
	</table>
		
</form>
<script type="text/javascript">
function upPopup1(){
	window.open("<%=application.getContextPath()%>/page/taxi/addr_start.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes");
}

function upPopup2(){
	window.open("<%=application.getContextPath()%>/page/taxi/addr_end.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes");
}


</script>
<jsp:include page="../../resources/form/footer.jsp" />
</body>
</html>