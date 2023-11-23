<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="cpath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en" style="background: white;">
<head>
<meta charset="utf-8">
<title>Makaan - Real Estate HTML Template</title>
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
.info-front {
	margin-bottom: 40px;
}

input {
	border: none; /* 상단, 좌우, 우측 테두리 제거 */
	border-bottom: 1px solid pink; /* 1px 두께의 검은색 밑줄 스타일 적용 */
	outline: none; /* 포커스 스타일 제거 */
}

.form-select {
	border: none;
}

.info-front {
	margin-bottom: 40px;
}

input {
	border: none;
	border-bottom: 1px solid pink;
	outline: none;
}

.form-select {
	border: none;
}

@media ( max-width : 768px) {
	/* 작은 화면에 대한 스타일 조정 */
	.info-front {
		margin-bottom: 20px;
	}
	input, select {
		width: 100%;
	}
}

#imageForm1 {
	display: inline-block;
	border: 2px solid #ddd;
	padding: 20px;
	border-radius: 10px;
}

#uploadInput {
	display: none;
}

#uploadButton {
	background-color: pink;
	color: white;
	padding: 10px 20px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 4px 2px;
	cursor: pointer;
	border-radius: 5px;
	border: none;
}

body {
	margin-top: 20px;
	color: #1a202c;
	text-align: left;
	background-color: #e2e8f0;
}

.main-body {
	padding: 15px;
}

.card {
	box-shadow: 0 1px 3px 0 rgba(0, 0, 0, .1), 0 1px 2px 0
		rgba(0, 0, 0, .06);
}

.card {
	position: relative;
	display: flex;
	flex-direction: column;
	min-width: 0;
	word-wrap: break-word;
	background-color: #fff;
	background-clip: border-box;
	border: 0 solid rgba(0, 0, 0, .125);
	border-radius: .25rem;
}

.card-body {
	flex: 1 1 auto;
	min-height: 1px;
	padding: 1rem;
}

.gutters-sm {
	margin-right: -8px;
	margin-left: -8px;
}

.gutters-sm>.col, .gutters-sm>[class*=col-] {
	padding-right: 8px;
	padding-left: 8px;
}

.mb-3, .my-3 {
	margin-bottom: 1rem !important;
}

.bg-gray-300 {
	background-color: #e2e8f0;
}

.h-100 {
	height: 100% !important;
}

.shadow-none {
	box-shadow: none !important;
}


#photoLink {
	color: pink;
	width: 30px;
	font-weight: bold;
	text-decoration: underline;
}

.slider-container {
  background: white;
  display: flex;
  align-items: center;
  justify-content: center;
  height: 60%;
  
}

.slider {
  width: 100%;
  max-width: 350px;
  height: 300px;
  margin: 20px;
  text-align: center;
  border-radius: 20px;
  overflow: hidden;
  position: relative;
}

.slides {
  display: flex;
  overflow-x: scroll;
  position: relative;
  scroll-behavior: smooth;
  scroll-snap-type: x mandatory;
}

.slide {
  justify-content: center;
  align-items: center;
  flex-shrink: 0;
  width: 100%;
  height: 300px;
  scroll-snap-align: center;
  margin-right: 0px;
  box-sizing: border-box;
  background: white;
  transform-origin: center center;
  transform: scale(1);
}


.slide__text {
  font-size: 40px;
  font-family: sans-serif;
}


.slide a {
  background: none;
  border: none;
}

a.slide__prev,
.slider::before {
  transform: rotate(135deg);
  -webkit-transform: rotate(135deg);
  left: 5%;
}

a.slide__next,
.slider::after {
  transform: rotate(-45deg);
  -webkit-transform: rotate(-45deg);
  right: 5%;
}

.slider::before,
.slider::after,
.slide__prev,
.slide__next {
  position: absolute;
  top: 48%;
  width: 35px;
  height: 35px;
  border: solid black;
  border-width: 0 4px 4px 0;
  padding: 3px;
  box-sizing: border-box;
}

.slider::before,
.slider::after {
  content: "";
  z-index: 1;
  background: none;
  pointer-events: none;
}

#card-body-top{
	height:530px;
}
.image-box {
  display: inline-block;
    width: 100%;
    height: 90%;
    overflow: hidden;
    object-fit: cover;
    border-radius: 5px;

}

.image-box img {
  width: 100%; /* 이미지를 부모 요소에 꽉 채웁니다. */
  height: 100%; /* 이미지를 부모 요소에 꽉 채웁니다. */

}

