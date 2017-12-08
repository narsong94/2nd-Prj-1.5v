<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="page" value="${pageContext.request.contextPath}" />

<main>
<a href="${ctx}/admin/board/honor/reg"></a>
<c:forEach var="h" items="${list}">
${h.content}<p>
${h.tag}
</c:forEach>
</main>