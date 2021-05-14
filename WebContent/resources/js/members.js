

var phoneChecking = false;

function phoneCheck(){
		
		var m_phone = $("#m_phone").val();
		if(m_phone.length <=10){
			alert('연락처를 제대로 입력해주세요.');
			return;
		}
		if(!document.reg_frm.m_phone.value.match(/^[0-9]{11}$/)){//폰벨류값이 뒤에있는형식과 같냐?
			alert("정상적인 연락처를 입력해주세요.");
			return;
		}
		
		//서버랑 연결할거야 -아작스
		$.ajax ({
			type: 'GET',
			url: '/semi_temp/UserPhoneCheckServlet',
			data: { m_phone : m_phone},
			success: function(result){
				
				if(result == 1) {
					phoneChecking = true;
					var id_val = $("#m_phone").val();
					alert(id_val + "는 사용할 수 있는 연락처입니다.");
				}else {
					
					phoneChecking = false;
					$("#m_phone").val("");
					alert('사용할 수 없는 연락처입니다.');
					$("#m_phone").focus();
				}
				
			}
		
			})
		
		
	}

function infoConfirm() {
	if (document.reg_frm.m_id.value.length == 0) {
		alert("아이디를 반드시 입력하세요.");
		reg_frm.m_id.focus();
		return;
	}
	if (document.reg_frm.m_id.value.length < 4) {
		alert("아이디는 4글자 이상이어야 합니다.");
		reg_frm.m_id.focus();
		return;
	}
	if(!isChecking){
		alert("아이디 중복체크를 해주세요.");
		reg_frm.m_id.focus();
		return;
		
	}
	if (document.reg_frm.m_pw1.value.length == 0) {
		alert("비밀번호는 필수사항입니다.");
		reg_frm.m_pw1.focus();
		return;
	}
	if (document.reg_frm.m_pw1.value != document.reg_frm.m_pw2.value) {
		alert("비밀번호가 일치하지 않습니다.");
		reg_frm.m_pw2.focus();
		return;
	}
	if (document.reg_frm.m_name.value.length == 0) {
		alert("이름은 필수사항입니다.");
		reg_frm.m_name.focus();
		return;
	}
	if (document.reg_frm.m_email.value.length == 0) {
		alert("이메일은 필수사항입니다.");
		reg_frm.m_email.focus();
		return;
	}
	if (document.reg_frm.m_phone.value.length == 0) {
		alert("연락처는 필수사항입니다.");
		reg_frm.m_phone.focus();
		return;
	}
	if(!phoneChecking){
		alert("연락처 중복체크를 해주세요.")
		reg_frm.m_phone.focus();
		return;
	}
	
	
	document.reg_frm.submit();// type이 submit이면 위 함수들을 실행이 안되고 바로 submit- action으로 넘어감 그래서 button으로 바꾼거

}

/*
function doublecheck(){
	var id = $("#m_id").val();
	
	if(id.length <=0){
		alert("아이디를 입력해주세요.")
	}else{
		 $.ajax({		//ajax를 이용한 비동기 통신 연결, form 서브밋에서 넘겨줬는데, 에이작스가 대신 데이터를 넘겨서 결과값을 받는다.
	            url : "/semi_temp/userRegister?m_id="+id, //?겟방식의 키와 벨류
	            type : 'get', //겟방식일때는 데이터타입이 필요 ㄴㄴ/데이터타입 : 제이슨 -> 포스트방식으로 데이터를 넘길때 제이슨 형태로 넘기겠다.
	            success : function(msg) {
	            	var result = JSON.parse(msg);
	            	alert(result);
	            	if(result){
	            		console.log("true");
	            	}
	               console.log(msg);
	               
	            }, 
	            error : function(err) {
	               alert("실패 !"+ JSON.stringify(err));
	            }
	         });
	}
	
	
}
*/

function updateInfoConfirm() {			//정보 수정 조건 검사
	if (document.reg_frm.m_pw.value == "") {
		alert("비밀번호를 입력하세요.");
		document.reg_frm.m_pw.focus();
		return;
	}
	if (document.reg_frm.m_pw.value != document.reg_frm.pw_check.value) {
		alert("비밀번호가 일치하지 않습니다.");
		reg_frm.m_pw.focus();
		return;
	}
	if (document.reg_frm.m_email.value.length == 0) {
		alert("이메일은 필수사항입니다.");
		reg_frm.m_email.focus();
		return;
	}
	
	if (document.reg_frm.m_phone.value.length == 0) {
		alert("연락처는 필수사항입니다.");
		reg_frm.m_phone.focus();
		return;
	}
	if(!document.reg_frm.m_phone.value.match(/^[0-9]{11}$/)){//폰벨류값이 뒤에있는형식과 같냐?
		alert("정상적인 연락처를 입력해주세요.");
		return;
	}
	if(!phoneChecking){
		alert("연락처 중복체크를 해주세요.")
		reg_frm.m_phone.focus();
		return;
	}
	
	alert("회원 정보 수정에 성공하셨습니다!");
	document.reg_frm.submit();
}


