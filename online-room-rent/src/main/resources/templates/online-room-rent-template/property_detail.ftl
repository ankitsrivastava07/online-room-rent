<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title></title>
  <link href="/online-room-rent/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <link href="/online-room-rent/assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="/online-room-rent/assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
  <link href="/online-room-rent/assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="/online-room-rent/assets/css/style.css" rel="stylesheet">
  <style>
#btn {
    padding: 0 16px;
    height: 32px;
    line-height: 32px;
    margin-top:4% ;
}
  </style>
</head>
<body>

 <!-- ======= Header ======= -->
 <header id="header" class="d-flex align-items-center">
  <div class="container d-flex align-items-center">

    <div class="logo me-auto">
      <h1><a href="index.html">Mamba</a></h1>
      <!-- Uncomment below if you prefer to use an image logo -->
      <!-- <a href="dashboard.ftl"><img src="online-room-rent/assets/img/logo.png" alt="" class="img-fluid"></a>-->
    </div>

    <nav id="navbar" class="navbar">
      <ul>
        <li><a class="nav-link scrollto active" href="#hero">Home</a></li>
        <li><a class="nav-link scrollto" href="#about">About</a></li>
        <li><a class="nav-link scrollto" href="#services">Services</a></li>
        <li><a class="nav-link scrollto" href="#portfolio">Portfolio</a></li>
        <li><a class="nav-link scrollto" href="#team">Team</a></li>
        <li class="dropdown"><a href="#"><span>Drop Down</span> <i class="bi bi-chevron-down"></i></a>
          <ul>
            <li><a href="#">Drop Down 1</a></li>
            <li class="dropdown"><a href="#"><span>Deep Drop Down</span> <i class="bi bi-chevron-right"></i></a>
              <ul>
                <li><a href="#">Deep Drop Down 1</a></li>
                <li><a href="#">Deep Drop Down 2</a></li>
                <li><a href="#">Deep Drop Down 3</a></li>
                <li><a href="#">Deep Drop Down 4</a></li>
                <li><a href="#">Deep Drop Down 5</a></li>
              </ul>
            </li>
            <li><a href="#">Drop Down 2</a></li>
            <li><a href="#">Drop Down 3</a></li>
            <li><a href="#">Drop Down 4</a></li>
          </ul>
        </li>
        <li><a class="nav-link scrollto" href="/property-poster/register">Property Post Here</a></li>
      </ul>
      <i class="bi bi-list mobile-nav-toggle"></i>
    </nav><!-- .navbar -->
  </div>
</header><!-- End Header -->
<!--start searchbar-->
<div class="container py-5">
<div class="row">
<h1>Welcome back! Let???s continue your search
</h1>
</div>
</div>

<section class="search-sec">
    <div class="container">
        <form action="#" method="post" novalidate="novalidate">
            <div class="row">
                <div class="col-lg-12">
                    <div class="row">
					 <div class="col-lg-3 col-md-3 col-sm-12 p-0">
                            <select class="form-control search-slt">
                                <option>Choose Rating</option>
                            4 stars and up -->
                                <option>
                                  <a><p>&#9733; &#9733; &#9733; &#9733; &#x2606;</p></a href="#">
                                </option>
                             3 stars and up -->
                                <option>
                                    <a><p>&#9733; &#9733; &#9733; &#x2606; &#x2606;</p></a href="#">
                                </option>

                                <option>
                                    <a><p>&#9733; &#9733; &#x2606; &#x2606; &#x2606;</p></a href="#">
                                </option>
                              1 star and up -->
                                <option>
                                    <a><p>&#9733; &#x2606; &#x2606; &#x2606; &#x2606;</p></a href="#">
                                </option>
                            </select>
                        </div>

						 <div class="col-lg-3 col-md-3 col-sm-12 p-0">
                            <input type="text" class="form-control search-slt" placeholder="Enter Search">
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-12 p-0">
                            <select class="form-control search-slt">
                                <option>Choose Rating</option>
                             4 stars and up -->
                                <option>
                                  <a><p>&#9733; &#9733; &#9733; &#9733; &#x2606;</p></a href="#">
                                </option>
                            3 stars and up -->
                                <option>
                                    <a><p>&#9733; &#9733; &#9733; &#x2606; &#x2606;</p></a href="#">
                                </option>
                              2 stars and up -->
                                <option>
                                    <a><p>&#9733; &#9733; &#x2606; &#x2606; &#x2606;</p></a href="#">
                                </option>
                               1 star and up -->
                                <option>
                                    <a><p>&#9733; &#x2606; &#x2606; &#x2606; &#x2606;</p></a href="#">
                                </option>
                            </select>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-12 p-0">
                            <button type="button" class="btn btn-primary wrn-btn">Search</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</section>
  <main id="main">
    </div>
    </div>
    <div class="container">
 <div class="row">
