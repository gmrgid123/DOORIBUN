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
<style type="text/css">
    #main{
    	height: 450px; width: 100%;
    	display: compact;
    	
    }
    #back{
    	width: 8%;
    	height: 100%;
    	position: relative;
    	left: 0%;
    	
    }
    #next{
  		width: 8%;
  		height: 100%;
  		position: relative;
  		left: 92%;
  		top: -303%;
    }
    #img{
		width: 45%; 
		height: 100%;
		position: relative;
		left:5%;
		top:-100%;
    }
    #introduce{
		width: 40%; 
		height: 70%;
		position: relative;
		top: -200%;
		left: 50%;
		text-align:center;
    }
    #review{
		width: 40%; 
		height: 25%;
		position: relative;
		top: -200%;
		left: 50%;
	}
	.tourimg{
		overflow: hidden;
		display: flex;
		align-items: center;
		justify-content: center;
		width: 100%;
		height: 100%;
	}
</style>
</head>
<body>

<h1>여행지 선택</h1>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	<table border="1">
	<colgroup>
		<col style="width: 100px;">
		<col style="width:500px">
	</colgroup>
	
	<c:choose>
	
			<c:when test="${empty list }">
				<tr><td>리스트의 자료가 없다</td><td>꽝</td></tr>
			</c:when>
			<c:otherwise>
				<c:forEach var="dto" items="${list }">
    <div id="main">
		<div id="img"><img class="tourimg" src="<%=application.getContextPath() %>/page/tour_img/tour_img${dto.tour_id }.png"></div>
		<div id="introduce" style="margin: 20px">
		<span class="topname"><a class="name_sel" href="javascript:sel_tour(${dto.tour_id},'${dto.tour_name }',${selbox });">${dto.tour_name }</a></span><br><hr><br>
		<span class="mid">${dto.tour_content }</span><br><br><br>
		</div>
		<div id="review" style="margin: 20px">
		<hr>
		<span class="mid">주소 : ${dto.tour_addr }</span><br>
		<span class="mid">전화번호 : ${dto.tour_phone }</span><br>
		<span class="mid">주차공간 : ${dto.tour_park }</span><br>
		<hr>
		</div>
	</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>
	<script type="text/javascript" src="<%=application.getContextPath()%>/resources/js/jquery-3.5.1.js"></script>
	<script type="text/javascript">
		function sel_tour(tour_id,tour_name,index){
			var tourSel_arr=opener.document.getElementsByClassName("tour_sel");
			var tourId_arr=opener.document.getElementsByClassName("tour_id");
			
			for(var i=0; i<tourSel_arr.length;i++){
				if(tourSel_arr[i].value == tour_name && tourId_arr[i].value == tour_id){
					alert("이미 고른 여행지입니다.");
					return false;
				}
			}
			opener.document.getElementsByClassName("tour_sel")[index].value = tour_name;
			opener.document.getElementsByClassName("tour_id")[index].value = tour_id;
			opener.makeRoute();
			self.close();
			
		}
		

	</script>
	
</body>
</html>