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
        .info-front{
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
                    <h1 class="m-0 text-primary">SIM KOONG</h1>
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
                    <h2>본인 정보 입력</h2>
                </div>
            </div>
            <br>
            <br>
            <br>
            <div class="row">
                <!-- 왼쪽 세로 정렬 -->
                <div class="col-md-3"></div>
                <div class="col-md-3 info-front">
                    <div class="mb-3 row">
                        <label for="age" class="col-4 col-form-label">나이</label>
                        <div class="col-8">
                            <input type="number" id="age" name="age" required>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="location" class="col-4 col-form-label">거주지역</label>
                        <div class="col-8">
                            <input type="text" id="location" name="location" required>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="interests" class="col-4 col-form-label">관심사</label>
                        <div class="col-8">
                            <input type="text" id="interests" name="interests" required>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="exercise" class="col-4 col-form-label">운동</label>
                        <div class="col-8">
                            <select class="form-select" id="exercise" name="exercise" required>
                                <option value="yes">예</option>
                                <option value="no">아니오</option>
                            </select>
                        </div>
                    </div>
                </div>
                <!-- 오른쪽 세로 정렬 -->
                <div class="col-md-3">
                    <div class="mb-3 row">
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
                            <select class="form-select" id="drinking" name="drinking" required>
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
                </div>
            </div>
            <br>
            <br>
            <div class="">
                <!-- 양쪽에 자기소개 배치 -->
                <div class="row">
                    <div class="col-md-3"></div>
                    <div class="col-md-6">
                        <label for="introduction" class="form-label">자기소개</label>
                        <textarea class="form-control" id="introduction" name="introduction" rows="5" required></textarea>
                    </div>
                </div>
                <br>
                <br>
                <div class="">
                    <!-- 가입 버튼 오른쪽에 배치 -->
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary">가입하기</button>
                    </div>
                </div>
            </div>
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

    <!-- Template Javascript -->
    <script src="js/main.js"></script>
</body>
</html>