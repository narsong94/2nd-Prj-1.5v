<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<link rel="stylesheet" type="text/css" href="${path}/resource/css/header.css">

<div id="menu">
	<ul>
		<img id="icon" src="" />
		<c:if test="${ empty sessionScope.id }">
			<li><a class="menu-selector" href="${path}/login">Login</a></li>
		</c:if>
		<c:if test="${ not empty sessionScope.id }">
			<li><a class="menu-selector" href="${path}/admin/board/mypage">Mypage</a></li>
			<li><a class="menu-selector" href="${path}/logout">Logout</a></li>
		</c:if>
		<li><a class="menu-selector" href="#about">About</a></li>
		<li class="dropdown"> <a class="menu-selector" class="dropbtn">Board</a>
			<div class="dropdown-content">
				<a href="#Advice">Advice</a> 
				<a href="#Voting">Voting</a> 
				<a href="#Info">Info</a>
				<a href="#Tip">Tip</a>
				<a href="#Free">Free</a>
			</div>
		</li>
		<li><a class="menu-selector active" href="${path}/index">Home</a></li>
	</ul> 

</div>
