<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix ="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
    
    <style type="text/css">
    
        .flip {
            position: relative;
            perspective: 1100px;
        }

        .usercard {
            position: relative;
            transform-style: preserve-3d;
            transition: transform 1.5s; /* 변경: 트랜지션 속성 순서 변경 */
        }

        .front, .back {
            position: absolute;
            backface-visibility: hidden;
            justify-content: center;
            align-items: center;
        }

        .back {
            transform: rotateY(180deg);
        }

        .flip.clicked .usercard { /* 변경: hover 제거, clicked 클래스만 사용 */
            transform: rotateY(180deg);
        }
    
    
    
    </style>
</head>

<body style="background: white;">
    <div class="container-xxl bg-white p-0">
        <jsp:include page="header.jsp"></jsp:include>
        <!-- Navbar End -->


        <!-- Header Start -->
        <!-- <div class="container-fluid header bg-white p-0">
            <div class="row g-0 align-items-center flex-column-reverse flex-md-row">
                <div class="col-md-6 p-5 mt-lg-5">
                    <h1 class="display-5 animated fadeIn mb-4">About Us</h1> 
                        <nav aria-label="breadcrumb animated fadeIn">
                        <ol class="breadcrumb text-uppercase">
                            <li class="breadcrumb-item"><a href="#">Home</a></li>
                            <li class="breadcrumb-item"><a href="#">Pages</a></li>
                            <li class="breadcrumb-item text-body active" aria-current="page">About</li>
                        </ol>
                    </nav>
                </div>
                <div class="col-md-6 animated fadeIn">
                    <img class="img-fluid" src="img/header.jpg" alt="">
                </div>
            </div>
        </div> -->
        <!-- Header End -->


        <!-- Search Start -->
        <!-- <div class="container-fluid bg-primary mb-5 wow fadeIn" data-wow-delay="0.1s" style="padding: 35px;">
            <div class="container">
                <div class="row g-2">
                    <div class="col-md-10">
                        <div class="row g-2">
                            <div class="col-md-4">
                                <input type="text" class="form-control border-0 py-3" placeholder="Search Keyword">
                            </div>
                            <div class="col-md-4">
                                <select class="form-select border-0 py-3">
                                    <option selected>Property Type</option>
                                    <option value="1">Property Type 1</option>
                                    <option value="2">Property Type 2</option>
                                    <option value="3">Property Type 3</option>
                                </select>
                            </div>
                            <div class="col-md-4">
                                <select class="form-select border-0 py-3">
                                    <option selected>Location</option>
                                    <option value="1">Location 1</option>
                                    <option value="2">Location 2</option>
                                    <option value="3">Location 3</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <button class="btn btn-dark border-0 w-100 py-3">Search</button>
                    </div>
                </div>
            </div>
        </div> -->
        <!-- Search End -->


        <!-- About Start -->
        <!-- <div class="container-xxl py-5">
            <div class="container">
                <div class="row g-5 align-items-center">
                    <div class="col-lg-6 wow fadeIn" data-wow-delay="0.1s">
                        <div class="about-img position-relative overflow-hidden p-5 pe-0">
                            <img class="img-fluid w-100" src="img/about.jpg">
                        </div>
                    </div>
                    <div class="col-lg-6 wow fadeIn" data-wow-delay="0.5s">
                        <h1 class="mb-4">#1 Place To Find The Perfect Property</h1>
                        <p class="mb-4">Tempor erat elitr rebum at clita. Diam dolor diam ipsum sit. Aliqu diam amet diam et eos. Clita erat ipsum et lorem et sit, sed stet lorem sit clita duo justo magna dolore erat amet</p>
                        <p><i class="fa fa-check text-primary me-3"></i>Tempor erat elitr rebum at clita</p>
                        <p><i class="fa fa-check text-primary me-3"></i>Aliqu diam amet diam et eos</p>
                        <p><i class="fa fa-check text-primary me-3"></i>Clita duo justo magna dolore erat amet</p>
                        <a class="btn btn-primary py-3 px-5 mt-3" href="">Read More</a>
                    </div>
                </div>
            </div>
        </div> -->
        <!-- About End -->


        <!-- Call to Action Start -->
        <!-- <div class="container-xxl py-5">
            <div class="container">
                <div class="bg-light rounded p-3">
                    <div class="bg-white rounded p-4" style="border: 1px dashed rgba(0, 185, 142, .3)">
                        <div class="row g-5 align-items-center">
                            <div class="col-lg-6 wow fadeIn" data-wow-delay="0.1s">
                                <img class="img-fluid rounded w-100" src="img/call-to-action.jpg" alt="">
                            </div>
                            <div class="col-lg-6 wow fadeIn" data-wow-delay="0.5s">
                                <div class="mb-4">
                                    <h1 class="mb-3">Contact With Our Certified Agent</h1>
                                    <p>Eirmod sed ipsum dolor sit rebum magna erat. Tempor lorem kasd vero ipsum sit sit diam justo sed vero dolor duo.</p>
                                </div>
                                <a href="" class="btn btn-primary py-3 px-4 me-2"><i class="fa fa-phone-alt me-2"></i>Make A Call</a>
                                <a href="" class="btn btn-dark py-3 px-4"><i class="fa fa-calendar-alt me-2"></i>Get Appoinment</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div> -->
        <!-- Call to Action End -->


        <!-- Team Start -->
        <div class="container-xxl py-5">
            <div class="container">
                <div class="text-center mx-auto mb-5 wow fadeInUp" data-wow-delay="0.1s" style="max-width: 600px;">
                    <h1 class="mb-3">Choose Like</h1>
                    <p></p>
                </div>
                <div class="row g-4">
                    <div class="col-lg-3 col-md-6 wow fadeInUp" data-wow-delay="0.1s">
                    <div class="flip">
                    <div class="usercard">
                        <div class="team-item rounded overflow-hidden front">
                            <div class="position-relative">
                                <img class="img-fluid" src="img/team-1.jpg" alt="">
                                <div class="position-absolute start-50 top-100 translate-middle d-flex align-items-center">
                                    <button class="btn btn-square mx-1 like-btn" data-count="0"><i class="far fa-heart"></i></button>
                                    <button class="btn btn-square mx-1 dislike-btn" data-count="0"><img src="img/before broken.png" style="width: 18px;"></button>
                                    <a class="btn btn-square mx-1" onclick="flipCard()"><img src="img/repeat.png" style="width: 18px;"></a>
                                </div>
                            </div>
                            <div class="text-center p-4 mt-3">
                                <h5 class="fw-bold mb-0">${mvo.nickname}</h5>
                                <p>${mvo.age}</p>
                                <small>${mvo.aboutme}</small>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-6 back" style="background: mintcream; width: 216px; height: 392px; border-radius: 5px;">
	                            <div>
	                            <a class="btn btn-square mx-1" onclick="flipCard()"><img src="img/repeat.png" style="width: 18px;"></a>
	                            </div>
	                            <p><strong>Address:</strong> ${mvo.address}</p>
					            <p><strong>Interest:</strong> ${mvo.interest}</p>
					            <p><strong>MBTI:</strong> ${mvo.mbti}</p>
					            <p><strong>Sport:</strong> ${mvo.sport}</p>
					            <p><strong>Smoke:</strong> ${mvo.smoking}</p>
					            <p><strong>Drink:</strong> ${mvo.drinking}</p>
					            <p><strong>Job:</strong> ${mvo.job}</p>
					            <p><strong>Education:</strong> ${mvo.school}</p>
                        </div>
                      </div>
                   </div>
                </div>
                    <div class="col-lg-3 col-md-6 wow fadeInUp" data-wow-delay="0.3s">
                    	<div class="flip">
                    	<div class="usercard">
                        <div class="team-item rounded overflow-hidden front">
                            <div class="position-relative">
                                <img class="img-fluid" src="img/team-2.jpg" alt="">
                                <div class="position-absolute start-50 top-100 translate-middle d-flex align-items-center">
                                    <button class="btn btn-square mx-1 like-btn" data-count="0"><i class="far fa-heart"></i></button>
                                    <button class="btn btn-square mx-1 dislike-btn" data-count="0"><img src="img/before broken.png" style="width: 18px;"></button>
                                    <a class="btn btn-square mx-1" onclick="flipCard()"><img src="img/repeat.png" style="width: 18px;"></a>
                                </div>
                            </div>
                            <div class="text-center p-4 mt-3">
                                <h5 class="fw-bold mb-0">${mvo.nickname}</h5>
                                <p>${mvo.age}</p>
                                <small>${mvo.aboutme}</small>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-6 back" style="background: turquoise; width: 216px; height: 392px; border-radius: 5px;">
	                            <p><strong>Address:</strong> ${mvo.address}</p>
					            <p><strong>Interest:</strong> ${mvo.interest}</p>
					            <p><strong>MBTI:</strong> ${mvo.mbti}</p>
					            <p><strong>Sport:</strong> ${mvo.sport}</p>
					            <p><strong>Smoke:</strong> ${mvo.smoking}</p>
					            <p><strong>Drink:</strong> ${mvo.drinking}</p>
					            <p><strong>Job:</strong> ${mvo.job}</p>
					            <p><strong>Education:</strong> ${mvo.school}</p>
	                            <a class="btn btn-square mx-1" onclick="flipCard()"><img src="img/repeat.png" style="width: 18px;"></a>
                        </div>
                    </div>
                 </div>
              </div>
                    <div class="col-lg-3 col-md-6 wow fadeInUp" data-wow-delay="0.5s">
                        <div class="team-item rounded overflow-hidden">
                            <div class="position-relative">
                                <img class="img-fluid" src="img/team-3.jpg" alt="">
                                <div class="position-absolute start-50 top-100 translate-middle d-flex align-items-center">
                                    <button class="btn btn-square mx-1 like-btn" data-count="0"><i class="far fa-heart"></i></button>
                                    <button class="btn btn-square mx-1 dislike-btn" data-count="0"><img src="img/before broken.png" style="width: 18px;"></button>
                                    <a class="btn btn-square mx-1" href=""><img src="img/repeat.png" style="width: 18px;"></a>
                                </div>
                            </div>
                            <div class="text-center p-4 mt-3">
                                <h5 class="fw-bold mb-0">${mvo.nickname}</h5>
                                <p>${mvo.age}</p>
                                <small>${mvo.aboutme}</small>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6 wow fadeInUp" data-wow-delay="0.7s">
                        <div class="team-item rounded overflow-hidden">
                            <div class="position-relative">
                                <img class="img-fluid" src="img/team-4.jpg" alt="">
                                <div class="position-absolute start-50 top-100 translate-middle d-flex align-items-center">
                                    <button class="btn btn-square mx-1 like-btn" data-count="0"><i class="far fa-heart"></i></button>
                                    <button class="btn btn-square mx-1 dislike-btn" data-count="0"><img src="img/before broken.png" style="width: 18px;"></button>
                                    <a class="btn btn-square mx-1" href=""><img src="img/repeat.png" style="width: 18px;"></a>
                                </div>
                            </div>
                            <div class="text-center p-4 mt-3">
                                <h5 class="fw-bold mb-0">${mvo.nickname}</h5>
                                <p>${mvo.age}</p>
                                <small>${mvo.aboutme}</small>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Team End -->
        

        <!-- Footer Start -->
        <div class="container-fluid bg-dark text-white-50 footer pt-5 mt-5 wow fadeIn" data-wow-delay="0.1s">
            <div class="container py-5">
                <div class="row g-5">
                    <div class="col-lg-3 col-md-6">
                        <h5 class="text-white mb-4">Get In Touch</h5>
                        <p class="mb-2"><i class="fa fa-map-marker-alt me-3"></i>123 Street, New York, USA</p>
                        <p class="mb-2"><i class="fa fa-phone-alt me-3"></i>+012 345 67890</p>
                        <p class="mb-2"><i class="fa fa-envelope me-3"></i>info@example.com</p>
                        <div class="d-flex pt-2">
                            <a class="btn btn-outline-light btn-social" href=""><i class="fab fa-twitter"></i></a>
                            <a class="btn btn-outline-light btn-social" href=""><i class="fab fa-facebook-f"></i></a>
                            <a class="btn btn-outline-light btn-social" href=""><i class="fab fa-youtube"></i></a>
                            <a class="btn btn-outline-light btn-social" href=""><i class="fab fa-linkedin-in"></i></a>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <h5 class="text-white mb-4">Quick Links</h5>
                        <a class="btn btn-link text-white-50" href="">About Us</a>
                        <a class="btn btn-link text-white-50" href="">Contact Us</a>
                        <a class="btn btn-link text-white-50" href="">Our Services</a>
                        <a class="btn btn-link text-white-50" href="">Privacy Policy</a>
                        <a class="btn btn-link text-white-50" href="">Terms & Condition</a>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <h5 class="text-white mb-4">Photo Gallery</h5>
                        <div class="row g-2 pt-2">
                            <div class="col-4">
                                <img class="img-fluid rounded bg-light p-1" src="img/property-1.jpg" alt="">
                            </div>
                            <div class="col-4">
                                <img class="img-fluid rounded bg-light p-1" src="img/property-2.jpg" alt="">
                            </div>
                            <div class="col-4">
                                <img class="img-fluid rounded bg-light p-1" src="img/property-3.jpg" alt="">
                            </div>
                            <div class="col-4">
                                <img class="img-fluid rounded bg-light p-1" src="img/property-4.jpg" alt="">
                            </div>
                            <div class="col-4">
                                <img class="img-fluid rounded bg-light p-1" src="img/property-5.jpg" alt="">
                            </div>
                            <div class="col-4">
                                <img class="img-fluid rounded bg-light p-1" src="img/property-6.jpg" alt="">
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <h5 class="text-white mb-4">Newsletter</h5>
                        <p>Dolor amet sit justo amet elitr clita ipsum elitr est.</p>
                        <div class="position-relative mx-auto" style="max-width: 400px;">
                            <input class="form-control bg-transparent w-100 py-3 ps-4 pe-5" type="text" placeholder="Your email">
                            <button type="button" class="btn btn-primary py-2 position-absolute top-0 end-0 mt-2 me-2">SignUp</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="copyright">
                    <div class="row">
                        <div class="col-md-6 text-center text-md-start mb-3 mb-md-0">
                            &copy; <a class="border-bottom" href="#">Your Site Name</a>, All Right Reserved. 
							
							<!--/*** This template is free as long as you keep the footer author’s credit link/attribution link/backlink. If you'd like to use the template without the footer author’s credit link/attribution link/backlink, you can purchase the Credit Removal License from "https://htmlcodex.com/credit-removal". Thank you for your support. ***/-->
							Designed By <a class="border-bottom" href="https://htmlcodex.com">HTML Codex</a>
                        </div>
                        <div class="col-md-6 text-center text-md-end">
                            <div class="footer-menu">
                                <a href="">Home</a>
                                <a href="">Cookies</a>
                                <a href="">Help</a>
                                <a href="">FQAs</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Footer End -->


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
    
