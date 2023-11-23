<%@page import="kr.spring.entity.Info"%>
<%@page import="java.util.Map"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="cpath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
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

/* #imageForm1 {
	display: inline-block;
	border: 2px solid #ddd;
	padding: 20px;
	border-radius: 10px;
} */

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

div.gallery {
  border: 1px solid #ccc;
}

div.gallery:hover {
  border: 1px solid #777;
}

div.gallery img {
  width: 100%;
  height: auto;
}

div.desc {
  padding: 15px;
  text-align: center;
}

* {
  box-sizing: border-box;
}

.responsive {
  padding: 0 6px;
  float: left;
  width: 24.99999%;
}

@media only screen and (max-width: 700px) {
  .responsive {
    width: 49.99999%;
    margin: 6px 0;
  }
}

@media only screen and (max-width: 500px) {
  .responsive {
    width: 100%;
  }
}

.clearfix:after {
  content: "";
  display: table;
  clear: both;
}
 #move{
 	color : graytext;
 	text-align:right;
 	margin-top:10px;
 	margin-bottom:10px;
 }

</style>
</head>
<body>
	<div class="container-xxl bg-white p-0">
		<jsp:include page="header.jsp"></jsp:include>
		<!-- Navbar End -->
		

		<!-- Header Start -->
		<br> <br>
		<div class="container">
			<div class="row">
				<div class="col-12 text-center">
					<h2>4개의 사진을 업로드 해주세요</h2>
					<br> <br> <br> <br>
					<div class="card">
						<div class="card-body mt-3" >
							<div class="row">
							<!-- 사진 업로드  -->
							<c:forEach items="${imageDatas}" var="imageData" varStatus="i" >    
								<div class="responsive">
									<div class="gallery"> 
									<img src="data:image/jpeg;base64,${imageData}" alt="S3 Image" class="rounded" style="width:300px; height:350px;">
											<form action="${cpath}/fileUpload" method="post" enctype="multipart/form-data" id="imageForm${i.count}" >											   
												<input type="file" id="photo${i.count}" name="file" accept="image/*">
												<input type ="hidden" id="photoNum" name="photoNum" value="${i.count}">
												<button type="submit" id="uploadButton${i.count}">업로드</button>
											</form>	
										</div>
									</div>					
							</c:forEach>
							</div>
							<br> <br> <br>							
						</div>
						<div class="row">
							<div class="col-sm-10" >
							 	 <h3 id="move">추가 정보 입력 페이지로 이동 </h3>
							</div>
							<div class="col-sm-2" >
							 	 <a href="/boot/update" class="btn btn-lg btn-lg-square" style="background-color:pink; width:70px; height:70px;"> <i class="bi bi-chevron-right" style="color: white"></i> </a>
							</div>	
						</div>					
						<div>
							
						</div>
						<!-- div 컨테이너  -->
						<!-- 정보 입력 끝!!!!!!!!!!!!!!!!!!!!!!!!  -->
						<br> <br> <br>
						<!-- Header End -->


						<!-- Back to Top -->
						<a href="#"
							class="btn btn-lg btn-primary btn-lg-square back-to-top"><i
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

						
</body>
</html>