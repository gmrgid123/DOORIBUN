<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("UTF-8"); %> 
<% response.setContentType("text/html; charset=UTF-8"); %>     
 
<%@ page import="com.oreilly.servlet.multipart.FileRenamePolicy"%>
<%@ page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy, java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Community</title>
<!-- include libraries(jQuery, bootstrap) -->
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 

<!-- include summernote css/js-->
<!-- <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script> -->

<!-- <link href="./dist/summernote.css" rel="stylesheet">
<script src="./dist/summernote.js"></script> -->

<!-- 참고 필요 이미지 업로드: https://programmer93.tistory.com/31 -->
<script type="text/javascript">
	$(function(){
   	 $("form").submit(function(){
       if($("form input:text").val() == "" || $("textarea").val() == ""){  //입력하지 않으면 경고창 띄우고 submit 중단시키기
          alert("모두 입력해 주세요");
          return false;  //return false를 해줘야 submit 이벤트가 취소됨
       }
   	 });
 	});
	
	
/* 	$(document).ready(function() {
   	 $('#summernote').summernote({
   		 	width: 1000,				//에디터 넓이
   	 	 	height: 600,                 // 에디터 높이
	 		minHeight: null,             // 최소 높이
	  		maxHeight: null,             // 최대 높이
	 	 	focus: true,                  // 에디터 로딩 후 포커스를 맞출지 여부
	  		lang: "ko-KR",					// 한글 설정
	  		placeholder: '최대 2048자까지 쓸 수 있습니다.'
	  });
	}); */
	
	//url: 보낼 곳
	//enctype: 'multipart/form-data', //파일을 업로드할 때 쓰는 속성

    /* summernote에서 이미지 업로드시 실행할 함수 */
 	function sendFile(file, editor) {
        // 파일 전송을 위한 폼 생성
 		data = new FormData();
 	    data.append("uploadFile", file);
 	    $.ajax({ // ajax를 통해 파일 업로드 처리
 	        type : "POST",
 	        url : "<%=application.getContextPath() %>/uploadImage", 
 	       	data : "data="+data, //!!!!문제점!!!! 키:value 있어야 함....
 	        cache : false,
 	        contentType : false,
 	        processData : false,
 	        success : function(data) { // 처리가 성공할 경우 // (괄호안) 괄호안의 변수에다가 값이 들어가게 되는 것
                // 에디터에 이미지 출력
 	        	$(editor).summernote('editor.insertImage', data.url); //!!!!문제점!!!!onImageUpload 받아줄 곳이 없음
 	        },
 	        error: function(request, status, error){
 	        	console.log(" ");
 	        }
 	    });
 	}
	
/* 	$(document).ready(function(){ 
		$("#community_submit").click(function(){
				if($("#community_title").val().length==0){ 
					alert("제목을 입력하세요."); 
				return false; 
				}
				if($("#member_no").val().length==0){ 
					alert("작성자를 입력하세요.");
				return false; 
				}
				if($("#community_content").val().length==0){ 
					alert("내용을 입력하세요.");
				return false; 
				}
			});		
	});
	 */
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
	<h1>Community</h1>

	<!-- 글 작성  -->
	<form action="../../../CommunityController" method="post" enctype="multipart/form-data">
		<input type="hidden" name="command" value="boardwrite">
		<table class="table">
			<tr>
				<th>TITLE</th>
				<td><input type="text" id="community_title" name="community_title" placeholder="제목을 입력하세요." required="required" style="width:1000px;"></td>	
			</tr>
			<tr>
				<th>CONTENT</th>
				<td><textarea name="community_content" id="community_content" placeholder="내용을 입력하세요."  required="required"></textarea>
				<input type="file" name="community_file">
				<script>
          		  $(document).ready(function() {
              	 /*  $('#summernote').summernote({ // summernote를 사용하기 위한 선언
                    height: 400
					/* callbacks: { // 콜백을 사용
                        // 이미지를 업로드할 경우 이벤트를 발생
					    onImageUpload: function(files, editor, welEditable) {
						    			sendFile(files[0], this);
								}
							} 
						}); */
					});
				</script>
				</td>
				
			</tr>
			
			<tr>
				<td colspan="2">
				<input type="submit" value="등록" id="community_submit" name="community_submit">
				<input type="button" value="취소" onclick="location.href='<%=application.getContextPath()%>/CommunityController?command=list'">
				</td>
			</tr>
		</table>
  		
  		
	</form>
	</div>
	
	
	<!-- footer include -->
	<jsp:include page="../../../resources/form/footer.jsp" />
</body>
</html>