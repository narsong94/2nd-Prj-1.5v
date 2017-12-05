<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script>
/* $(function() {
    $(window).scroll(function() {
        if ($(this).scrollTop() > 200) {
            $('#MOVE_TOP_BTN').fadeIn();
        } else {
            $('#MOVE_TOP_BTN').fadeOut();
        }
    });
    
    $("#MOVE_TOP_BTN").click(function() {
        $('html, body').animate({
            scrollTop : 0
        }, 400);
        return false;
    });
});
 */
</script>

<main>
<input type="hidden" id="advice_id" name="advice_id" value="${a.id}" />					<!-- 댓글 중요!!!!!!!!!! -->
<table>
	<tbody>
		<tr>
			<th>제목</th>
			<td colspan="2">${a.title}</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td colspan="2">
				<fmt:formatDate pattern="yyyy-MM-dd" value="${a.date}" />
			</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${a.writerId}</td>
			<th>조회수</th>
			<td>${a.hit}</td>
		</tr>
		<%-- <tr>
			<th>첨부파일</th>
			<td colspan="3">
				<c:forEach var="file" items="${files}" varStatus="s">
					<a href="../download?f=${file.src}">${file.src}</a>
					<c:if test="${!s.last}">,</c:if>
				</c:forEach>
			</td>
		</tr> --%>
		<tr class="content">
			<td colspan="3"><c:forEach var="file" items="${files}"
					varStatus="s">
					<img src="upload/${file.src}" />
				</c:forEach> ${a.content}<br />
		</tr>
		<tr>
			<td colspan="3">
				<c:forEach var="prevAdvice" items="${prevlist}">
					${prevAdvice.title} ~
				</c:forEach>
			</td>
		</tr>
	</tbody>
</table>

<!-- -----------------------------------댓글 start-------------------------------------------------- -->

<!--DB에서 가져온 댓글테이블  -->
<table id="comment_area">
	<tbody>
	  <c:forEach var="comment" items="${adviceCommentList}" varStatus="status">
	  	<tr>
	  		<td>${comment.writerId }</td>
	  		<td>${comment.content }</td>
	  		<td><fmt:formatDate value="${comment.date}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	  		<td><input type="checkbox" id="${comment.id}" name="${comment.id}" value="${comment.id}"/></td>	
	  	</tr>
	  	<input type="hidden" class="adviceComment_id" name="adviceComment_id" value="${comment.id}" /> 
	  </c:forEach>
  </tbody>
  <template>
	<tr>
		<td></td>
		<td></td>
		<td></td>
	</tr>
  </template>
</table>
<!-- 댓글 삽입하는 테이블 -->
<table>
	<tr>
		<td><textarea id="comment_content" name="comment_content" placeholder="댓글을 입력하세요."></textarea> </td>
		<td><button id="comment_save" name="comment_save">댓글 등록</button></td>
		<td><button id="comment_update" name="comment_update">리플 새로고침</button></td>
		<td><button id="comment_delete" name="comment_delete">리플 삭제</button></td>
	</tr>
