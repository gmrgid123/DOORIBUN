
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
    
<!DOCTYPE html>
<html>
<!-- Basic -->

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Mobile Metas -->
    <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">

    <!-- Site Metas -->
    <title>두리번</title>
 

    <style type="text/css">
    #main{
    	height: 750px; width: 100%;
    }
    #select{
    	text-align: center;
    	margin-bottom: 20px;
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
    .butt{
    	border: 5px solid rgb(8, 146, 253);
   		width: 100px;
   		height: 100px;
   		border-radius: 40%;
   		background-color: rgb(8, 146, 253);
   		margin: 30px;
   		font-size: 25px;	
   		font-weight: 900;
   		cursor: pointer;
   		color: white;
    }
    .hover:hover{
        border: 5px solid black;
        color:black;
    }
    
    .topname{
    	font-size: 50px;
    	font-weight: 700;
    }
    
    .mid{
    	font-size: 20px;
    	text-align: left;
	}
	
	#backbutt{
		border: 0px;
		background-color: rgb(8, 146, 253);
		color: white;
		font-size: 50px;
		width: 50%;
		left: 0%;
		position: absolute;
		height: 100%;
		cursor: pointer;
	}
	
	#nextbutt{
		border: 0px;
		background-color: rgb(8, 146, 253);
		color: white;
		font-size: 50px;
		width: 50%;
		position: absolute;
		height: 100%;
		cursor: pointer;
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
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script type="text/javascript">
    //지역별 버튼 script
    	function next(){
    		var val = ${dto.tour_id};
    		
    		if(val < 7){
				location.href="introduceServlet?command=GangseoGu&tour_id=" + (val + 1);
    		}else if(val == 7 || val == 8){
    			location.href="introduceServlet?command=GwanakGu&tour_id=" + (val + 1);
    		}else if(val >= 9 && val <= 11){
    			location.href="introduceServlet?command=SeongbukGu&tour_id=" + (val + 1);
    		}else if(val == 12 || val == 13){
    			location.href="introduceServlet?command=EunpyeongGu&tour_id=" + (val + 1);
    		}else if(val >= 14 && val <= 17){
    			location.href="introduceServlet?command=JungnangGu&tour_id=" + (val + 1);
    		}else if(val >= 18 && val <= 23){
    			location.href="introduceServlet?command=SeochoGu&tour_id=" + (val + 1);
    		}
    	}
    	
    	function back(){
    		var val = ${dto.tour_id};
    		
    		if(val >= 1 && val <= 6){
    			location.href="introduceServlet?command=GangseoGu&tour_id=" + (val - 1);    			
    		}else if(val == 7 || val == 8){
   	 			location.href="introduceServlet?command=GwanakGu&tour_id=" + (val - 1);    			
    		}else if(val >= 9 && val <= 11){
    			location.href="introduceServlet?command=SeongbukGu&tour_id=" + (val - 1);
    		}else if(val == 12 || val == 13){
    			location.href="introduceServlet?command=EunpyeongGu&tour_id=" + (val - 1);	
    		}else if(val >= 14 && val <= 17){
    			location.href="introduceServlet?command=JungnangGu&tour_id=" + (val - 1);
    		}else if(val >= 18 && val <= 23){
    			location.href="introduceServlet?command=SeochoGu&tour_id=" + (val - 1);
    		}
    	}	
    </script>

    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Site Icons -->
    <link rel="shortcut icon" href="#" type="image/x-icon" />
    <link rel="apple-touch-icon" href="#" />


</head>

<body id="about_us" data-spy="scroll" data-target="#navbar-wd" data-offset="98">

	<!-- header include -->
	<jsp:include page="resources/form/header.jsp" />


<div style="margin-top:50px; margin-left: 10%; margin-right: 10%; margin-bottom: 150px;">	
<!-- 지역별 버튼 -->

    <div id="select" style="margin-top: 100px;">
      	<input type="submit" value="강서구" class="butt hover" onclick="location.href='introduceServlet?command=GangseoGu&tour_id=1'">
      	<input type="submit" value="관악구" class="butt hover" onclick="location.href='introduceServlet?command=GwanakGu&tour_id=7'">
      	<input type="submit" value="성북구" class="butt hover" onclick="location.href='introduceServlet?command=SeongbukGu&tour_id=9'">
      	<input type="submit" value="은평구" class="butt hover" onclick="location.href='introduceServlet?command=EunpyeongGu&tour_id=12'">
      	<input type="submit" value="중랑구" class="butt hover" onclick="location.href='introduceServlet?command=JungnangGu&tour_id=14'">
      	<input type="submit" value="서초구" class="butt hover" onclick="location.href='introduceServlet?command=SeochoGu&tour_id=18'">
    </div>
    
<!-- 화면에 값 뿌려주는 div 태그 -->
<%if(!(request.getParameter("tour_id")==null)) {%>
    <div id="main">
		<div id="back"><input type="button" value="ᐊ" id="backbutt" onclick="back();"></div>
		<div id="img"><img class="tourimg" src="page/tour_img/tour_img${dto.tour_id }.png"></div>
		<div id="introduce" style="margin: 20px"><hr>
		<span class="topname">${dto.tour_name }</span><br><hr><br>
		<span class="mid">${dto.tour_content }</span><br><br><br>
		</div>
		<div id="review" style="margin: 20px">
		<hr>
		<span class="mid">주소 : ${dto.tour_addr }</span><br>
		<span class="mid">전화번호 : ${dto.tour_phone }</span><br>
		<span class="mid">주차공간 : ${dto.tour_park }</span><br>
		<hr>
		</div>
		<div id="next"><input type="button" value="ᐅ" id="nextbutt" onclick="next();"></div>
	</div>
<%} %>
</div>
	<!-- footer include -->
	<jsp:include page="resources/form/footer.jsp" />
	
</body>

</html>