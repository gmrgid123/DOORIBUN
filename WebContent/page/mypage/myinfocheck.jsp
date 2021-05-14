<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인정보 조회/변경</title>
<style type="text/css">
	#infowrapper1 {
		height: 500px;
	}
	#infocontainer1 {
		display: grid;
		grid-template-columns: 1fr 5fr;
	}
	#mypagetab {
		position: relative;
		top: 100px;
	}
	#pwcheck {
		position: relative;
		top: 200px;
		text-align: center;
	}
	#pw {
		height: 29px;
	}
	#submit {
		height: 30px;
	}
</style>
</head>
<body>
	<jsp:include page="../../resources/form/header.jsp" />
		<div id="infowrapper1">
			<div id="infocontainer1">
				<div id="mypagetab">
					<jsp:include page="mypagetab.jsp" />
				</div>
				<div>
					<form action="<%=application.getContextPath() %>/myinfoView" method="post">
						<div id="pwcheck">
							비밀번호를 입력해 주세요.<br>
							비밀번호:
							<input type="password" name="pw" id="pw">
							<input type="submit" value="확인" id="submit">
						</div>
					</form>
				</div>
			</div>
		</div>
	<jsp:include page="../../resources/form/footer.jsp" />
</body>
</html>