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
					<h2>본인 정보 입력</h2>
					<br> <br> <br> <br>
					<form action="${cpath}/fileUpload" method="post" enctype="multipart/form-data" id="imageForm1">
						<label for="uploadInput" id="uploadButton">이미지 선택</label> <input
							type="file" id="photo" name="file" accept="image/*">
						<button type="submit" onclick="uploadImage()" id="uploadButton">업로드</button>
					</form>
				</div>
			</div>
			<br> <br> <br>
			<form action="${cpath}/info" method="post" enctype="multipart/form-data">
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
							<label for="phone" class="col-7 col-form-label">핸드폰 번호</label>
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
								<select class="form-select" id="smoking" name="smoking" required>
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




</body>
</html>