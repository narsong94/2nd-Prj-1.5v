<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
$(function() {
	var titleText = $("form input[name='title']");
	var contentText = $("form textarea[name='content']");
	var submitBtn = $("form input[type='submit']");
	var cancelBtn = $(".btns a");

	submitBtn.click(function(e) {
		if (titleText.val() == "" | contentText.val() == "") {
			alert("제목 및 내용을 입력하세요.");
			e.preventDefault();
		}
	});

	cancelBtn.click(function(e) {
		if (!confirm("작성중이던 입력을 취소하시겠습니까?")){
			if (titleText.val() == "" || contentText.val() == "") {
				alert("제목 및 내용을 입력하세요.");
			}
			else
				e.preventDefault();
		}
	});
});
</script>

<form action="edit" method="post">
	<table border="1">
		<tbody>
			<tr>
				<td>제목</td>
				<td colspan="2">
					<input type="text" name="title" value="${f.title}" />
				</td>
			</tr>
			<tr>
				<td>조회수</td>
				<td>${f.hit}</td>
			</tr>
			<%-- <tr>
				<td>첨부파일</td>
				<td colspan="3"><c:forEach var="f" items="${files}">
						<span>${f.src}</span>
						<a href="notice-file-del?code=${f.code}&ncode=${n.code}">[X]</a>
					</c:forEach></td>
			</tr> --%>
			<tr>
				<td colspan="3">
					<textarea name="content" rows="20" cols="60">${f.content}</textarea>
				</td>
			</tr>
		</tbody>
	</table>

	<div class="btns">
		<%-- <input type="hidden" name="code" value="${n.code}" />  --%>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<input type="submit" value="저장" /><%--  <a href="notice-detail?code=${n.code}">취소</a> --%>
		<a href="..">취소</a>
	</div>
</form>





