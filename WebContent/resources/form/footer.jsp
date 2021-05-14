<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
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

</head>
<body id="home" data-spy="scroll" data-target="#navbar-wd" data-offset="98">
    <!-- Start Footer -->
    <footer class="footer-box">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="logo">
                        <a href="<%=application.getContextPath() %>/index.jsp"><img src="<%=application.getContextPath() %>/resources/images/footer_logo.png" alt="#" /></a>
                    </div>
                </div>
                <div class="col-lg-12 white_fonts">
                    <h4 class="text-align">Contact Us</h4>
                </div>
                <div class="margin-top_30 col-md-8 offset-md-2 white_fonts">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="full icon text_align_center">
                                <img src="<%=application.getContextPath() %>/resources/images/social1.png">
                            </div>
                            <div class="full white_fonts text_align_center">
                                <p>서울특별시 강남구 역삼동 
                                    <br>테헤란로14길 6</p>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="full icon text_align_center">
                                <img src="<%=application.getContextPath() %>/resources/images/social2.png">
                            </div>
                            <div class="full white_fonts text_align_center">
                                <p>admin@kh.co.kr</p>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="full icon text_align_center">
                                <img src="<%=application.getContextPath() %>/resources/images/social3.png">
                            </div>
                            <div class="full white_fonts text_align_center">
                                <p>1544-9970</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </footer>
    <!-- End Footer -->

    <div class="footer_bottom">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <p class="crp">© 2019 RD resume . All Rights Reserved.</p>
                    <ul class="bottom_menu">
                        <li><a href="#">About Us</a></li>
                        <li><a href="#">Find jobs</a></li>
                        <li><a href="<%=application.getContextPath() %>/contact.jsp">Contact us</a></li>
                        <li><a href="#">Terms of Service</a></li>
                        <li><a href="#">Privacy</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
	<!-- scroll-to-top button start -->
    <a href="#" id="scroll-to-top" class="hvr-radial-out"><i class="fa fa-angle-up"></i></a>
   	<!-- scroll-to-top button end -->

</body>
</html>