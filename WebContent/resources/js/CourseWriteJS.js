var tourArr=[];
var marker_temp = [];
var polyline;
var bound_cnt;
			
			
var container = document.getElementById('map');
var options = {
				center: new kakao.maps.LatLng(37.564465644280055, 126.97562896260159),
				level: 5
};
	
var map = new kakao.maps.Map(container, options);
			
			


function makeRoute() {
	var tourTag = document.getElementsByClassName("tour_id");
	
	var listEl = document.getElementById('placesList');
	removeAllChildNods(listEl);
	var cnt=0;
	for(var i=0 ; i<tourTag.length; i++){
		if(!tourTag[i].value){
			break;
		}else{
			cnt++;
			(function(i){
					$.ajax({
						type:"POST",
						url:"/semi_temp/TourSelectController",
						data : "Tid="+tourTag[i].value,
						dataType : "json",
						async: false,
						success : function(data){
							tourArr[i]=data;
							// 1개만 select 할것이 아니라 필요한 id개수 만큼 select
							// 시전하도록 수정이 필요한 것 같다
							console.log(i+" 번째 lat : " + tourArr[i].tour_lat +" / "+i+" 번째 lon : " + tourArr[i].tour_lon);
							makeMarker(i, tourArr[i].tour_lat, tourArr[i].tour_lon);
						},
						error : function(request,status,error) {  
							console.log("TourSelectController Ajax Error");
					     }
					});
				})(i);
			}
	}
	if(cnt>1){
	makeRouteLine(cnt);
	bound_cnt = cnt;
	}
	cnt = 0;
}


			
			
			
function makeMarker(index,lat,lon){
				if(marker_temp[index]){
					//마커 삭제
					marker_temp[index].setMap(null);
				}
				
				
			    var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
		        imageSize = new kakao.maps.Size(36, 37),  // 마커 이미지의 크기
		        imgOptions =  {
		            spriteSize : new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
		            spriteOrigin : new kakao.maps.Point(0, (index*46)+10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
		            offset: new kakao.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
		        },
		        markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions);
				
				// 마커 위치
				var markerPosition = new kakao.maps.LatLng(lat, lon);
				
				// 마커 정보 할당
				marker_temp[index] = new kakao.maps.Marker({ 
					position : markerPosition,
					image : markerImage
				});
			/*
			 * marker_temp.splice(index,0, new kakao.maps.Marker({ position :
			 * markerPosition }));
			 */
				console.log(index +" : "+marker_temp[index].getPosition().toString());				
				// 마커 생성
				marker_temp[index].setMap(map);
				
				var iwContent = '<div style="padding:5px">'+tourArr[index].tour_name+'<br>'+tourArr[index].tour_content+'</div>';
				iwRemoveable = true;
				
				var infowindow = new kakao.maps.InfoWindow({ 
					content : iwContent,
					removable : iwRemoveable //삭제버튼으로 추측 중
				});
				
				
				var listEl = document.getElementById('placesList'), 
				    menuEl = document.getElementById('menu_wrap'),
				    fragment = document.createDocumentFragment(), 
				    listStr = '';


				var bounds = new kakao.maps.LatLngBounds();
				//선택 여행지 목록 요소
				itemEl = getListItem(index, tourArr[index]);
				// LatLngBounds 객체에 좌표를 추가합니다
				bounds.extend(markerPosition);
					
					
				fragment.appendChild(itemEl);
				
				
				//마커의 마우스 이벤트 처리 부분
				kakao.maps.event.addListener(marker_temp[index],'click',function(){ 
					infowindow.open(map,marker_temp[index]);
				} );
				
				/*kakao.maps.event.addListener(marker_temp[index],'mouseout',function() {
	                infowindow.close();
	            });*/
				
				itemEl.addEventListener('click', function(){
					map.setBounds(bounds);
					infowindow.open(map,marker_temp[index]);
				});
				
				/*itemEl.onmouseover =  function () {
					infowindow.open(map,marker_temp[index]);
	            };

	            itemEl.onmouseout =  function () {
	                infowindow.close();
	            };*/
	            
			    // 검색결과 항목들을 검색결과 목록 Elemnet에 추가합니다
			    listEl.appendChild(fragment);
			    menuEl.scrollTop = 0;

			    // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
			    map.setBounds(bounds);
				
				
			}
			
			//마커 라인 생성
			function makeRouteLine(cnt){
				if(polyline){
					polyline.setMap(null);  
					polyline = null;
				}
			
				var linePath = [];
				for(var i=0 ; i < cnt ; i++){
						
					console.log("makeRouteLine:"+i+" : " + marker_temp[i].getPosition());
					linePath[i] = marker_temp[i].getPosition();
				}
				// 지도에 표시할 선을 생성합니다
				polyline = new kakao.maps.Polyline({
				    path: linePath, // 선을 구성하는 좌표배열 입니다
				    strokeWeight: 5, // 선의 두께 입니다
				    strokeColor: '#FF0000', // 선의 색깔입니다
				    strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록
										// 투명합니다
				    strokeStyle: 'solid' // 선의 스타일입니다
				});

				// 지도에 선을 표시합니다
				polyline.setMap(map);  
				
				var path = polyline.getPath();
				 if (path.length > 1) {

			            var distance = Math.round(polyline.getLength()), // 선의 총
																			// 거리를
																			// 계산합니다
			                content = getTimeHTML(distance); // 커스텀오버레이에 추가될
																// 내용입니다
			                
			            // 그려진 선의 거리정보를 지도에 표시합니다
			            showDistance(content, path[path.length-1]);  
			             
			        }
			}
			
			
			

			
			
			
			
			// 지도 목록 항목을 Element로 반환하는 함수입니다
			function getListItem(index, places) {

			    var el = document.createElement('li'),
			    itemStr = '<span class="markerbg marker_' + (index+1) + '"></span>' +
			                '<div class="info">' +
			                '   <h5 style="font-weight:bold; font-size:16px;">' + places.tour_name + '</h5>';

			  
			   itemStr += '    <span style="font-weight:bold;">' + places.tour_addr + '</span>';
			    
			                 
			  itemStr += '  <span class="tel" style="font-weight:bold;">' + places.tour_phone  + '</span>' +
			                '</div>';           

			    el.innerHTML = itemStr;
			    el.className = 'item';

			    return el;
			}
			


			
			
			$(".tour_sel").click(function() {
				
				var tour_index = $(".tour_sel").index(this);
				if(tour_index>0){
					for(var i=0; i<tour_index; i++){
						if($(".tour_sel").eq(i).val() == ""){
							alert("앞의 여행지를 먼저 선택해주세요.");
							return false;
						}
					}
				}
				var url = "/semi_temp/WriteCourseController.do?command=tourlist&selbox="+tour_index;
				window.open(url,"tour select page", "width=800px,height=800px");

				
			});

			
			
			
			
			// 그려진 선의 거리정보를 지도에 표시합니다
			var distanceOverlay;
			function showDistance(content, position) {
			    
			    if (distanceOverlay) { // 커스텀오버레이가 생성된 상태이면
			        
			        // 커스텀 오버레이의 위치와 표시할 내용을 설정합니다
			        distanceOverlay.setPosition(position);
			        distanceOverlay.setContent(content);
			        
			    } else { // 커스텀 오버레이가 생성되지 않은 상태이면
			        
			        // 커스텀 오버레이를 생성하고 지도에 표시합니다
			        distanceOverlay = new kakao.maps.CustomOverlay({
			            map: map, // 커스텀오버레이를 표시할 지도입니다
			            content: content,  // 커스텀오버레이에 표시할 내용입니다
			            position: position, // 커스텀오버레이를 표시할 위치입니다.
			            xAnchor: 0,
			            yAnchor: 0,
			            zIndex: 3  
			        });      
			    }
			}
			
			//지도 경로의 거리 시간 등등 표시 영역
			function getTimeHTML(distance) {

			    // 도보의 시속은 평균 4km/h 이고 도보의 분속은 67m/min입니다
			    var walkkTime = distance / 67 | 0;
			    var walkHour = '', walkMin = '';

			    // 계산한 도보 시간이 60분 보다 크면 시간으로 표시합니다
			    if (walkkTime > 60) {
			        walkHour = '<span class="number">' + Math.floor(walkkTime / 60) + '</span>시간 '
			    }
			    walkMin = '<span class="number">' + walkkTime % 60 + '</span>분'

			    // 자전거의 평균 시속은 16km/h 이고 이것을 기준으로 자전거의 분속은 267m/min입니다
			    var bycicleTime = distance / 227 | 0;
			    var bycicleHour = '', bycicleMin = '';

			    // 계산한 자전거 시간이 60분 보다 크면 시간으로 표출합니다
			    if (bycicleTime > 60) {
			        bycicleHour = '<span class="number">' + Math.floor(bycicleTime / 60) + '</span>시간 '
			    }
			    bycicleMin = '<span class="number">' + bycicleTime % 60 + '</span>분'

			    // 거리와 도보 시간, 자전거 시간을 가지고 HTML Content를 만들어 리턴합니다
			    var content = '<ul class="dotOverlay distanceInfo">';
			    content += '    <li>';
			    content += '        <span class="label">총거리</span><span class="number">' + distance/1000 + '</span>Km';
			    content += '    </li>';
			    content += '    <li>';
			    content += '        <span class="label">도보</span>' + walkHour + walkMin;
			    content += '    </li>';
			    content += '    <li>';
			    content += '        <span class="label">자전거</span>' + bycicleHour + bycicleMin;
			    content += '    </li>';
			    content += '</ul>'

			    return content;
			}




			//지도 위치 잡기
			function setBounds(){ 
				if(bound_cnt>0){
					var bounds = new kakao.maps.LatLngBounds(); 
					for(var i=0; i<bound_cnt; i++){
						bounds.extend(marker_temp[i].getPosition());
					}
					map.setBounds(bounds);
				}
				
			}




			// 목록의 자식 Element를 제거하는 함수입니다
			function removeAllChildNods(el) {   
			    while (el.hasChildNodes()) {
			        el.removeChild (el.lastChild);
			    }
			}

			





/*
 * function tour(tour_id, tour_name, tour_addr, tour_content, tour_lat,
 * tour_lon, tour_agency, tour_phone, tour_park){ this.tour_id = tour_id;
 * this.tour_name = tour_name; this.tour_addr = tour_addr; this.tour_content =
 * tour_content; this.tour_lat = tour_lat; this.tour_lon = tour_lon;
 * this.tour_agency = tour_agency; this.tour_phone = tour_phone; this.tour_park =
 * tour_park;
 * 
 * this.getTourId = function(){ return this.tour_id; }
 * 
 * this.getTourName = function(){ return this.tour_name; }
 * 
 * this.getTourAddr = function(){ return this.tour_addr; }
 * 
 * this.getTourContent = function(){ return this.tour_content; }
 * 
 * this.getTourLat = function(){ return this.tour_lat; }
 * 
 * 
 * this.getTourLon = function(){ return this.tour_lon; }
 * 
 * this.getTourAgency = function(){ return this.tour_agency; }
 *  }
 */