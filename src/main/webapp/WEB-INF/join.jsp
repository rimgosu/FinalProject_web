<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix ="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
        <jsp:include page="header.jsp"></jsp:include>
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
            <form action="${cpath}/join" method="post" onsubmit="return submitForm()">
                <table>
                    <tr>
                        <td class="joinfont"><i class="fa-regular fa-user"></i>  닉네임</td>
                        <td><input type="text" name="nickname" required></td>
                    </tr>
                    <tr>
                        <td class="joinfont"><i class="fa-regular fa-envelope"></i> 이메일</td>
                        <td><input type="email" name="username" required></td>
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
                     <input type="submit" style="width: 300px;" class="btn btn-primary" value="가입하기">
                       <!-- <button style="width: 300px;" class="btn btn-primary" type="submit" onclick="location.href='${cpath}/info'">가입하기</button>   -->
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