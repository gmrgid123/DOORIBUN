<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("UTF-8"); %> 
<% response.setContentType("text/html; charset=UTF-8"); %>     
 
<%@ page import="com.oreilly.servlet.multipart.FileRenamePolicy"%>
<%@ page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy, java.util.*" %>

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
    
/*     function check_onclick(){
    	theFrom = document.next_form;
    	
    	if(theFrom.qna_title=="" || theFrom.qna_writer=="" || theFrom.qna_content=="" ){
    		alert("필수 입력란이 비었습니다.");
    	}   		
    	
    } */
    
/* 	$(document).ready(function(){ 
		$("#qna_submit").click(function(){
				if($("#qna_title").val().length==0){ 
					alert("제목을 입력하세요."); 
				return false; 
				}
				if($("#qna_writer").val().length==0){ 
					alert("작성자를 입력하세요.");
				return false; 
				}
				if($("#qna_content").val().length==0){ 
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
	<h1>Q&A</h1>
	
	<!-- 글 작성  -->
	<form action="../../../QnaController" method="post" enctype="multipart/form-data" id="next_form" name="next_form">
		<input type="hidden" name="command" value="boardwrite">
		
		<table class="table">
			<tr>
				<th>TITLE</th>
				<td><input type="text" id="qna_title" name="qna_title" placeholder="제목을 입력하세요." required="required" style="width:1000px;"></td>	
			</tr>
			<tr>
				<th>CONTENT</th>
				<td><textarea name="qna_content" id="qna_content" placeholder="내용을 입력하세요." required="required"></textarea>
				<input type="file" name="qna_file">
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
				<input type="submit" value="등록" id="qna_submit" name="qna_submit" onclick="check_onclick()">
				<input type="button" value="취소" onclick="location.href='<%=application.getContextPath()%>/QnaController?command=list'">
				</td>
			</tr>
		</table>
  		
  		
	</form>
	</div>
	
	
	
	<!-- footer include -->
	<jsp:include page="../../../resources/form/footer.jsp" />
</body>
</html>