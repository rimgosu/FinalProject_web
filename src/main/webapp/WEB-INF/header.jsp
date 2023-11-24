<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="cpath" value="${pageContext.request.contextPath}" />
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
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
                    <div class="p-2 me-2">
                        <img class="img-fluid" src="img/logo.png" alt="Icon" style="width: 30px; height: 30px;">
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
                       <!--  <div class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Property</a>
                            <div class="dropdown-menu rounded-0 m-0">
                                <a href="info" class="dropdown-item">내 정보 입력</a>

                                <a href="like" class="dropdown-item">LIKE</a>
                            </div> -->
                        </div>
                        <!-- <div class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Pages</a>
                            <div class="dropdown-menu rounded-0 m-0">
                                <a href="testimonial.html" class="dropdown-item">Testimonial</a>
                                <a href="404.html" class="dropdown-item">404 Error</a>
                            </div>
                        </div> -->
                        
                    </div>
                    <c:if test="${empty mvo}">
                  <form class="form-inline" action="${cpath}/login" method="post">
                          <a href="login" class="btn btn-primary px-3 d-none d-lg-flex">LOGIN</a>
                       </form>
                    </c:if>
                    <c:if test="${not empty mvo}">
                     <div class="form-group">
                        <label></label>
                     </div>
                  <!--  <a href="chat" class="nav-item nav-link">CHAT</a> -->  
                     <a href="showChatRoom" class="nav-item nav-link">CHATROOM</a>
                  <form class="form-iniline" action="${cpath}/profile" method="get">
                     <button type="submit" class="btn btn-prmary px-3 d-none d-lg-flex" style="color: var(--dark); font-weight: 500;">PROFILE</button>
                  </form>
       <%--            <form class="form-inline" action="${cpath}/logout" method="get">
                     <button type="submit" class="btn btn-default" style="color: var(--dark); font-weight: 500;">LOGOUT</button> --%>
                  </form>
               </c:if>   
                </div>
            </nav>
         </div>

</body>
</html>