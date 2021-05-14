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
<link href="<%=application.getContextPath()%>/resources/css/CourseMapCSS.css" rel="stylesheet" type="text/css">
<style type="text/css">
.trans-box {
	width: 150px;
	font-size: 20px;
	font-weight: 400;
    text-align: center;
	color: #fff;
    background-color: #17a2b8;
    border-color: #17a2b8;
    border-radius: .25rem;
}
.writer-info{
	float: left;
}
.article-info{
	float: right;
}
textarea:focus{
	outline: none;
}
</style>
</head>
<body>
	<!-- header include -->
	<jsp:include page="../../resources/form/header.jsp" />
	
<!-- course detail section start -->
<div style="margin-left: 10%; margin-right: 10%;">

	<!-- 코스 제목 칸 -->	
	<div style="margin-top:100px; margin-bottom: 30px;">
		<div><h1>${dto.course_title}</h1></div>
		<div style="border:1px solid black;">
			<div class="writer-info" >
				<span>작성자 : ${member_dao.getMemberId(dto.member_no)}</span>
			</div>
			<div class="article-info">
				<span>추천 : ${dto.course_good}</span>
				<span> | </span>
				<span>조회 : ${dto.course_hit}</span>
				<span> | </span>
				<span>작성일 : ${dto.getCourse_regdateDate() } ${dto.getCourse_regdateTime() }</span>
			</div>
		</div>
	</div>
	<!--  코스 제목 칸 끝 -->	

	<hr>			
	<!-- map start section -->
	<div class="map_wrap" style="margin-top:30px;">
		<div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>
			
		<div id="menu_wrap" class="bg_white">
			<ul id="placesList"></ul>
			<div id="pagination"></div>
		</div>
	</div>
	<!--  map end section -->
	
	<!--  지도 범위 재설정 시작 -->
		<div>
			<button onclick="setBounds();" style="margin-top:10px;">지도 범위 재설정</button>
		</div>
	<!--  지도 범위 재설정 끝 -->
	<hr>
	<!-- 이동 수단 칸 -->
	<div>
		<h3>이동수단</h3>
		<label class="trans-box">${dto.transport }</label>
	</div>
	
	
	<div id="bus_info" style="display: none; margin-top:10px;">
		<h3 class="input_title">버스정류장</h3>
		<div id="bus_item">
		</div>
	</div>
	
	<hr>
	
	<!-- 메모 칸 시작 -->
	<div style="margin-bottom: 20px;">
		<span><h2>Memo</h2></span>
		<textarea class="text_memo" rows="10" cols="120" readonly="readonly" style="resize: none;">${dto.course_memo }</textarea>
	</div>
	<!-- 메모 칸 종료 -->

	
	<div style="margin-bottom: 100px;">
		<span style="float: left;">
		<button onclick="location.href='<%=application.getContextPath()%>/courseList.view?p=1';">목록</button>
		<c:choose>
				<c:when test="${sessionScope.loginMember.m_no eq dto.member_no || sessionScope.loginMember.m_type eq '관리자' }">
			<button onclick="location.href='<%=application.getContextPath()%>/UpdateFormCourseController?course_id=${dto.course_id }';">수정</button>
			<button onclick="delCheck();">삭제</button>
				</c:when>
		</c:choose>
		</span>
		<br>
		<hr>
	</div>
	
	
</div>
<!-- course detail section end -->

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5452bc8e5a9738b76981e28e7800ca0b"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/resources/js/CourseDetail.js"></script>
<script type="text/javascript">
var tour = [${dto.tour1}, ${dto.tour2} , ${dto.tour3} , ${dto.tour4}, ${dto.tour5}]; // 에러 발생하면 "" 붙여보자
var transport = "${dto.transport }";

function delCheck(){
	if(confirm("코스 삭제를 하시겠습니까?")){
		location.href='<%=application.getContextPath()%>/DeleteCourseController?course_id=${dto.course_id }';
	}else{
		
	}
}
</script>
	

	<!-- footer include -->
	<jsp:include page="../../resources/form/footer.jsp" />
</body>
</html>