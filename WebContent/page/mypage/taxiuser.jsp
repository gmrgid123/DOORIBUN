<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 코스 목록</title>
<style type="text/css">
	#wrapper {
		height: 500px;
	}
	#container {
		display: grid;
		grid-template-columns: 1fr 5fr;
	}
	#mypagetab {
		position: relative;
		top: 100px;
	}
</style>
</head>
<body>
	<jsp:include page="../../resources/form/header.jsp" />
	<div id="wrapper">
		<div id="container">
				<div id="mypagetab">
					<jsp:include page="mypagetab.jsp" />
				</div>
		</div>
	</div>
	<jsp:include page="../../resources/form/footer.jsp" />
</body>
</html>