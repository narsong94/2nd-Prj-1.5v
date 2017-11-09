<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
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
<form action="?${_csrf.parameterName}=${_csrf.token}" method="post"
	enctype="multipart/form-data">
	<table border="1">
		<tbody>
			<tr>
				<td>제목</td>
				<td><input type="text" name="title" /></td>
			</tr>
			<tr>
				<td>첨부파일</td>
				<td><input type="file" name="file" /></td>
			</tr>
			<!-- <tr>
				<td>첨부파일</td>
				<td><input type="file" name="file" /></td>
			</tr> -->
			<tr>
				<td colspan="2"><textarea name="content" rows="20" cols="60"></textarea>

				</td>
			</tr>
		</tbody>
	</table>

	<div class="btns">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
		<input type="submit" value="등록" /> 
		<a href="../info">취소</a>
	</div>
</form>