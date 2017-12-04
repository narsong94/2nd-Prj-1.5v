<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<div>
	<a>회원가입이 정상적으로 완료되었습니다.</a>
	<a>즐겁고 행복한 하루 되시기 바랍니다.</a>
	<a href="${path}/login">로그인</a>
	<a href="${path}/index">홈으로</a>
</div>