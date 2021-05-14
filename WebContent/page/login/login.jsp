<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>    
<% response.setContentType("text/html; charset=UTF-8"); %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>		<!-- JSTL 문법 사용 -->

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device",initial-scale="1">

<title>로그인</title>


<style type="text/css">
    

    .jumbtron{
     	background-color: lightgray;
        padding: 20px;
        border-radius : 20px;
        text-align: center;
    }
    
   
</style>
<script>
	
	function checker(){
		var m_id = document.getElementsByName("m_id")[0].value;
		var m_pw = document.getElementsByName("m_pw")[0].value;
		
		if(m_id.length <=0) {
			alert("아이디를 입력해주세요");
		}else if(m_pw.length <=0) {
			alert("패스워드를 입력해주세요");
		}else {
			var form = document.userinfo;
			
			form.submit();
		}
		
	}
	
</script>


</head>
<body>
	<!-- header include -->
	<jsp:include page="../../resources/form/header.jsp" />
	
 	<!-- 로그인 양식 -->
 	<div class="container" style="margin-left: 40%; margin-right: 40%; margin-top: 100px; margin-bottom: 100px;">
  		<div class="col-lg-4"></div>
  		<div class="col-lg-4"  style="text-align: center;">
   			<div class="jumbtron" style="padding-top: 20px;">
   				<form name = "userinfo" method="post" action="/semi_temp/userLogin">	<!-- 프로젝트네임/web.xml에 있는 컨트롤러 이름 -->
    				<h3 style="text-align: center;">로그인</h3>
    				<div class="form-group">
    					<input type="text" class="form-control" placeholder="아이디" name="m_id" maxlength="20" 
    							value="<%if (session.getAttribute("m_id") != null)
								out.println(session.getAttribute("m_id"));%>" >     
    				</div>    
    				<div class="form-group">
     					<input type="password" class="form-control" placeholder="비밀번호" name="m_pw" maxlength="20">     
    				</div>
    				<div style="text-align: center;">
    				<input style="width : 100px" type="button" class="btn btn-primary form-control" value="로그인" onclick="checker();">&nbsp;&nbsp;
    				<input style="width : 100px" type="button" class="btn btn-primary form-control" value="회원가입" 
								onclick="location.href='join.jsp'">&nbsp;&nbsp;
					<input style="width : 70px" type="reset" name="reset" value="취소" >
					</div>
   				</form>
  			</div>
 		</div> 
 </div>
 
 

 
 	<!-- footer include -->
	<jsp:include page="../../resources/form/footer.jsp" />
</body>
</html>