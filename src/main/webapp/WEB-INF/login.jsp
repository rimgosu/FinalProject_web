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
    
</head>
<body>
    <div class="container-xxl bg-white p-0">
        <jsp:include page="header.jsp"></jsp:include>
        <!-- Navbar End -->


        <!-- Header Start -->
        <div class="container-fluid mb-5 wow fadeIn" data-wow-delay="0.1s" style="padding: 35px;">
            <div class="container">
                <div class="row g-2">
                    <div class="col-md-4 mx-auto text-center mb-3">
                        <h2 class="text-black">Login</h2>
                        
                        <form action="${cpath}/login" method="post" >
                        	<table>
                        		<tr>
                        			<td class="form-group" style="width:400px;"> 
                        			 	<input type="email" class="form-control border-0 py-3" placeholder="Username" id="username" name="username">
                        			 </td>
                        		</tr>
                        		<br>
                        		<tr>
                        			<td class="form-group"> 
                        				 <input type="password" class="form-control border-0 py-3" placeholder="Password" id="password" name="password">
                        			 </td>
                        		</tr>
                        	</table>
                        	<br>
						    <input type="submit" class="btn btn-dark border-0 w-100 py-3" value="Login">
						</form>
                                                
                       <!--  <div class="form-group">
                            <input type="text" class="form-control border-0 py-3" placeholder="Username" id="username">
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control border-0 py-3" placeholder="Password" id="password">
                        </div>
                        <button type ="submit" class="btn btn-dark border-0 w-100 py-3" >Login</button> -->
                        
                        <br>
                        <br>
                        <p>회원이 아니신가요? <a href="join" style="
                            margin-left: 5px;">회원가입</a></p>

                    	</form>
                    </div>
                </div>
            </div>
        </div>
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