</style>
</head>
<body style="background: white;">
	<div class="container-xxl bg-white p-0">
		<jsp:include page="header.jsp"></jsp:include>
		<!-- Navbar End -->


		<!-- Header Start -->
		<br> <br>

		<div class="container">
			<div class="row">
				<div class="col-12 text-center">
					<h2>Profile</h2>
				</div>
			</div>
			<div class="container">
				<div class="main-body">
					<!-- Breadcrumb -->
					<!--           <nav aria-label="breadcrumb" class="main-breadcrumb">
            <ol class="breadcrumb">
              <li class="breadcrumb-item"><a href="index.html">Home</a></li>
              <li class="breadcrumb-item"><a href="javascript:void(0)">User</a></li>
              <li class="breadcrumb-item active" aria-current="page">User Profile</li>
            </ol>
          </nav> -->
					<!-- /Breadcrumb -->
					<div class="row gutters-sm">
						<div class="col-md-4 mb-3">
							<div class="card">
								<div class="card-body">
									<div class="d-flex flex-column align-items-center text-center">
										<div class="slider-container">
											<!-- 프로필 사진 슬라이드 -->
											<div class="slider">
												<div class="slides">
													<c:forEach items="${imageDatas}" var="imageData"
														varStatus="i">
														<div id="slides__${i.count}" class="slide">
															<div class="image-box">
																<img src="data:image/jpeg;base64,${imageData}" alt="S3 Image" width="200">
															</div>

															<c:choose>
																<c:when test="${i.count eq 1}">
																	<!-- i가 1일때는 이전버튼 :4, 다음버튼은 2  -->
																	<a class="slide__prev" href="#slides__4" title="prev"></a>
																	<a class="slide__next" href="#slides__2" title="Next"></a>
																</c:when>
																<c:when test="${i.count eq 4}">
																	<!-- i가 4일때는 이전버튼 :3, 다음버튼은 1  -->
																	<a class="slide__prev" href="#slides__3" title="prev"></a>
																	<a class="slide__next" href="#slides__1" title="Next"></a>
																</c:when>
																<c:otherwise>
																	<a class="slide__prev" href="#slides__${i.count-1}"
																		title="prev"></a>
																	<a class="slide__next" href="#slides__${i.count+1}"
																		title="Next"></a>
																</c:otherwise>
															</c:choose>
														</div>
													</c:forEach>
												</div>
											</div>
										</div>

										<div class="mt-3">
											<h4>${mvo.nickname}님 </h4>
											<p class="text-secondary mb-1">${mvo.job}</p>
											<p class="text-muted font-size-sm">${mvo.address}</p>
											<a href="/boot/info" id="photoLink"> 사진 추가 및 업데이트 </a>
										</div>
										<a href="sendlike" class="nav-item nav-link">보낸 좋아요</a>
									</div>
								</div>
							</div>

						</div>
						<div class="col-md-8">
							<div class="card mb-3">
								<div class="card-body">
									<div class="row">
										<div class="col-sm-3">
											<h6 class="mb-0">Nick</h6>
										</div>
										<div class="col-sm-9">${mvo.nickname}</div>
									</div>
									<hr>
									<div class="row">
										<div class="col-sm-3">
											<h6 class="mb-0">Age</h6>
										</div>
										<div class="col-sm-9">${mvo.age}</div>
									</div>
									<hr>
									<div class="row">
										<div class="col-sm-3">
											<h6 class="mb-0">Phone</h6>
										</div>
										<div class="col-sm-9">${mvo.phone}</div>
									</div>
									<hr>
									<div class="row">
										<div class="col-sm-3">
											<h6 class="mb-0">Address</h6>
										</div>
										<div class="col-sm-9">${mvo.address}</div>
									</div>
									<hr>
									<div class="row">
										<div class="col-sm-3">
											<h6 class="mb-0">Interest</h6>
										</div>
										<div class="col-sm-9">${mvo.interest}</div>
									</div>
									<hr>
									<div class="row">
										<div class="col-sm-3">
											<h6 class="mb-0">MBTI</h6>
										</div>
										<div class="col-sm-9">${mvo.mbti}</div>
									</div>
									<hr>
									<div class="row">
										<div class="col-sm-3">
											<h6 class="mb-0">Sport</h6>
										</div>
										<div class="col-sm-9">${mvo.sport}</div>
									</div>
									<hr>
									<div class="row">
										<div class="col-sm-3">
											<h6 class="mb-0">Smoke</h6>
										</div>
										<div class="col-sm-9">${mvo.smoking}</div>
									</div>
									<hr>
									<div class="row">
										<div class="col-sm-3">
											<h6 class="mb-0">Drink</h6>
										</div>
										<div class="col-sm-9">${mvo.drinking}</div>
									</div>
									<hr>
									<div class="row">
										<div class="col-sm-3">
											<h6 class="mb-0">Job</h6>
										</div>
										<div class="col-sm-9">${mvo.job}</div>
									</div>
									<hr>
									<div class="row">
										<div class="col-sm-3">
											<h6 class="mb-0">Education</h6>
										</div>
										<div class="col-sm-9">${mvo.school}</div>
										<hr>
										<div class="card mt-3">
											<textarea class="form-control" id="aboutme" name="aboutme"
												rows="9" readonly="readonly" style="resize: none;">${mvo.aboutme}</textarea>
										</div>
									</div>

								</div>
							</div>
							<hr>
							<div class="row">
								<div class="col-sm-12">
									<a class="btn btn-info " target="__blank" href="update">수정</a>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
			<!-- Header End -->


			<!-- Back to Top -->
			<a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i
				class="bi bi-arrow-up"></i></a>
		</div>

		<!-- JavaScript Libraries -->
		<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
		<script src="lib/wow/wow.min.js"></script>
		<script src="lib/easing/easing.min.js"></script>
		<script src="lib/waypoints/waypoints.min.js"></script>
		<script src="lib/owlcarousel/owl.carousel.min.js"></script>

		<!-- Template Javascript -->
		<script src="js/main.js"></script>

		<script>
       
       function uploadImage() {
            const input = document.getElementById('uploadInput');
            const file = input.files[0];

            if (file) {
              const formData = new FormData();
              formData.append('image', file);

              // 서버로 데이터 전송
              fetch('/upload-endpoint', {
                method: 'POST',
                body: formData,
              })
              .then(response => response.json())
              .then(data => {
                console.log('Upload successful:', data);
              })
              .catch(error => {
                console.error('Error uploading image:', error);
              });
            }
          }
       
   </script>
</body>
</html>