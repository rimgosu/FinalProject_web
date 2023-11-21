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
<html lang="en" style="background: white;">
<head>
<meta charset="utf-8">
<title>SimKoong</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">

<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css?family=Roboto:400,700,900&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="fonts/icomoon/style.css">


    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    
    <!-- Style -->
    <link rel="stylesheet" href="css/style.css">

    <title>Contact Form #9</title>

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



h1, h2, h3, h4, h5, h6,
.h1, .h2, .h3, .h4, .h5, .h6 {
  font-family: "Roboto", sans-serif;
  color: #000; }

a {
  -webkit-transition: .3s all ease;
  -o-transition: .3s all ease;
  transition: .3s all ease; }
  a, a:hover {
    text-decoration: none !important; }

.text-black {
  color: #000; }

.content {
  padding: 0 3rem 0; }

.heading {
  font-size: 2.5rem;
  font-weight: 900; }

.form-control {
  border: none;
  border-bottom: 1px solid #ccc;
  padding-left: 0;
  padding-right: 0;
  border-radius: 0;
  background: none; }
  .form-control:active, .form-control:focus {
    outline: none;
    -webkit-box-shadow: none;
    box-shadow: none;
    border-color: #000; }

.col-form-label {
  color: #000;
  font-size: 13px; }

/* .btn, .form-control, .custom-select {
  height: 45px;
  border-radius: 0; } */

.custom-select {
  border: none;
  border-bottom: 1px solid #ccc;
  padding-left: 0;
  padding-right: 0;
  border-radius: 0; }
  .custom-select:active, .custom-select:focus {
    outline: none;
    -webkit-box-shadow: none;
    box-shadow: none;
    border-color: #000; }

/* .btn {
  border: none;
  border-radius: 0;
  font-size: 11px;
  letter-spacing: .2rem;
  text-transform: uppercase;
  border-radius: 30px !important; }
  .btn.btn-primary {
    border-radius: 30px;
    background: #ef4339;
    color: #fff;
    -webkit-box-shadow: 0 15px 30px 0 rgba(239, 67, 57, 0.2);
    box-shadow: 0 15px 30px 0 rgba(239, 67, 57, 0.2); }
  .btn:hover {
    color: #fff; }
  .btn:active, .btn:focus {
    outline: none;
    -webkit-box-shadow: none;
    box-shadow: none; } */

.contact-wrap {
  -webkit-box-shadow: 0 0px 20px 0 rgba(0, 0, 0, 0.05);
  box-shadow: 0 0px 20px 0 rgba(0, 0, 0, 0.05);
  border: 1px solid #efefef; }
  .contact-wrap .col-form-label {
    font-size: 14px;
    color: #b3b3b3;
    margin: 0 0 10px 0;
    display: inline-block;
    padding: 0; }
  .contact-wrap .form, .contact-wrap .contact-info {
    padding: 40px; }
  .contact-wrap .contact-info {
    color: rgba(255, 255, 255, 0.5); }
    .contact-wrap .contact-info ul li {
      margin-bottom: 15px;
      color: rgba(255, 255, 255, 0.5); }
      .contact-wrap .contact-info ul li .wrap-icon {
        font-size: 20px;
        color: #fff;
        margin-top: 5px; }
  .contact-wrap .form {
    background: #fff; }
    .contact-wrap .form h3 {
      color: #000;
      font-size: 2rem;
      font-weight: 700;
      margin-bottom: 30px; }
  .contact-wrap .contact-info {
    height: 100vh;
    background-size: cover;
    background-position: center center;
    background-repeat: no-repeat; }
    .contact-wrap .contact-info a {
      position: absolute;
      top: 0;
      bottom: 0;
      left: 0;
      right: 0; }
    @media (max-width: 1199.98px) {
      .contact-wrap .contact-info {
        height: 400px !important; } }
    .contact-wrap .contact-info h3 {
      color: #fff;
      font-size: 20px;
      margin-bottom: 30px; }

label.error {
  font-size: 12px;
  color: red; }

#message {
  resize: vertical; }

#form-message-warning, #form-message-success {
  display: none; }

#form-message-warning {
  color: #B90B0B; }

#form-message-success {
  color: #55A44E;
  font-size: 18px;
  font-weight: bold; }

.submitting {
  float: left;
  width: 100%;
  padding: 10px 0;
  display: none;
  font-weight: bold;
  font-size: 12px;
  color: #000; }


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



</style>
</head>
<body style="background: white;">
	<div class="container-xxl bg-white p-0">
		<jsp:include page="header.jsp"></jsp:include>
		<!-- Navbar End -->
<<<<<<< HEAD

<br>
		<!-- Header Start -->
  <div class="content">
    
    <div class="container">
      <div class="row align-items-stretch no-gutters contact-wrap">
        <div class="col-md-12">
          <div class="form h-100">
            <h3></h3>
            <form class="mb-5" method="post" id="contactForm" name="contactForm">
              <div class="row">
                <div class="col-md-6 form-group mb-3">
                  <label for="" class="col-form-label">&#127874; 나이 *</label>
                  <input type="number" class="form-control" name="age" id="age" placeholder="Your age">
                </div>
                <div class="col-md-6 form-group mb-3">
                  <label for="budget" class="col-form-label">&#128684; 흡연 *</label>
                  <select class="form-control" id="smoking" name="smoking" style="background: white; appearance: auto; margin-top: -2px;">
				    <option selected>Choose...</option>
				    <option value="yes">Yes</option>
				    <option value="no">No</option>
				  </select>
                </div>
              </div>
              <br>
			  <div class="row">
                <div class="col-md-6 form-group mb-3">
                  <label for="" class="col-form-label">&#128241; 핸드폰 번호 *</label>
                  <input type="text" class="form-control" name="phone" id="phone" placeholder="Your phone">
                </div>
                <div class="col-md-6 form-group mb-3">
                  <label for="" class="col-form-label">&#127866; 음주 *</label>
                  <input type="text" class="form-control" name="drinking" id="drinking"  placeholder="Your drink">
                </div>
              </div>
              <br>
              <div class="row">
                <div class="col-md-6 form-group mb-3">
                  <label for="" class="col-form-label">&#127968; 거주지역 *</label>
                  <input type="text" class="form-control" name="address" id="address" placeholder="Your age">
                </div>
                <div class="col-md-6 form-group mb-3">
                  <label for="" class="col-form-label">&#127970; 직업 *</label>
                  <input type="text" class="form-control" name="job" id="job"  placeholder="Your job">
                </div>
              </div>
              <br>
              <div class="row">
                <div class="col-md-6 form-group mb-3">
                  <label for="" class="col-form-label">&#128161; 관심사 *</label>
                  <input type="text" class="form-control" name="interest" id="interest" placeholder="Your interest">
                </div>
                <div class="col-md-6 form-group mb-3">
                  <label for="budget" class="col-form-label">&#128214; 학력 *</label>
                  <select class="form-control" id="school" name="school" style="background: white; appearance: auto; margin-top: -2px;">
				    <option selected>Choose...</option>
				    <option value="고졸">고등학교 졸업</option>
				    <option value="대졸">대학교 졸업</option>
				  </select>
                </div>
              </div>
              <br>
              <div class="row">
                <div class="col-md-6 form-group mb-3">
                  <label for="budget" class="col-form-label">MBTI *</label>
                  <select class="form-control" id="mbti" name="mbti" style="background: white; appearance: auto; margin-top: -2px;">
				    <option selected>Choose...</option>
				    <option value="INTJ">INTJ</option>
				    <option value="INTP">INTP</option>
				    <option value="INFJ">INFJ</option>
				    <option value="INFP">INFP</option>
				    <option value="ISTJ">ISTJ</option>
				    <option value="ISFJ">ISFJ</option>
				    <option value="ISTP">ISTP</option>
				    <option value="ISFP">ISFP</option>
				    <option value="ENTJ">ENTJ</option>
				    <option value="ENTP">ENTP</option>
				    <option value="ENFJ">ENFJ</option>
				    <option value="ENFP">ENFP</option>
				    <option value="ESTJ">ESTJ</option>
				    <option value="ESFJ">ESFJ</option>
				    <option value="ESTP">ESTP</option>
				    <option value="ESFP">ESFP</option>
				  </select>
                </div>
                <div class="col-md-6 form-group mb-3">
                  <label for="budget" class="col-form-label">&#128692; 운동 *</label>
                  <select class="form-control" id="budget" name="budget" style="background: white; appearance: auto; margin-top: -2px;">
				    <option selected>Choose...</option>
				    <option value="yes">Yes</option>
				    <option value="no">No</option>
				  </select>
                </div>
              </div>
              <br>
              <div class="row">
                <div class="col-md-6 form-group mb-3">
                  <label for="budget" class="col-form-label">등급 *</label>
                  <select class="form-control" id="budget" name="budget" style="background: white; appearance: auto; margin-top: -2px;">
				    <option selected>Choose...</option>
				    <option value="회원">회원</option>
				    <option value="관리자">관리자</option>
				  </select>
                </div>
              </div>
				<br>
              <div class="row">
                <div class="col-md-12 form-group mb-3">
                  <label for="message" class="col-form-label">&#128221; 자기소개 *</label>
                  <textarea class="form-control" name="aboutme" id="aboutme" cols="30" rows="6" style="resize: none;" placeholder="Introduce yourself"></textarea>
                </div>
              </div>
              <br>
              <br>
              <div class="row">
                <div class="col-md-12 form-group">
                  <input type="submit" value="confirm" class="btn btn-primary rounded-0 py-2 px-4" style="margin-left: 525px;">
                  <span class="submitting"></span>
                </div>
              </div>
            </form>

<!--             <div id="form-message-warning mt-4"></div> 
            <div id="form-message-success">
              Your message was sent, thank you!
            </div> -->

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
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.validate.min.js"></script>
	<script>
		function checkFormAndSubmit() {
			checkForm();

			var isSubmitEnabled = !document.getElementById("submitBtn").disabled;

			if (isSubmitEnabled) {
				// 이동할 페이지의 URL을 여기에 입력해줘
				window.location.href = "index";
			}
		}

		function checkForm() {
			var age = document.getElementById("age").value;
			var phone = document.getElementById("phone").value;
			var address = document.getElementById("address").value;
			var interest = document.getElementById("interest").value;
			var mbti = document.getElementById("mbti").value;
			var sport = document.getElementById("sport").options[document
					.getElementById("sport").selectedIndex].value;
			var smoking = document.getElementById("smoking").options[document
					.getElementById("smoking").selectedIndex].value;
			var drinking = document.getElementById("drinking").options[document
					.getElementById("drinking").selectedIndex].value;
			var job = document.getElementById("job").value;
			var school = document.getElementById("school").value;
			var role = document.getElementById("role").options[document
					.getElementById("role").selectedIndex].value;
			var aboutme = document.getElementById("aboutme").value;

			if (age && phone && address && interest && mbti && sport !== "none"
					&& smoking && drinking && job && school && role !== "none"
					&& aboutme) {
				document.getElementById("submitBtn").disabled = false;
			} else {
				document.getElementById("submitBtn").disabled = true;
			}
		}

		function uploadImage() {
			var input = document.getElementById('photo');
			var file = fileInput.files[0];

			//선택된 파일이 존재하면 FormData객체 생성 후, 해당 객체에 파일 추가
			if (file) {
				var formData = new FormData(document.getElementById('infoForm'));
				formData.append('file', file);
				// 여기에서 서버로 Ajax 요청을 보내거나, form.submit()을 사용하여 제출할 수 있습니다.
				// 예를 들어, jQuery를 사용하여 Ajax 요청을 보내는 경우
				$.ajax({
					type : "post",
					url : '${cpath}/info',
					data : formData,
					processData : false, // FormData를 직렬화
					contentType : false, //컨텐츠 타입 설정 X
					success : function(response) {
						// 성공 시 동작
						alert("Success");
					},
					error : function(error) {
						// 에러 시 동작
						alert("Error");
					}
				});
			} else {
				alert("Please select a file.")
			}
		}
	</script>
=======
		

		<!-- Header Start -->
		<br> <br>
		<div class="container">
			<div class="row">
				<div class="col-12 text-center">
					<h2>본인 정보 입력</h2>
					<br> <br> <br> <br>
					<div class="card">
						<div class="card-body mt-3" >
							<div class="row">
							<!-- 사진 업로드  -->
							<c:forEach items="${fileNames}" var="fileName" varStatus="i" >    
								<div class="responsive">
									<div class="gallery"> 
									<img src="${fileName}" alt="Admin" class="rounded" width="150">
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
							<form action="${cpath}/info" method="post"
								enctype="multipart/form-data">
								<div class="row">
									<!-- 왼쪽 세로 정렬 -->
									<div class="col-md-1"></div>
									<div class="col-md-5 info-front">

										<div class="mb-3 row">
											<label for="age" class="col-4 col-form-label">나이</label>
											<div class="col-8">
												<input type="number" id="age" name="age" required>
											</div>
										</div>

										<div class="mb-3 row">
											<label for="phone" class="col-7 col-form-label">핸드폰
												번호</label>
											<div class="col-8">
												<input type="number" id="phone" name="phone" required>
											</div>
										</div>

										<div class="mb-3 row">
											<label for="address" class="col-4 col-form-label">거주지역</label>
											<div class="col-8">
												<input type="text" id="address" name="address" required>
											</div>

										</div>
										<div class="mb-3 row">
											<label for="interest" class="col-4 col-form-label">관심사</label>
											<div class="col-8">
												<input type="text" id="interest" name="interest" required>
											</div>
										</div>

										<div class="mb-3 row">
											<label for="mbti" class="col-4 col-form-label">MBTI</label>
											<div class="col-8">
												<input type="text" id="mbti" name="mbti" required>
											</div>
										</div>

										<div class="mb-3 row">
											<label for="sport" class="col-4 col-form-label">운동</label>
											<div class="col-8">
												<select class="form-select" id="sport" name="sport" required>
													<option value="yes">예</option>
													<option value="no">아니오</option>
												</select>
											</div>
										</div>
									</div>


									<!-- 오른쪽 세로 정렬 -->
									<div class="col-md-5">
										<div class="mb-5 row">
											<label for="smoking" class="col-4 col-form-label">흡연</label>
											<div class="col-8">
												<select class="form-select" id="smoking" name="smoking"
													required>
													<option value="yes">예</option>
													<option value="no">아니오</option>
												</select>
											</div>
										</div>

										<div class="mb-3 row">
											<label for="drinking" class="col-4 col-form-label">음주</label>
											<div class="col-8">
												<select class="form-select" id="drinking" name="drinking"
													required>
													<option value="yes">예</option>
													<option value="no">아니오</option>
												</select>
											</div>
										</div>

										<div class="mb-3 row">
											<label for="job" class="col-4 col-form-label">직장</label>
											<div class="col-8">
												<input type="text" id="job" name="job" required>
											</div>
										</div>

										<div class="mb-3 row">
											<label for="school" class="col-4 col-form-label">학교</label>
											<div class="col-8">
												<input type="text" id="school" name="school" required>
											</div>
										</div>

										<div class="mb-3 row">
											<label for="role" class="col-4 col-form-label">등급</label>
											<div class="col-8">
												<select class="form-select" id="role" name="role" required>
													<option value="user">사용자</option>
													<option value="admin">관리자</option>
												</select>

											</div>
										</div>

									</div>
								</div>

								<br> <br>

								<div class="">
									<!-- 양쪽에 자기소개 배치 -->
									<div class="row">
										<div class="col-md-1"></div>
										<div class="col-md-10">
											<label for="aboutme" class="form-label">자기소개</label>
											<textarea class="form-control" id="aboutme" name="aboutme"
												rows="5" required></textarea>
										</div>
									</div>
									<br> <br>
									<div class="">
										<!-- 가입 버튼 오른쪽에 배치 -->
										<div class="text-center">
											<input type="submit" class="btn btn-primary" value="가입하기"
												onclick="checkFormAndSubmit()">
										</div>
									</div>
								</div>
							</form>
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

					<script>
						function checkFormAndSubmit() {
							checkForm();

							var isSubmitEnabled = !document
									.getElementById("submitBtn").disabled;

							if (isSubmitEnabled) {
								// 이동할 페이지의 URL을 여기에 입력해줘
								window.location.href = "index";
							}
						}
>>>>>>> origin/master

						function checkForm() {
							var age = document.getElementById("age").value;
							var phone = document.getElementById("phone").value;
							var address = document.getElementById("address").value;
							var interest = document.getElementById("interest").value;
							var mbti = document.getElementById("mbti").value;
							var sport = document.getElementById("sport").options[document
									.getElementById("sport").selectedIndex].value;
							var smoking = document.getElementById("smoking").options[document
									.getElementById("smoking").selectedIndex].value;
							var drinking = document.getElementById("drinking").options[document
									.getElementById("drinking").selectedIndex].value;
							var job = document.getElementById("job").value;
							var school = document.getElementById("school").value;
							var role = document.getElementById("role").options[document
									.getElementById("role").selectedIndex].value;
							var aboutme = document.getElementById("aboutme").value;

							if (age && phone && address && interest && mbti
									&& sport !== "none" && smoking && drinking
									&& job && school && role !== "none"
									&& aboutme) {
								document.getElementById("submitBtn").disabled = false;
							} else {
								document.getElementById("submitBtn").disabled = true;
							}
						}

						function uploadImage() {
							var input = document.getElementById('photo');
							var file = fileInput.files[0];

							//선택된 파일이 존재하면 FormData객체 생성 후, 해당 객체에 파일 추가
							if (file) {
								var formData = new FormData(document
										.getElementById('infoForm'));
								formData.append('file', file);
								// 여기에서 서버로 Ajax 요청을 보내거나, form.submit()을 사용하여 제출할 수 있습니다.
								// 예를 들어, jQuery를 사용하여 Ajax 요청을 보내는 경우
								$.ajax({
									type : "post",
									url : '${cpath}/info',
									data : formData,
									processData : false, // FormData를 직렬화
									contentType : false, //컨텐츠 타입 설정 X
									success : function(response) {
										// 성공 시 동작
										alert("Success");
									},
									error : function(error) {
										// 에러 시 동작
										alert("Error");
									}
								});
							} else {
								alert("Please select a file.")
							}
						}
					</script>
</body>
</html>