
$("#trans_sel label").click(function(){ 
	var click_index = $("#trans_sel label").index(this);
	
	BusDataDisplay(click_index);
		
});



function BusDataDisplay(click_index){
	var tour_val = $(".tour_sel").eq(4).val();
	if(!tour_val==""){
		var Y = 'Y';
		var N = 'N';
		if(click_index==0){
			if(document.getElementsByClassName("trans_state")[0].value == N){
				BusAjax();
				$("#bus_info").css("display","block");
				document.getElementsByClassName("trans_state")[0].value= Y;
			} else {
				return false;
			}
			
		} else {
			$("#bus_info").css("display","none");
			$("#bus_item *").remove();
			document.getElementsByClassName("trans_state")[0].value= N;
		}
	} else {
		alert("여행지 선택을 완료해주세요");
		return false;
	}
	
}


function BusAjax(){
	for(var i=0 ; i < $(".tour_sel").length ; i++){
		(function(i){
		
			$.ajax({
				type:"GET",
				url : "/semi_temp/BusCourseController.json",
				data : {
					"gpsLati" : tourArr[i].tour_lat,
					"gpsLong" : tourArr[i].tour_lon
				},
				dataType : "json",
				async: false,
				success : function(data){
					
					$("#bus_item").append(
							"<table border='1'><thead><tr><th style='width:400px;'>" + tourArr[i].tour_name + " 의 반경 500m 내 버스정류장 목록</th></tr></thead></table>"
					);
					
					if(data==""){
						$("#bus_item table").eq(i).append(
								"<tbody><tr><td>주위에 버스 정류장이 없습니다.</td></tr></tbody>" );
					} else{
					
						$.each(JSON.parse(data), function(key,val){
								$("#bus_item table").eq(i).append(
										"<tbody><tr><td>" + val.nodename + "(정류장번호 : " + val.nodeno +")</td></tr></tbody>" );
						});
					}
					
					
				},
				error : function(){
					console.log("bus ajax error");
				}
			});
		})(i);
		
		
	}
}