<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta charset="utf-8">
<title>좌표로 주소를 얻어내기</title>
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
</style>
</head>
<body>
	<div class="map_wrap">
    <div id="map" style="width: 100%; height: 100%; position: relative; overflow: hidden;"></div>
    <div class="hAddr">
        <span class="title">지도중심기준 행정동 주소정보</span>
        <span id="centerAddr"></span>
        <div id="addressInfo">
            <p id="roadAddress"></p>
            <p id="jibunAddress"></p>
        </div>
    </div>
</div>


	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=fa8ff46cd5bf462c2b89f5178674aa04&libraries=services"></script>
	  <script>
        function sendAddressToServer(addressData) {
        	 fetch('/boot/location', {
        	        method: 'POST',
        	        headers: {
        	            'Content-Type': 'application/json'
        	        },
        	        body: JSON.stringify(addressData)
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

        function displayAddress(roadAddress, jibunAddress) {
            document.getElementById('roadAddress').innerText = '도로명 주소: ' + roadAddress;
            document.getElementById('jibunAddress').innerText = '지번 주소: ' + jibunAddress;
           
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
                searchDetailAddrFromCoords(mouseEvent.latLng, function(result, status) {
                    if (status === kakao.maps.services.Status.OK) {
                        var roadAddress = result[0].road_address.address_name;
                        var jibunAddress = result[0].address.address_name;
                        displayAddress(roadAddress, jibunAddress);
                        var addressData = {
                            roadAddress: roadAddress,
                            jibunAddress: jibunAddress
                        };
                        sendAddressToServer(addressData);
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