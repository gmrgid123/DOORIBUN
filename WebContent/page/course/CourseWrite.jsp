<%@page import="web.dto.TourDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<% response.setHeader("Access-Control-Allow-Origin", "*"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Course Write</title>
<link href="<%=application.getContextPath()%>/resources/css/CourseMapCSS.css" rel="stylesheet" type="text/css">
<style type="text/css"></style>
</head>
<body>
<div>
	<!-- header include -->
	<jsp:include page="../../resources/form/header.jsp" />
	
	
	
	
	<!-- course main section -->
	<div style="margin-left: 10%; margin-right: 10%;">
	
			<!-- map start section -->
			<div class="map_wrap" style="margin-top:100px;">
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
			<!-- 제목 입력 칸 시작 -->
			<form action="/semi_temp/RegistCourseController" method="post">
				<div style="margin-top: 10px;">
					<h3 class="input_title"><label>코스 제목</label></h3>
					<div id="course_title">
						<input class ="title_write" name="course_title" type="text" placeholder="제목을 입력해주세요." required="required">
					</div>
				</div>
			
			<!-- 제목 입력 칸 끝 -->
			<hr>
			<!-- 여행지 선택 입력칸 시작 -->
				<div style="margin-top: 10px; margin-bottom: 15px;">
					<h3 class="input_title"><label>여행지 선택</label></h3>
					<div id="course_input">
						<span><input type="text" class="tour_sel" value="" placeholder="여행지1" readonly="readonly" required="required"></span>
						<span><input type="text" class="tour_sel" value="" placeholder="여행지2" readonly="readonly" required="required"></span>
						<span><input type="text" class="tour_sel" value="" placeholder="여행지3" readonly="readonly" required="required"></span>
						<span><input type="text" class="tour_sel" value="" placeholder="여행지4" readonly="readonly" required="required"></span>
						<span><input type="text" class="tour_sel" value="" placeholder="여행지5" readonly="readonly" required="required"></span>
					</div>
				</div>	
			<!-- 여행지 선택 입력칸 끝 -->
			<hr>	
		<div>
			
				<!-- 여행지의 id값을 hidden으로 넘기기-->
				<input type="hidden" name="tour1" class="tour_id" value="">
				<input type="hidden" name="tour2" class="tour_id" value="">
				<input type="hidden" name="tour3" class="tour_id" value="">
				<input type="hidden" name="tour4" class="tour_id" value="">
				<input type="hidden" name="tour5" class="tour_id" value="">
				
				<!-- 이동수단 선택칸 시작 -->
				<div>
					<h3 class="input_title"><label>이동수단 선택</label></h3>
						<div>	
						      <div id="trans_sel" class="btn-group btn-group-toggle" data-toggle="buttons">
						        <label id="bus_label" class="btn btn-info trans-box" role="button">
						          <input type="radio" name="options_trans" value="대중교통" required ><span style="font-size: 20px;">대중교통</span>
						        </label>
						        <label class="btn btn-info trans-box" role="button">
						          <input type="radio" name="options_trans" value="자전거"><span style="font-size: 20px;">자전거</span>
						        </label>
						        <label class="btn btn-info trans-box" role="button">
						          <input type="radio" name="options_trans" value="도보"><span style="font-size: 20px;">도보</span>
						        </label>
						        <label class="btn btn-info trans-box" role="button">
						          <input type="radio" name="options_trans" value="자가용"><span style="font-size: 20px;">자가용</span>
						        </label>
						      </div>
					     </div>
					     <input  type="hidden" class="trans_state" value="N">
					     
					     <div id="bus_info" style="display: none; margin-top:10px;">
					     	<h3 class="input_title">버스정류장</h3>
					     	<div id="bus_item">
					     	</div>
					     </div>
					     
					  
					     
					     
				</div>
				<!-- 이동수단 선택칸 끝 -->
			     <hr>
			    <!-- 메모 입력칸 시작 -->
			    <div style="margin-top:10px;">
			    	<h3 class="input_title"><label>메모 작성</label></h3>
			    	<textarea name="course_memo" class="memo_text" rows="10" cols="120" placeholder="메모를 작성하세요."></textarea>
			    </div>
				<!-- 메모 입력칸 끝 -->
				
				<!-- 제출 버튼 칸  시작 -->
				<div style="margin-top: 10px;">
					<button type="submit">코스 제출</button>
				</div>	
				<!-- 제출 버튼 칸  끝 -->
			
		</div>
		</form>
	</div><!-- course main section -->	
		
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5452bc8e5a9738b76981e28e7800ca0b"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/resources/js/CourseWriteJS.js"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/resources/js/CourseBusJS.js"></script>
<script type="text/javascript">
</script>
	
	<!-- footer include -->
	<jsp:include page="../../resources/form/footer.jsp" />
</div>
</body>
</html>