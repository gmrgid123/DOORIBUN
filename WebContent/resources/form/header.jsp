<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Mobile Metas -->
    <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">

    <!-- Site Metas -->
    <title>Create CV Resume - Responsive HTML5 Template</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Site Icons -->
    <link rel="shortcut icon" href="#" type="image/x-icon" />
    <link rel="apple-touch-icon" href="#" />

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=application.getContextPath() %>/resources/css/bootstrap.min.css">
    <!-- Pogo Slider CSS -->
    <link rel="stylesheet" href="<%=application.getContextPath() %>/resources/css/pogo-slider.min.css">
    <!-- Site CSS -->
    <link rel="stylesheet" href="<%=application.getContextPath() %>/resources/css/style.css">
    <!-- Responsive CSS -->
    <link rel="stylesheet" href="<%=application.getContextPath() %>/resources/css/responsive.css">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="<%=application.getContextPath() %>/resources/css/custom.css">

<style type="text/css">

	
	.login_menu{
		margin-right: 10%;
	}
	.login_menu ul{
		margin: 0;
		padding: 0;
		float: right;
		line-height: 30px;
	}
	.login_menu ul li{
		margin: 0;
		padding: 0;
		float: left;
	}
	.login_menu ul li a{
		border-left: 1px #e1e4e8 solid;
		font-family: "NanumGothic";
  		font-size: 13px;
    	color: #555;
    	padding: 0 0 0 7px;
    	margin-left: 6px;
	}
</style>
</head>
<body id="home" data-spy="scroll" data-target="#navbar-wd" data-offset="98">

	<!-- Start header -->
	<header style="height: 130px;">
		<div style="width:100%; margin:0 auto;  height: 30px; background: #f8f8f8;">
    		<nav class="login_menu">
    			<ul>
    				<%if(session.getAttribute("loginMember")==null){%>
    				<li><a href="<%=application.getContextPath() %>/page/login/login.jsp">로그인</a></li>
    				<li><a href="<%=application.getContextPath() %>/page/login/join.jsp">회원가입</a></li>
    				<li></li>
    				<% }else{%>
    				<li><a>${sessionScope.loginMember.m_id } 님 환영합니다</a></li>
    				<li><a href="<%=application.getContextPath() %>/page/mypage/mypagemain.jsp">마이페이지</a></li>
                    <li><a href="<%=application.getContextPath() %>/Logout">로그아웃</a></li>
                    <%} %>
    			</ul>
    		</nav>
    	</div>
    	<div class="top-header">
            <nav class="navbar header-nav navbar-expand-lg">
                <div class="container-fluid">
                    <a class="navbar-brand" href="<%=application.getContextPath() %>/index.jsp"><img src="<%=application.getContextPath() %>/resources/images/logo.png" alt="image"></a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar-wd" aria-controls="navbar-wd" aria-expanded="false" aria-label="Toggle navigation">
                        <span></span>
                        <span></span>
                        <span></span>
                    </button>
                    <div class="collapse navbar-collapse justify-content-end" id="navbar-wd">
                        <ul class="navbar-nav">
                            <li><a class="nav-link active" href="<%=application.getContextPath() %>/index.jsp">Home</a></li>
                            <li><a class="nav-link" href="<%=application.getContextPath() %>/about.jsp">여행지 소개</a></li>
                            <li><a class="nav-link" href="<%=application.getContextPath() %>/coursewrite" id="header_course">코스 생성</a></li>
                            <li><a class="nav-link" href="<%=application.getContextPath() %>/courseList.view?p=1">코스 목록</a></li>
                            <li><a class="nav-link" href="<%=application.getContextPath() %>/CommunityController?command=list">커뮤니티</a></li>
                            <li><a class="nav-link" href="<%=application.getContextPath() %>/NoticeController?command=list">공지사항</a></li>
                            <li><a class="nav-link" href="<%=application.getContextPath() %>/QnaController?command=list">Q&amp;A</a></li>
                            <li><a class="nav-link" href="<%=application.getContextPath() %>/taxi.do?command=list">택시</a></li>
                            
                            
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
        </header>
    	<!-- End header -->
    
     <!-- ALL JS FILES -->
    <script src="<%=application.getContextPath() %>/resources/js/jquery-3.5.1.js"></script>
    <script src="<%=application.getContextPath() %>/resources/js/popper.min.js"></script>
    <script src="<%=application.getContextPath() %>/resources/js/bootstrap.min.js"></script>
    <!-- ALL PLUGINS -->
    <script src="<%=application.getContextPath() %>/resources/js/jquery.magnific-popup.min.js"></script>
    <script src="<%=application.getContextPath() %>/resources/js/jquery.pogo-slider.min.js"></script>
    <script src="<%=application.getContextPath() %>/resources/js/slider-index.js"></script>
    <script src="<%=application.getContextPath() %>/resources/js/smoothscroll.js"></script>
    <script src="<%=application.getContextPath() %>/resources/js/form-validator.min.js"></script>
    <script src="<%=application.getContextPath() %>/resources/js/contact-form-script.js"></script>
    <script src="<%=application.getContextPath() %>/resources/js/isotope.min.js"></script>
    <script src="<%=application.getContextPath() %>/resources/js/images-loded.min.js"></script>
    <script src="<%=application.getContextPath() %>/resources/js/custom.js"></script>
</body>
</html>