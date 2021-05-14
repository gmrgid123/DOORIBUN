<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Q&A</title>
<!-- include libraries(jQuery, bootstrap) -->
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 

<!-- include summernote css/js-->
<!-- <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script> -->

<!-- <link href="./dist/summernote.css" rel="stylesheet">
<script src="./dist/summernote.js"></script> -->

<script type="text/javascript">

	
/* 	 $(document).ready(function(){ 
			$("#qna_submit").click(function(){
					if($("#qna_title").val().length==0){ 
						alert("제목을 입력하세요."); 
					return false; 
					}
					if($("#qna_content").val().length==0){ 
						alert("내용을 입력하세요.");
					return false; 
					}
				});		
		});
	 */

	 $(function(){
			$('#qna_button').click(function(){
				if(confirm("취소하시겠습니까?")){
					self.location.href='QnaController?command=detail&qna_id=${dto.qna_id}';
				}  else  {
					self.location.href='QnaController?command=updateform&qna_id=${dto.qna_id}';
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
	<h1>Q&A</h1>
	<form action="<%=application.getContextPath() %>/QnaController" method="post" enctype="multipart/form-data">
		<input type="hidden" name="command" value="boardupdate"> 
		<input type="hidden" name="qna_id" value="${dto.qna_id }">
		<table class="table">
			<tr>
				<th>NO</th>
				<td>${dto.qna_id }</td>
			</tr>
			<tr>
				<th>WRITER</th>
				<td>${member_dao.getMemberId(dto.member_no) }</td>
			</tr>
			<tr>
				<th>DATE</th>
				<td>${dto.qna_regdate }</td>
			</tr>
			<tr>
				<th>TITLE</th>
				<td><input type="text" name="qna_title" id="qna_title" value="${dto.qna_title }" required="required" style="width:1000px;"></td>	
			</tr>
			<tr>
				<th>CONTENT</th>
				<td><textarea name="qna_content" id="qna_content" required="required">${dto.qna_content }</textarea>
				<br><input type="text" name="qna_oringin_file" value="${dto.qna_oringin_filename }">
				<input type="file" name="qna_file">
				<!-- multiple -->
			</tr>

			<tr>
				<td colspan="2">
				<input type="submit" value="수정" name="qna_submit" id="qna_submit"> 
				<script>
					$(function(){
						$('#qna_submit').click(function(){
							self.window.alert("수정 완료");
						});
					});
				</script>
				
				<input type="button" value="취소" name="qna_button" id="qna_button"
					onclick="location.href='QnaController?command=detail&qna_id=${dto.qna_id}'">
				</td>
			</tr>
		</table>
	</form>
	</div>
	
	<!-- footer include -->
	<jsp:include page="../../../resources/form/footer.jsp" />
</body>
</html>