</table>
<!-- Bootstrap -->
<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요한) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>	
console.log("advice_id : " + $("#advice_id").val());
	var count = 0;
	$(function() {
		
	    $(window).scroll(function() {
	        if ($(this).scrollTop() > 200) {
	            $('#MOVE_TOP_BTN').fadeIn();
	        } else {
	            $('#MOVE_TOP_BTN').fadeOut();
	        }
	    });
	    
	    $("#MOVE_TOP_BTN").click(function() {
	        $('html, body').animate({
	            scrollTop : 0
	        }, 400);
	        return false;
	    });
		
		//삭제링크를 눌렀을때 해당 댓글을 삭제하는 이벤트
		$("#comment_delete").click(function(){
			console.log("삭제");
			var i = ($(".adviceComment_id").first().val());
			for(var a=$(".adviceComment_id").first().val(); a<= $(".adviceComment_id").last().val(); a++){
				if(a.checked)
					console.log(a);
			}
			
		});
						
		//댓글 새로고침을 하는 이벤트
		var updateComment = function() {
				//ajax 호출
				//console.log("count더하기 전"+$(".adviceComment_id").last().val());			
				var adviceCommentLastId = $(".adviceComment_id").last().val();
				var fianlAdviceCommentLastId = parseInt(adviceCommentLastId) + count;
				//console.log("count더하기 후"+fianlFreeCommentLastId);
				
				var template  = $("#comment_area template");
				var tbody = $("#comment_area tbody");
				
				$.get("${path}/SecondPrj/admin/board/advice/comment/update-ajax?adviceId="
						+ $("#advice_id").val() + "&cId="
						+ fianlAdviceCommentLastId, function(data) {
						
						//alert(data);
						if(data=="[]")
							alert("최신댓글입니다.");
						else{
							var json =JSON.parse(data);//data를 json형식으로 만들어줌
	 						for (var i = 0; i < json.length; i++) {
								var clone = $(document.importNode(template.prop("content"),		
										true));
								var tds = clone.find("td");
								tds.eq(0).text(json[i].writerId);
								tds.eq(1).text(json[i].content);
								var date = new Date(parseInt(Date.parse(json[i].date)));
								var month = date.getMonth()+1;
								var year = date.getFullYear();
								var day = date.getDate();
								var hour = date.getHours();
								var min = date.getMinutes();	
								var sec = date.getSeconds();
								tds.eq(2).text(year+"-"+month+"-"+day+" "+hour+":"+min+":"+sec);
								
								tbody.append(clone).html;// 복제된 clone(tr)을 노드 트리에 추가
								
								count++;
							}
						}	
				});
			};
			
			//댓글을 다는 이벤트
			var saveComment = function() {
				//null 검사
				if ($("#comment_content").val().trim() == "") {
					alert("내용을 입력하세요.");
					$("#comment_content").focus();
					return false;
				}
				var comment_content = $("#comment_content").val()
						.replace("\n", " ");//개행처리
						//.replace("\n", "<br>");//개행처리			
				//값 셋팅
				var objParams = {
					advice_id : $("#advice_id").val(),
					comment_content : comment_content
				};
				console.log("objParmas_advicerid : "+ $("#advice_id").val());
				/* var token = $("meta[name='_csrf']").attr("content");
				var header = $("meta[name='_csrf_header']").attr(
						"content"); */
				var comment_id;
				//ajax 호출
				$.ajax({
							/* beforeSend : function(xhr) {
								xhr.setRequestHeader(header, token);
							}, */
							url : '${path}/SecondPrj/admin/board/advice/comment/save?${_csrf.parameterName}=${_csrf.token}',
							dataType : 'gson',
							contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
							type : 'POST',
							async : false, //동기: false, 비동기: ture
							data : objParams 								
					});
							console.log("ajax실행후");
				//댓글 초기화
				$("#comment_content").val("");
				updateComment();
			}
		
		$("#comment_save")
				.click(function(){
					saveComment();
				});
		$('#comment_content').keyup(function(e) {
		    if (e.keyCode == 13) saveComment();        
		});
		출처: http://blog.whitelife.co.kr/95 [White Life Story]
		$("#comment_update").click(function() {
			updateComment();
		});
	});
</script>
<!---------------------------------------댓글 end--------------------------------------------------------->

<table>
	<tbody>
		<c:if test="${empty next}">
			<tr>
				<th>다음글</th>
				<td colspan="3">다음글이 없습니다.</td>
			</tr>
		</c:if>
		<c:if test="${not empty next}">
			<tr>
				<th>다음글</th>
				<td colspan="3">
					<a href="${next.id}">${next.title}</a>
				</td>
			</tr>
		</c:if>
		<c:if test="${empty prev}">
			<tr>
				<th>이전글</th>
				<td colspan="3">이전글이 없습니다.</td>
			</tr>
		</c:if>
		<c:if test="${not empty prev}">
			<tr>
				<th>이전글</th>
				<td colspan="3">
					<a href="${prev.id}">${prev.title}</a>
				</td>
			</tr>
		</c:if>
	</tbody>
</table>

<div>
	<a href="../advice">목록</a>
</div>

<div>
	<a href="../advice/${a.id}/edit">수정하기</a>
	<a href="../advice/${a.id}/del">삭제하기</a>
</div>

<a id="MOVE_TOP_BTN" href="#">TOP</a>

</main>