</div>
</div>
</div>
</div>
<div class="container">
	<div class="row">
<h2>Popular Properties</h2>
</div>
</div>
    <!-- Topic Cards -->
    <div id="cards_landscape_wrap-2" >
    <div class="container-fluid">
        <div class="row">
        <div id="cards_landscape_wrap-2">
        <div class="container-fluid">
            <div class="row">
                <#list dtos as dto>
                <div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
                    <a href="/propertyDetail/${dto.slugName}-For-${dto.address.slugName}?propertyId=${dto.id}">
                        <div class="card-flyer" style="width: 18rem;">
                            <div class="text-box">
                            <div class="image-box">
                                <img src="${dto.images[0].bucketUrl}" alt="" />
                            </div>
                            <div class="text-container">
                                <span class="text-dark">
                                  ${dto.title}
                                </span>
                                <p class="rupees">???24,000 | 720 SQT</p>
                                <p>${dto.address.address}, ${dto.address.state}
                              <span id="btn" class="btn btn-primary" font-size=":10px;" style="border-radius: 65px">Read More</span>
                            </div>
                        </div>
                    </div>
                    </a>
                </div>
               </#list>
  </main><!-- End #main -->
  <!-- ======= Footer ======= -->
  <footer id="footer">
    <div class="footer-top">
      <div class="container">
        <div class="row">
          <div class="col-lg-3 col-md-6 footer-info">
            <h3>Mamba</h3>
            <p>
              A108 Adam Street <br>
              NY 535022, USA<br><br>
              <strong>Phone:</strong> +1 5589 55488 55<br>
              <strong>Email:</strong> info@example.com<br>
            </p>
            <div class="social-links mt-3">
              <a href="#" class="twitter"><i class="bx bxl-twitter"></i></a>
              <a href="#" class="facebook"><i class="bx bxl-facebook"></i></a>
              <a href="#" class="instagram"><i class="bx bxl-instagram"></i></a>
              <a href="#" class="google-plus"><i class="bx bxl-skype"></i></a>
              <a href="#" class="linkedin"><i class="bx bxl-linkedin"></i></a>
            </div>
          </div>
          <div class="col-lg-2 col-md-6 footer-links">
            <h4>Useful Links</h4>
           <ul>
              <li><i class="bx bx-chevron-right"></i> <a href="#">Web Design</a></li>
              <li><i class="bx bx-chevron-right"></i> <a href="#">Web Development</a></li>
              <li><i class="bx bx-chevron-right"></i> <a href="#">Product Management</a></li>
              <li><i class="bx bx-chevron-right"></i> <a href="#">Marketing</a></li>
              <li><i class="bx bx-chevron-right"></i> <a href="#">Graphic Design</a></li>
            </ul>
          </div>
          <div class="col-lg-3 col-md-6 footer-links">
            <h4>Our Services</h4>
            <ul>
              <li><i class="bx bx-chevron-right"></i> <a href="#">Web Design</a></li>
              <li><i class="bx bx-chevron-right"></i> <a href="#">Web Development</a></li>
              <li><i class="bx bx-chevron-right"></i> <a href="#">Product Management</a></li>
              <li><i class="bx bx-chevron-right"></i> <a href="#">Marketing</a></li>
              <li><i class="bx bx-chevron-right"></i> <a href="#">Graphic Design</a></li>
            </ul>
          </div>

          <div class="col-lg-4 col-md-6 footer-newsletter">
            <h4>Our Newsletter</h4>
            <p>Tamen quem nulla quae legam multos aute sint culpa legam noster magna</p>
            <form action="" method="post">
              <input type="email" name="email"><input type="submit" value="Subscribe">
            </form>

          </div>

        </div>
      </div>
    </div>

    <div class="container">
      <div class="copyright">
        &copy; 2021 <strong><span>copyright</span></strong>. All Rights Reserved
      </div>
      <div class="credits">
      </div>
    </div>
  </footer><!-- End Footer -->

  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

  <!-- Vendor JS Files -->
  <script src="/online-room-rent/assets/vendor/purecounter/purecounter.js"></script>
  <script src="/online-room-rent/assets/vendor/aos/aos.js"></script>
  <script src="/online-room-rent/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="/online-room-rent/assets/vendor/glightbox/js/glightbox.min.js"></script>
  <script src="/online-room-rent/assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
  <script src="/online-room-rent/assets/vendor/swiper/swiper-bundle.min.js"></script>
  <script src="/online-room-rent/assets/vendor/php-email-form/validate.js"></script>
  <!-- Template Main JS File -->
  <script src="/online-room-rent/assets/js/main.js"></script>
</body>
</html>