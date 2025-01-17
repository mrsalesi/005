<%-- 
    Document   : index
    Created on : Nov 15, 2021, 1:13:01 PM
    Author     : IRANNOVIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="cms.tools.Js"%>
<%@page import="cms.tools.jjTools"%>
<%@page import="javax.servlet.http.HttpServlet"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>
<%@page import="cms.access.Access_User"%>
<%@page import="jj.jjDatabase"%>
<%@page import="cms.tools.jjTools"%>
<%@page import="jj.jjCalendar_IR"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="cms.tools.Server"%>
<%@page import="jj.jjDatabaseWeb"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="javax.xml.ws.Holder"%>
<%@page import="cms.tools.ServerLog"%>
<%@page import="cms.tools.Server"%>
<%@page import="cms.cms.Content"%>
<%@page import="cms.cms.Pic"%>
<%@page import="cms.cms.Category_Product"%>
<%@page import="cms.cms.Product"%>
<%@page import="cms.cms.Journal"%>
<%@page import="cms.cms.Category_Gallery"%>
<%@page import="cms.cms.Comment"%>
<%@page import="cms.cms.News"%>
<%

    Server.Connect();
//    request.setAttribute("text", "تعاونی دادگستری");
//    Content.sw(request, response, Server.db, true);
    String categuryId = jjTools.getParameter(request, "category");
    System.out.println("categuryId====" + categuryId);
    StringBuilder html1 = new StringBuilder();
    jjDatabaseWeb db;
    db = Server.db;
%>
<!DOCTYPE html>

