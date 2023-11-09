<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Inter:wght@700;800&display=swap" rel="stylesheet">
    
    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="lib/animate/animate.min.css" rel="stylesheet">
    <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="css/style.css" rel="stylesheet">
    <style>
        .joinfont{
            font-weight: 1000;
            color: var(--dark);
        }

        table {
            margin-left: 500px; 
            border-collapse: separate;
            border-spacing: 0 40px;
            }

        input {
            margin-left: 20px;
            border: none; /* 상단, 좌우, 우측 테두리 제거 */
            border-bottom: 1px solid pink; /* 1px 두께의 검은색 밑줄 스타일 적용 */
            outline: none; /* 포커스 스타일 제거 */
        }
    </style>
</head>
<body>
    <div class="container-xxl bg-white p-0">
        <!-- Spinner Start -->
        <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
            <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
                <span class="sr-only">Loading...</span>
            </div>
        </div>
        <!-- Spinner End -->


        <!-- Navbar Start -->
        <div class="container-fluid nav-bar bg-transparent">
            <nav class="navbar navbar-expand-lg bg-white navbar-light py-0 px-4">
                <a href="index" class="navbar-brand d-flex align-items-center text-center">
                    <div class="icon p-2 me-2">
                        <img class="img-fluid" src="img/icon-deal.png" alt="Icon" style="width: 30px; height: 30px;">
                    </div>
                    <h1 class="m-0 marko">SIM KOONG</h1>
                </a>
                <button type="button" class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarCollapse">
                    <div class="navbar-nav ms-auto">
                        <a href="index" class="nav-item nav-link">Home</a>
                        <a href="recommend" class="nav-item nav-link">RECOMMEND</a>
                        <div class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Property</a>
                            <div class="dropdown-menu rounded-0 m-0">
                                <a href="property-list.html" class="dropdown-item">Property List</a>
                                <a href="property-type.html" class="dropdown-item">Property Type</a>
                                <a href="like" class="dropdown-item">LIKE</a>
                            </div>
                        </div>
                        <!-- <div class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Pages</a>
                            <div class="dropdown-menu rounded-0 m-0">
                                <a href="testimonial.html" class="dropdown-item">Testimonial</a>
                                <a href="404.html" class="dropdown-item">404 Error</a>
                            </div>
                        </div> -->
                        <a href="chat" class="nav-item nav-link">CHAT</a>
                    </div>
                    <a href="login" class="btn btn-primary px-3 d-none d-lg-flex">LOGIN</a>
                </div>
            </nav>
        </div>
        <!-- Navbar End -->


        <!-- Header Start -->
        <br>
        <br>
        <div class="container">
            <div class="row">
                <div class="col-12 text-center">
                    <h2>회원가입</h2>
                </div>
            </div>
            <br>
            <br>
            <form>
                <table>
                    <tr>
                        <td class="joinfont"><i class="fa-regular fa-user"></i>  닉네임</td>
                        <td><input type="text" name="nickname" required></td>
                    </tr>
                    <tr>
                        <td class="joinfont"><i class="fa-solid fa-id-badge fa-lg"></i>   아이디</td>
                        <td><input type="text" name="username" required></td>
                    </tr>
                    <tr>
                        <td class="joinfont"><i class="fa-regular fa-envelope"></i>   이메일</td>
                        <td><input type="email" name="email" required></td>
                    </tr>
                    <tr>
                        <td class="joinfont"><i class="fa-solid fa-key"></i>   비밀번호</td>
                        <td><input type="password" name="password" required></td>
                    </tr>
                    <tr>
                        <td class="joinfont"><i class="fa-solid fa-key"></i>   비밀번호 확인</td>
                        <td><input type="password" name="confirm_password" required></td>
                    </tr>
                    
                </table>
                <br>
                <br>
                <div class="row">
                    <div class="col-12 text-center">
                        <button style="width: 300px;" class="btn btn-primary" type="button" onclick="location.href='info'">가입하기</button>
                    </div>
                </div>
            </form>
        </div>
        <br>
        <br>
        <br>
        <!-- Header End -->


        <!-- Back to Top -->
        <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
    </div>
    
    <!-- JavaScript Libraries -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="lib/wow/wow.min.js"></script>
    <script src="lib/easing/easing.min.js"></script>
    <script src="lib/waypoints/waypoints.min.js"></script>
    <script src="lib/owlcarousel/owl.carousel.min.js"></script>
    <script src="https://kit.fontawesome.com/60641a2f0e.js" crossorigin="anonymous"></script>

    <!-- icons-->
    <!-- Template Javascript -->
    <script src="js/main.js"></script>
    
    <script>
	    function submitForm() {
	        // 입력된 값 가져오기
	        var nickname = $("input[name='nickname']").val();
	        var username = $("input[name='username']").val();
	        var password = $("input[name='password']").val();
	        var confirm_password = $("input[name='confirm_password']").val();
	
	        // 필수 입력 필드 확인
	        if (nickname === "" || username === "" || password === "" || confirm_password === "") {
	            alert("모든 필수 입력 필드를 작성해주세요.");
	            return false; // 폼 전송 취소
	        } else {
	            // 비밀번호 일치 여부 확인
	            if (password !== confirm_password) {
	                alert("비밀번호가 일치하지 않습니다.");
	                return false; // 폼 전송 취소
	            } else {
	                // 모든 조건이 충족되면 true를 반환하여 폼이 제출될 수 있도록 함
	                return true;
	            }
	        }
	    }
</script>

</body>
</html>