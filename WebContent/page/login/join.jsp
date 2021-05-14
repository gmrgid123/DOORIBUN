<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>    
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<style type="text/css">
thead th {

.modal-content {
    width: inherit;
    height: inherit;
    margin: 0 auto;
    pointer-events: none;
}


</style>
<script src="<%=application.getContextPath() %>/resources/js/bootstrap.js"></script>
<script type="text/javascript">
	var isChecking = false;
	
	
	function registerCheckFunction(){
		var m_id = $('#m_id').val();
		if(m_id.length <= 3){
			alert('아이디를 네글자 이상 입력하세요.');
			return;
		}
		
		$.ajax({
			type: 'POST',
			url: '<%=application.getContextPath() %>/UserRegisterCheckServlet',
			data: { m_id : m_id}, // {파라미터변수 이름 : var m_id값}
			success: function(result){
				
				if(result == 1) {
					isChecking = true;
					var id_val = $("#m_id").val();
					alert(id_val + "는 사용할 수 있는 아이디입니다.");
				}else {
					console.log("로그인 중복체크 하는 중");
					
					isChecking = false;
					$("#m_id").val("");
					alert('사용할 수 없는 아이디입니다.');
					$("#m_id").focus();
				}
				
			}
		})
	}
	
	
	
	
	
	
	
	 
</script>

<title>회원가입</title>
<script type="text/javascript" src="<%=application.getContextPath() %>/resources/js/members.js"></script>	<!-- members.js 파일을 사용해 회원가입 조건 충족 확인 -->

<style type="text/css">
    
    thead tr{
    	background-color: lightgray;
    }
   
   #regist_table{
    height: 700px; width: 800px; 
   }
   tbody tr td *{
   	font-size: 18px;
   }
   
   
    
</style>


</head>
<body>
	<!-- header include -->
	<jsp:include page="../../resources/form/header.jsp" />
	<div class="container" style="margin-top: 50px; margin-bottom: 100px;">
		<form method="post" action="/semi_temp/userRegister" name="reg_frm"> <!-- reg_frm이라는 이름을 사용해 members.js에서 확인 -->
			<table id="regist_table" class="table table-bordered table-hover" style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="3" style="text-align: center"><h4>회원 가입</h4></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="width: 110px;"><h5>아이디</h5></td>
						<td><input style="font-size: 15px;" class="form-control" type="text" id="m_id" name="m_id" maxLength="20" placeholder="아이디를 입력해주세요" required="required"></td>
						<td style="width: 80px;"><button style="font-size: 15px;" class="btn btn-primary" type= "button" onclick="registerCheckFunction();">중복체크</button></td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>비밀번호</h5></td>
						<td colspan="2"><input style="font-size: 15px;" class="form-control" type="password" id="m_pw1" name="m_pw1" maxLength="20" placeholder="비밀번호를 입력해주세요" required="required"></td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>비밀번호 확인</h5></td>
						<td colspan="2"><input style="font-size: 15px;" class="form-control" type="password" id="m_pw2" name="m_pw2" maxLength="20" placeholder="비밀번호를 입력해주세요" required="required"></td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>이름</h5></td>
						<td colspan="2"><input style="font-size: 15px;" class="form-control" type="text" id="m_name" name="m_name" maxLength="20" placeholder="이름을 입력해주세요" required="required"></td>
					</tr>
					
					<tr>
						<td style="width: 110px;"><h5>이메일</h5></td>
						<td colspan="2"><input style="font-size: 15px;" class="form-control" type="email" id="m_email" name="m_email" placeholder="이메일을 입력해주세요" required="required"></td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>연락처</h5></td>
						<td ><input style="font-size: 15px;" class="form-control" type="text" id="m_phone" name="m_phone"  required="required" placeholder="연락처를 입력해주세요" ></td>
						<td style="width: 80px;"><button style="font-size: 15px;" class="btn btn-primary" type= "button" onclick="phoneCheck();">중복체크</button></td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>타입</h5></td>
						<td colspan="2">
							<div class="form-group" style="text-align: center; margin: 0 auto;">
								<div class="btn-group btn-group-toggle" data-toggle="buttons">
									<label class="btn btn-primary active" role="button">
										<input type="radio" id="m_type1" name="m_type" autocomplete="off" value= "일반회원" style="font-size: 15px;" checked required="required">일반회원
									</label>
									<label class="btn btn-primary" role="button" >
										<input type="radio" id="m_type2" name="m_type" autocomplete="off" value= "택시기사" style="font-size: 15px;" >택시기사
									</label>
									
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td style="text-align: left" colspan="3"><input class="btn btn-primary pull-right" type="button" value="회원가입" onclick="infoConfirm()"></td>
						<!-- 회원가입 버튼을 누르면 members.js파일에 있는 infoConfirm()으로 이동 -->
					</tr>
				</tbody>
			
			
			</table>
		</form>
	
	</div>
	
	

 	<!-- footer include -->
	<jsp:include page="../../resources/form/footer.jsp" />

</body>
</html>