<!DOCTYPE html>
<!-- saved from url=(0047)http://themesflat.com/html/arch/latestpost.html -->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US"><!--<![endif]--><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


            <!--[if IE]><meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->
            <title>تعاونی دادگستری</title>
            <meta name="author" content="themesflat.com">

                <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
                    <link href="template/css/bootstrap.css" rel="stylesheet" type="text/css"/>
                    <link href="template/css/style.css" rel="stylesheet" type="text/css"/>
                    <link href="template/css/responsive.css" rel="stylesheet" type="text/css"/>
                    <link href="template/colors/color.css" rel="stylesheet" type="text/css"/>
                    <link href="template/css/animate.css" rel="stylesheet" type="text/css"/>
                    <link href="template/css/revelationSlider.css" rel="stylesheet" type="text/css"/>
                    <link href="template/css/flexslider.css" rel="stylesheet" type="text/css"/>
                    <link href="template/css/magpopup.css" rel="stylesheet" type="text/css"/>
                    <link href="template/css/shortcodes.css" rel="stylesheet" type="text/css"/>
                    <link href="template/css/owlcarusel.css" rel="stylesheet" type="text/css"/>
                    <link href="Manager/font-awesome.css" rel="stylesheet">
                        <link href="Manager/font-tahoma.css" rel="stylesheet" type="text/css"/>
                        <link href="Manager/ionicons.css" rel="stylesheet">


                            <style id="fit-vids-style">.fluid-width-video-wrapper{width:100%;position:relative;padding:0;}.fluid-width-video-wrapper iframe,.fluid-width-video-wrapper object,.fluid-width-video-wrapper embed {position:absolute;top:0;left:0;width:100%;height:100%;}
                                @media only screen and (max-width: 600px) {
                                    .menu-extra {
                                        display: none;
                                    }

                                }
                                @media screen and (min-width: 0px) and (max-width: 600px) {
                                    .menu-extra-mobile {display: block; }  /* show it on small screens */
                                }
                                @media screen and (min-width: 0px) and (max-width: 600px) {
                                    .information {display: grid; }  /* show it on small screens */
                                }

                                @media screen and (min-width: 600px){
                                    .menu-extra-mobile { display: none; 
                                    }   /* hide it elsewhere */
                                }
                                @media screen and (min-width: 600px){
                                    .textlogin1  { display: none; 
                                    }   /* hide it elsewhere */
                                }
                                @media screen and (min-width: 0px) and (max-width: 600px) {
                                    .textlogin1 {display: block; } ;   
                                }

                            </style></head>
                            <body class="header_sticky" style="font-family: vazir2">
                                <section class="flat-row portfolio-style2" id="gallery" style="direction: ltr;margin-top: 5px">
                                    <div class="container">
                                        <div class="row">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="title-section style3">
                                                        <h3 class="title">گالری</h3>
                                                    </div>
                                                </div>
                                            </div>
                                            <%
                                                List<Map<String, Object>> gallery = jjDatabase.separateRow(db.Select(Pic.tableName, Pic._category_id + "=11"));
                                            %>                                            
                                            <div class="col-md-12">
                                                <div class="featured-post flat-blog-slider">
                                                    <div id="slider" class="flexslider" >
                                                        <ul class="slides">
                                                            <%for (int i = 0; i < gallery.size(); i++) {%>
                                                            <li>
                                                                <a href="#"><img src="upload/<%=gallery.get(i).get(Pic._url_name) + "." + gallery.get(i).get(Pic._url_ex)%>" alt="image"></a>
                                                            </li>
                                                            <%}%>
                                                        </ul>
                                                    </div>
                                                    <div id="carousel" class="flexslider">
                                                        <ul class="slides">
                                                            <%for (int i = 0; i < gallery.size(); i++) {%>
                                                            <li>
                                                                <a href="#"><img src="upload/<%=gallery.get(i).get(Pic._url_name) +"_small"+ "." + gallery.get(i).get(Pic._url_ex)%>" alt="image"></a>
                                                            </li>
                                                            <%}%>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </section>
                                <script src="template/js/jquery.min.js" type="text/javascript"></script>
                                <script src="template/js/bootstrap.min.js" type="text/javascript"></script>
                                <script src="template/js/jj2.js" type="text/javascript"></script>
                                <script src="template/js/index.js" type="text/javascript"></script>
                                <!--                                        <script src="template/js/jquery.easing.js" type="text/javascript"></script>
                                                                        <script src="template/js/imagesloaded.min.js" type="text/javascript"></script>
                                                                        <script src="template/js/jquery.isotope.min.js" type="text/javascript"></script>
                                                                        <script src="template/js/jquery-waypoints.js" type="text/javascript"></script>
                                                                        <script src="template/js/jquery.magnific-popup.min.js" type="text/javascript"></script>
                                                                        <script src="template/js/jquery.cookie.js" type="text/javascript"></script>
                                                                        <script src="template/js/jquery.fitvids.js" type="text/javascript"></script>
                                                                        <script src="template/js/parallax.js" type="text/javascript"></script>
                                                                        <script src="template/js/smoothscroll.js" type="text/javascript"></script>-->
                                <script src="template/js/jquery.flexslider-min.js" type="text/javascript"></script>
                                <!--<script src='template/js/owl.carousel.js' type='text/javascript'></script>-->
                                <!--<script src="template/js/jquery-validate.js" type="text/javascript"></script>-->
                                <!--<script src="template/js/switcher.js" type="text/javascript"></script>-->
                                <!--<script src="template/js/js.js" type="text/javascript"></script>-->
                                <!--<script src="template/js/gmap3.min.js" type="text/javascript"></script>-->
                                <!--<script src="template/js/main.js" type="text/javascript"></script>-->
                                <!--                                        <script src="template/js/jquery.themepunch.tools.min.js" type="text/javascript"></script>
                                                                        <script src="template/js/jquery.themepunch.revolution.min.js" type="text/javascript"></script>-->
                                <!--                                        <script src="template/js/slider.js" type="text/javascript"></script>
                                                                        <script src="template/js/util.js" type="text/javascript"></script>-->
                                <script type="text/javascript">
                                    // The slider being synced must be initialized first
                                    $('#carousel').flexslider({
                                        animation: "slide",
                                        controlNav: true,
                                        animationLoop: true,
                                        slideshow: false,
                                        itemWidth: 210,
                                        itemMargin: 5,
                                        asNavFor: '#slider',
                                        prevText: '<i class="fa fa-angle-left"></i>',
                                        nextText: '<i class="fa fa-angle-right"></i>',
                                        smoothHeight: true
                                    });

                                    $('#slider').flexslider({
                                        animation: "slide",
                                        controlNav: false,
                                        animationLoop: false,
                                        slideshow: false,
                                        sync: "#carousel",
                                        prevText: '<i class="fa fa-angle-left"></i>',
                                        nextText: '<i class="fa fa-angle-right"></i>',
                                    });
                                </script>
                            </body>
                            </html>
