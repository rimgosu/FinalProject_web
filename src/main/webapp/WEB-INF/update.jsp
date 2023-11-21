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
	outline: none; /* 포커스 스타일 제거 */
}

.form-select {
	border: none;
}

.info-front {
	margin-bottom: 40px;
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
					<h2>Profile 수정화면 </h2>
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
										<img src="img/team-1.jpg" alt="Admin" class="rounded-circle"
											width="150">
										<div class="mt-3">
											<h4>${mvo.nickname}</h4>
											<p class="text-secondary mb-1">${mvo.job}</p>
											<p class="text-muted font-size-sm">${mvo.address}</p>
											<form id="imageForm1">
												<label for="uploadInput" id="uploadButton">이미지 선택</label> <input
													type="file" id="uploadInput" accept="image/*">
												<button type="button" onclick="uploadImage()"
													id="uploadButton">업로드</button>
											</form>
										</div>
									</div>
								</div>
							</div>
							</div>
							<!-- card  -->
					
						<div class="col-md-8">
							<form action="${cpath}/update" method="post">
								<input type="hidden" name="username" value="${mvo.username}">
								<div class="card mb-3">
									<div class="card-body">
										<div class="row">
											<div class="col-sm-3">
												<h6 class="mb-0">Nickname</h6>
											</div>
											<div class="col-sm-9">
												<input type="text" name="nickname" value="${mvo.nickname}">
											</div>
										</div>
										<hr>
										<div class="row">
											<div class="col-sm-3">
												<h6 class="mb-0">Age</h6>
											</div>
											<div class="col-sm-9">
												<input type="number" id="age" name="age" value="${mvo.age}">
											</div>
										</div>
										<hr>
										<div class="row">
											<div class="col-sm-3">
												<h6 class="mb-0">Phone</h6>
											</div>
											<div class="col-sm-9">
												<input type="number" id="phone" name="phone" value="${mvo.phone}">
											</div>
										</div>
										<hr>
										<div class="row">
											<div class="col-sm-3">
												<h6 class="mb-0">Address</h6>
											</div>
											<div class="col-sm-9">
												<input type="text" id="address" name="address" value="${mvo.address}">
											</div>
										</div>
										<hr>
										<div class="row">
											<div class="col-sm-3">
												<h6 class="mb-0">Interest</h6>
											</div>
											<div class="col-sm-9">
												<input type="text" id="interest" name="interest" value="${mvo.interest}">
											</div>
										</div>
										<hr>
										<div class="row">
											<div class="col-sm-3">
												<h6 class="mb-0">MBTI</h6>
											</div>
											<div class="col-sm-9">
												<select class="form-select" id="mbti" name="mbti" >
												       <optgroup label="E">
														<option value="ESTP">ESTP</option>
														<option value="ESFP">ESFP</option>
														<option value="ESFJ">ESFJ</option>
														<option value="ESFJ">ESFJ</option>
														<option value="ENTP">ENTP</option>
														<option value="ENTJ">ENTJ</option>
														<option value="ENFP">ENFP</option>																					
														<option value="ENFJ">ENFJ</option>
													</optgroup>
													<optgroup label="I">
														<option value="ISFP">ISFP</option>
														<option value="ISFJ">ISFJ</option>
														<option value="ISTP">ISTP</option>
														<option value="ISTJ">ISTJ</option>
														<option value="INTP">INTP</option>
														<option value="INTJ">INTJ</option>
														<option value="INFP">INFP</option>											
														<option value="INFJ">INFJ</option>
													</optgroup>
												</select>
											</div>
										</div>
										<hr>
										<div class="row">
											<div class="col-sm-3">
												<h6 class="mb-0">Sport</h6>
											</div>
											<div class="col-sm-9">
												<select class="form-select" id="sport" name="sport" >
														<option value="yes">예</option>
														<option value="no">아니오</option>
												</select>
											</div>
										</div>
										<hr>
										<div class="row">
											<div class="col-sm-3">
												<h6 class="mb-0">Smoke</h6>
											</div>
											<div class="col-sm-9">
												<select class="form-select" id="smoking" name="smoking">
														<option value="yes">네</option>
														<option value="no">아니오</option>
												</select>
											</div>
										</div>
										<hr>
										<div class="row">
											<div class="col-sm-3">
												<h6 class="mb-0">Drink</h6>
											</div>
											<div class="col-sm-9">
												<select class="form-select" id="drinking" name="drinking">
														<option value="yes">예</option>
														<option value="no">아니오</option>
												</select>
											</div>
										</div>
										<hr>
										<div class="row">
											<div class="col-sm-3">
												<h6 class="mb-0">Job</h6>
											</div>
											<div class="col-sm-9">
												<input type="text" id="job" name="job"  value="${mvo.job}">
											</div>
										</div>
										<hr>
										<div class="row">
											<div class="col-sm-3">
												<h6 class="mb-0">Education</h6>
											</div>
											<div class="col-sm-9">
												<input type="text" id="school" name="school" value="${mvo.school}">
											</div>
										</div>
										<div class="row">
											<div class="card mt-3">
												<textarea class="form-control" id="aboutme" name="aboutme"
													rows="9" style="resize: none;">${mvo.aboutme}</textarea>
											</div>
										</div>
	
									</div>
									<!-- card body  -->
								</div>
							<!-- card mb-3"  -->
							<hr>
							<div class="row">
								<div class="col-sm-12">
									<input type="submit" class="btn btn-info" value="수정">
								</div>
							</div>
							</form>
						</div>
						
						<!-- col md-8 -->
					</div>
					<!-- main body  -->
				</div>
				<!-- row gutters-sm -->
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