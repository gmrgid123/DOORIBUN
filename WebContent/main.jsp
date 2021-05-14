<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
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
    <title>두리번 - 나만의 서울 여행코스 만들기</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="author" content="">
</head>

<body id="home" data-spy="scroll" data-target="#navbar-wd" data-offset="98">
	
	<!-- header include -->
	<jsp:include page="resources/form/header.jsp" />


    <!-- Start Banner -->
    <div class="ulockd-home-slider">
        <div class="container-fluid">
            <div class="row">
                <div class="pogoSlider" id="js-main-slider">
                    <div class="pogoSlider-slide" style="background-image:url(resources/images/slider-01.png);">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="slide_text">
                                        <h3><span class="theme_color">서울</span> 여행코스 만들기</h3>
                                        <br>
                                        <h4>서울 곳곳의 여행지를 한 눈에 보고<br>나만의 여행코스를 만들어보세요</h4>
                                        <br>
                                        <p><b>나만의 여행코스를 공유하고
                                            <br>새로운 만남도 찾아보세요</b></p>
                                        <a class="contact_bt" href="<%=application.getContextPath()%>/coursewrite">서울코스 만들기</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="pogoSlider-slide" style="background-image:url(resources/images/slider-02.png);">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="slide_text">
                                        <h3><span class="theme_color">서울</span> 여행코스 만들기</h3>
                                        <br>
                                        <h4>서울 곳곳의 여행지를 한 눈에 보고<br>나만의 여행코스를 만들어보세요</h4>
                                        <br>
                                        <p><b>나만의 여행코스를 공유하고
                                            <br>새로운 만남도 찾아보세요</b></p>
                                        <a class="contact_bt" href="about.jsp">여행지 소개</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- .pogoSlider -->
            </div>
        </div>
    </div>
    <!-- End Banner -->

    <div class="section layout_padding">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="full center">
                        <div class="heading_main text_align_center">
                            <h2><span class="theme_color">팀장</span> 김민수</h2>
                            <p class="large">박성민 / 김정우 / 이현지 / 정수인 / 김주현</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    

  


	<!-- footer include -->
	<jsp:include page="resources/form/footer.jsp" />

</body>

</html>