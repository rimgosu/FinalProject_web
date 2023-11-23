<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>좌표로 주소를 얻어서 회원정보 update</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">

<!-- Favicon -->
<link href="img/favicon.ico" rel="icon">

<!-- Google Web Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Inter:wght@700;800&display=swap"
	rel="stylesheet">

<!-- Icon Font Stylesheet -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
	rel="stylesheet">

<!-- Libraries Stylesheet -->
<link href="lib/animate/animate.min.css" rel="stylesheet">
<link href="lib/owlcarousel/assets/owl.carousel.min.css"
	rel="stylesheet">

<!-- Customized Bootstrap Stylesheet -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Template Stylesheet -->
<link href="css/style.css" rel="stylesheet">

<style>
.map_wrap {
	position: relative;
	width: 100%;
	height: 350px;
}

.title {
	font-weight: bold;
	display: block;
}

.hAddr {
	position: absolute;
	left: 10px;
	top: 10px;
	border-radius: 2px;
	background: #fff;
	background: rgba(255, 255, 255, 0.8);
	z-index: 1;
	padding: 5px;
}

#centerAddr {
	display: block;
	margin-top: 2px;
	font-weight: normal;
}

.bAddr {
	padding: 5px;
	text-overflow: ellipsis;
	overflow: hidden;
	white-space: nowrap;
}

body {
	margin-top: 20px;
	color: #1a202c;
	text-align: left;
	background-color: #e2e8f0;
}
</style>
</head>
<body style="background: white;">

	<!-- JavaScript Libraries -->
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
	<script src="lib/wow/wow.min.js"></script>
	<script src="lib/easing/easing.min.js"></script>
	<script src="lib/waypoints/waypoints.min.js"></script>
	<script src="lib/owlcarousel/owl.carousel.min.js"></script>
	<div class="container-xxl bg-white p-0">
		<jsp:include page="header.jsp"></jsp:include>
		<div class="container">
			<div class="map_wrap">
				<div id="map"
					style="width: 100%; height: 100%; position: relative; overflow: hidden;"></div>
				<div class="hAddr">
					<span class="title">현재 위치를 클릭해주세요.</span> <span id="centerAddr"></span>
					<div id="addressInfo">
						<p id="cityName"></p>
						<p id="roadAddress"></p>

					</div>
				</div>
			</div>
			<br>
			<div style="text-align:right;">
				<a href="/boot/update"  style="display: inline-block; padding: 10px 20px; background-color: pink; color: white;  border-radius: 3px;"> 현재 위치 입력 완료 </a>			
			</div>
		</div>
	</div>
	<script src="js/main.js"></script>
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=fa8ff46cd5bf462c2b89f5178674aa04&libraries=services"></script>
	<script>
    	 /* 도로명주소, 도시이름 보내는 function */
        function sendAddressToServer(AddressData) {     	
        	 fetch('/boot/location', {
        	        method: 'POST',
        	        headers: {
        	            'Content-Type': 'application/json'
        	        },
        	        body: JSON.stringify({
        	        	   roadAddress: AddressData.roadAddress,
        	               cityName: AddressData.cityName,
        	               latitude: AddressData.latitude, 
        	               longitude: AddressData.longitude 
        	        })
        	    })
        	    .then(response => {
        	        if (response.ok) {
        	        	 return response.json();
        	        }else {
        	            throw new Error('Network response was not ok. Status: ' + response.status);
        	        }
        	    })
        	    .then(data => console.log(data))
        	    .catch(error => console.error('Error:', error));
        	}
/* 지도 위에 출력하는것  */
        function displayAddress(roadAddress, cityName, latitude, longitude) {
            var roadSplit = roadAddress.split(' ');
            var cityName = roadSplit[0];
            var cityDistrict = roadSplit[1]; // '00구' 부분만 가져옵니다.	            
            var modifiedRoadAddress = cityName + ' ' + cityDistrict; 
            var latitude = latitude;
            var longitude = longitude;

            var AddressData = {
                    roadAddress: modifiedRoadAddress,
                    cityName: cityName,
                    latitude : latitude,
                    longitude : longitude
                };     	
            document.getElementById('roadAddress').innerText = '도로명 주소: ' + modifiedRoadAddress;
            document.getElementById('cityName').innerText = '도시명: ' + cityName; // '00시' 보여주기
            
            // 수정된 주소 데이터를 서버로 전송
            sendAddressToServer(AddressData);
           
        }
        
        function searchDetailAddrFromCoords(coords, callback) {
            var geocoder = new kakao.maps.services.Geocoder();
            geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
        }
        

        function initMap(position) {
            var mapContainer = document.getElementById('map');
            var mapOption = {
                center: new kakao.maps.LatLng(position.coords.latitude, position.coords.longitude),
                level: 1
            };
            
            var map = new kakao.maps.Map(mapContainer, mapOption); 
            var geocoder = new kakao.maps.services.Geocoder(); 
                       
            kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
            	 var clickedLatlng = mouseEvent.latLng; 
                searchDetailAddrFromCoords(mouseEvent.latLng, function(result, status) {
                    if (status === kakao.maps.services.Status.OK) {
                        var roadAddress = result[0].road_address.address_name;
/*                         var jibunAddress = result[0].address.address_name; */
                        var cityName = result[0].cityName;
                        
                        var latitude =  clickedLatlng.getLat();
                        var longitude = clickedLatlng.getLng();
                        
                        displayAddress(roadAddress, cityName, latitude, longitude);                      
                    }  else {
                        console.log('주소 정보를 찾을 수 없습니다.');
                    }  
                });
            });     
        }

        function getLocation() {
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(function(position) {
                    initMap(position);
                }, function(error) {
                    console.log("Error occurred: " + error.message);
                });
            } else { 
                console.log("Geolocation is not supported by this browser.");
            }
        }

        getLocation();
    </script>
</body>
</html>