<script>
	function flipCard() {
	    var flipElement = document.querySelector('.flip');
	    flipElement.classList.toggle('clicked');
	}
    // Initialize Wow.js
    new WOW().init();

    // jQuery Document Ready
    $(document).ready(function () {
        // Like button click event
        $('.like-btn').click(function () {
            var count = parseInt($(this).attr('data-count'));
            
            // Toggle the liked class and change the heart color
            if ($(this).hasClass('liked')) {
                count--;
                $(this).removeClass('liked');
                $(this).find('i').removeClass('fas').addClass('far'); // Change to outline heart
                alert("이 유저를 더 이상 선호하지 않습니다." + '\nLike: ' + count);
            } else {
                count++;
                $(this).addClass('liked');
                $(this).find('i').removeClass('far').addClass('fas'); // Change to filled heart
                alert("이 유저를 선호합니다." + '\nLike: ' + count);
            }

            $(this).attr('data-count', count);
            
        });
        
        $('.dislike-btn').click(function () {
            var count = parseInt($(this).attr('data-count'));

            // Toggle the liked class and change the heart color
            if ($(this).hasClass('disliked')) {
                count--;
                $(this).removeClass('disliked');
                $(this).find('img').attr('src', 'img/before broken.png'); // Change to original image
                alert("이 유저를 다시 추천받습니다." + '\ndisLike: ' + count);
            } else {
                count++;
                $(this).addClass('disliked');
                $(this).find('img').attr('src', 'img/broken-heart.png'); // Change to broken heart image
                alert("이 유저를 더 이상 추천받지 않습니다." + '\ndisLike: ' + count);
            }

            $(this).attr('data-count', count);
            
        });
    });
    
</script>
    
</body>

</html>