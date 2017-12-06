<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="path" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css" href="${path}/resource/css/board/tip.css">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
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
});

</script>

<main>
<input type="hidden" id="tip_id" name="tip_id" value="${t.id}" />	
<table>
	<tbody>
		<tr>
			<th>제목</th>
			<td colspan="2">${t.title}</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td colspan="2">
				<fmt:formatDate pattern="yyyy-MM-dd" value="${t.date}" />
			</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${t.writerId}</td>
			<th>조회수</th>
			<td>${t.hit}</td>
		</tr>
		<tr class="content">
			<td colspan="3"><c:forEach var="file" items="${files}"
					varStatus="s">
					<img src="upload/${file.src}" />
				</c:forEach> ${t.content}<br />
		</tr>
	</tbody>
</table>

<!-- -----------------------------------2.좋아요 start-------------------------------------------------- -->
<input type="hidden" id="tip_id" name="tip_id" value="${img.id}" /> <a
	id="likeCount">${likeCount}</a>
<button id="like_save" name="like_save">좋아용!</button>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	//db좋아요올리기
	//댓글을 다는 이벤트
	$("#like_save").click(
			function() {
				//console.log("tip_id" + $("#tip_id").val());
				//값 셋팅
				var objParams = {
					tip_id : $("#tip_id").val()
				};
				//ajax 호출
				console.log("tip_id : "+ $("#tip_id").val());
				$.get("${path}/admin/board/tip/like/save?tipId="
						+ $("#tip_id").val(), function(data) {
					var json = JSON.parse(data);
					var idCheck = json['idCheck'];
					//var idCheck = data.idCheck;//안대~
					var likeCount = json['likeCount'];
					if (idCheck > 0)
						alert("이미 좋아요를 누르셨습니다.");
					else if (idCheck == -10)
						alert("죄송합니다. 오류가 생겼습니다. 빠른시일내로 복구하겠습니다.");
					$("#likeCount").text(likeCount);
				});

			});
	//좋아요
</script> <!-- -----------------------------------2.좋아요 end-------------------------------------------------- -->

<!---------------------------- 댓글 -------------------------------->

<!--DB에서 가져온 댓글테이블  -->
<table id="comment_area">
   <!-- 전체를 새로고침 할것이기때문에 필요없음 -->
   <tbody id="data">

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
      <td><textarea id="comment_content" name="comment_content"
            placeholder="댓글을 입력하세요."></textarea></td>
      <td><button id="comment_save" name="comment_save">댓글 등록</button></td>
      <td><button id="comment_update" name="comment_update">리플
            새로고침</button></td>
   </tr>
</table>
<!-- Bootstrap --> <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요한) --> <script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
   $(function() {

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
         
         //값 셋팅
         var objParams = {
        		 tip_id : $("#tip_id").val(),
            comment_content : comment_content
         };
         var comment_id;
         //ajax 호출
         $
               .ajax({

                  url : '${path}/admin/board/tip/comment/save?${_csrf.parameterName}=${_csrf.token}',
                  dataType : 'gson',
                  contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
                  type : 'POST',
                  async : false, //동기: false, 비동기: ture
                  data : objParams,

               });
         //댓글 초기화
         $("#comment_content").val("");
         $("#comment_area #data tr").remove();
         updateComment();
      }

      //댓글 새로고침을 하는 이벤트
      var updateComment = function() {
         //ajax 호출
         var template = $("#comment_area template");
         var tbody = $("#comment_area tbody");
         $("#comment_area #data tr").remove();
         $.get(
               "${path}/admin/board/tip/comment/update-ajax?tipId="
                     + $("#tip_id").val(), function(data) {

                  var json = JSON.parse(data);//data를 json형식으로 만들어줌
                  //댓글 새로고침부분
                  for (var i = 0; i < json.length; i++) {
                     var clone = $(document.importNode(template
                           .prop("content"), true));
                     var tds = clone.find("td");
                     tds.eq(0).text(json[i].writerId);
                     tds.eq(1).text(json[i].content);
                     var date = new Date(parseInt(Date
                           .parse(json[i].date)));
                     var month = date.getMonth() + 1;
                     var year = date.getFullYear();
                     var day = date.getDate();
                     var hour = date.getHours();
                     var min = date.getMinutes();
                     var sec = date.getSeconds();
                     tds.eq(2).text(
                           year + "-" + month + "-" + day + " " + hour
                                 + ":" + min + ":" + sec);

                     tbody.append(clone);// 복제된 clone(tr)을 노드 트리에 추가
                  }

               });
      };
      updateComment();

      $("#comment_save").click(function() {
         saveComment();
      });
      $('#comment_content').keyup(function(e) {
         if (e.keyCode == 13)
            saveComment();
      });

      $("#comment_update").click(function() {
         updateComment();
      });
   });
</script>


<!---------------------------------------------------------------->
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
	<a href="../tip">목록</a>
</div>

<div>
	<a href="../tip/${t.id}/edit">수정하기</a>
	<a href="../tip/${t.id}/del">삭제하기</a>
</div>

<a id="MOVE_TOP_BTN" href="#">TOP</a>

</main>