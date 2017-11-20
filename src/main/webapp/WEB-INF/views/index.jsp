<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<link rel="stylesheet" type="text/css" href="resource/css/index.css">

<script type="text/javascript">
$("#dropdown-content a").click(function() {
	alert("???");
  var scrollPosition = $($(this).attr("href")).offset().top;
 
  $("body").animate({
        scrollTop: scrollPosition
  }, 500);
}); 
</script>

<div id="menu" class="fixed">
	<ul>
		<c:if test="${ empty sessionScope.id }">
			<li><a class="menu-selector" href="${path}/login">Login</a></li>
		</c:if>
		<c:if test="${ not empty sessionScope.id }">
			<li><a class="menu-selector" href="${path}/admin/board/mypage">Mypage</a></li>
			<li><a class="menu-selector" href="${path}/logout">Logout</a></li>
		</c:if>
		<li><a class="menu-selector" href="#about">About</a></li>
		<li class="dropdown"><a class="menu-selector" class="dropbtn">Board</a>
			<div class="dropdown-content">
				<a href="#advice">Advice</a> 
				<a href="#voting">Voting</a> 
				<a href="#info">Info</a> 
				<a href="#tip">Tip</a> 
				<a href="#free">Free</a>
			</div></li>
		<li><a class="menu-selector active"
			href="#home">Home</a></li>
	</ul>
</div>

<div id="main-menu">
	<section id="home" class="page">
		<li><a>home</a></li>
	</section>
	<section id="advice" class="page">
		<li><a>advice</a></li>
	</section>
	<section id="voting" class="page">
		<li><a>voting</a></li>
	</section>
	<section id="info" class="page">
		<li><a>info</a></li>
	</section>
	<section id="tip" class="page">
		<li><a>tip</a></li>
	</section>
	<section id="free" class="page">
		<li><a id=>free</a></li>
	</section>
</div>
