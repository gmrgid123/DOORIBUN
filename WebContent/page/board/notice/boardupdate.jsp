<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Notice</title>
<!-- include libraries(jQuery, bootstrap) -->
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 

<!-- include summernote css/js-->
<!-- <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script> -->

<!-- <link href="./dist/summernote.css" rel="stylesheet">
<script src="./dist/summernote.js"></script> -->

<script type="text/javascript">

/* 	$(document).ready(function() {
   	 $('#summernote').summernote({
   		 	width: 1000,				//에디터 넓이
   	 	 	height: 600,                 // 에디터 높이
	 		minHeight: null,             // 최소 높이
	  		maxHeight: null,             // 최대 높이
	 	 	focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
	  		lang: "ko-KR",					// 한글 설정
	  		placeholder: '최대 2048자까지 쓸 수 있습니다.'  	  
	  });
	}); */
	
/* 	 $(document).ready(function(){ 
			$("#notice_submit").click(function(){
					if($("#notice_title").val().length==0){ 
						alert("제목을 입력하세요."); 
					return false; 
					}
					if($("#notice_content").val().length==0){ 
						alert("내용을 입력하세요.");
					return false; 
					}
				});		
		});
 */
 
 $(function(){
		$('#notice_button').click(function(){
			if(confirm("취소하시겠습니까?")){
				self.location.href='NoticeController?command=detail&notice_id=${dto.notice_id}';
			}  else  {
				self.location.href='NoticeController?command=updateform&notice_id=${dto.notice_id}';
			} 
		});
	});

</script>

<style type="text/css">

table.table {
  border-collapse: collapse;
  text-align: left;
  line-height: 1.5;
  border-top: 1px solid #ccc;
  border-left: 3px solid #369;
  margin : 20px 10px;
}
table.table th {
  width: 147px;
  padding: 10px;
  font-weight: bold;
  vertical-align: top;
  color: #153d73;
  border-right: 1px solid #ccc;
  border-bottom: 1px solid #ccc;

}
table.table td {
  width: 349px;
  padding: 10px;
  vertical-align: top;
  border-right: 1px solid #ccc;
  border-bottom: 1px solid #ccc;
}

textarea{
	width:1000px; 
	height:300px;
	resize: vertical;
}

</style>
</head>
<body>
	<!-- header include -->
	<jsp:include page="../../../resources/form/header.jsp" />

	<div  style="margin-left: 20%; margin-right: 20%; margin-top: 50px;">	
	<h1>NOTICE</h1>
	
	<form action="<%=application.getContextPath() %>/NoticeController" method="post" enctype="multipart/form-data">
		<input type="hidden" name="command" value="boardupdate"> 
		<input type="hidden" name="notice_id" value="${dto.notice_id }">
		<table class="table">
			<tr>
				<th>NO</th>
				<td>${dto.notice_id }</td>
			</tr>
			<tr>
				<th>WRITER</th>
				<td>${member_dao.getMemberId(dto.member_no) }</td>
			</tr>
			<tr>
				<th>DATE</th>
				<td>${dto.notice_regdate }</td>
			</tr>
			<tr>
				<th>TITLE</th>
				<td><input type="text" name="notice_title" id="notice_title" value="${dto.notice_title }" required="required" style="width:1000px;"></td>	
			</tr>
			<tr>
				<th>CONTENT</th>
				<td><textarea name="notice_content" id="notice_content" required="required">${dto.notice_content }</textarea>
				<br><input type="text" name="oringin_file" value="${dto.oringin_filename }">
				<input type="file" name="notice_file">
				<!-- multiple -->
			</tr>

			<tr>
				<td colspan="2">
				<input type="submit" value="수정" name="notice_submit" id="notice_submit" > 
				<script>
					$(function(){
						$('#notice_submit').click(function(){
							self.window.alert("수정 완료");
						});
					});
				</script>
				
				
				<input type="button" value="취소" name="notice_button" id="notice_button"
					onclick="location.href='NoticeController?command=detail&notice_id=${dto.notice_id}'">
				</td>
			</tr>
		</table>
	</form>
	</div>
	
	<!-- footer include -->
	<jsp:include page="../../../resources/form/footer.jsp" />
</body>
</html>