<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		var id = ${v.id};
		var user = ${voteUser};
		if (user == 1) {
			$(".pic").prop('value', '투표 완료');
			$("#result").css("display", "inline");
		} else {
			$(".pic").prop('value', '이거 사');
		}
		$(".pic").click(
				function(e) {
					var num = e.target.name;
					//alert(num);
					if (e.target.value == '이거 사') {
						$.ajax({ // .like 버튼을 클릭하면 <새로고침> 없이 ajax로 서버와 통신하겠다.
							type : "POST", // 데이터를 전송하는 방법을 지정
							url : '${path}/admin/board/voting/' + id
									+ '/like?num=' + num
									+ '&${_csrf.parameterName}=${_csrf.token}', // 통신할 url을 지정
							data : id, // 서버로 데이터 전송시 옵션
							dataType : "text", // 서버측에서 전송한 데이터를 어떤 형식의 데이터로서 해석할 것인가를 지정, 없으면 알아서 판단
							// 서버측에서 전송한 Response 데이터 형식 (json)
							// {'likes_count': post.like_count, 'message': message }
							success : function(data) { // 통신 성공시 - 동적으로 좋아요 갯수 변경, 유저 목록 변경
								//alert(data);
								//alert("#vote"+ num);
								$("#result").css("display", "inline");
								$("#vote" + num).text(data);
								$("#result-value" + num).text(data);
								$(".pic").prop('value', '투표 완료');
							},
							error : function(request, status, error) { // 통신 실패시 - 로그인 페이지 리다이렉트
								//alert("로그인이 필요합니다.");
								//window.location.replace("${path}/login");
								alert("code:" + request.status + "\n"
										+ "message:" + request.responseText
										+ "\n" + "error:" + error);
							},
						});
					} else {
						alert("투표는 한번만 됩니다.")
					}
				});
	});
</script>
<script>
	$(function() {
		$(window).scroll(function() {
			if ($(this).scrollTop() > 100) {
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

<link rel="stylesheet" type="text/css"
	href="${path}/resource/css/board/voting.css">

<main class="main clearfix">
<input type="hidden" id="voting_id" name="voting_id" value="${v.id}" />
<table>
	<tbody>
		<tr>
			<th>제목</th>
			<td colspan="2">${v.title}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${v.id}</td>
			<th>조회수</th>
			<td>${v.hit}</td>
		</tr>
		<tr class="content">
			<div>
				<c:set var="w" value="${writer}"/>
				<a>! 작성자 정보 !</a>
				<table border="1">
					<tr>
						<td>나이</td>
						<td><a>${w.age}대</a></td>
					</tr>
					<tr>
						<td>키</td>
						<td><a>${w.height}</a></td>
					</tr>
					<tr>
						<td>몸무게</td>
						<td><a>${w.weight}</a></td>
					</tr>
				</table>

			</div>
			<td colspan="3"><c:forEach var="file" items="${files}"
					varStatus="s">
					<img src="upload/${file.src}" />
				</c:forEach> ${v.content}<br />
		</tr>
	</tbody>
</table>

<c:set var="pic" value="${pics}" />
<div id="gallery-box">
	<div class="gallery">
		<a target="_blank"> <img src='${pic.get(0)}' alt="Forest"
			width="350" height="400">
		</a>
		<div class="desc">
			<input type="button" class="pic" name="1" value="이거 사"> <a
				id="vote1">${vl.like1}</a>
		</div>
	</div>
	<div class="gallery">
		<a target="_blank"> <img src='${pic.get(1)}' alt="Forest"
			width="350" height="400">
		</a>
		<div class="desc">
			<input type="button" class="pic" name="2" value="이거 사"> <a
				id="vote2">${vl.like2}</a>
		</div>
	</div>
</div>

<div id="result" style="display: none;">
	<table border="1" id="result-table">
		<tr>
			<td>1번 후보</td>
			<td><a id="result-value1">${vl.like1}</a></td>
		</tr>
		<tr>
			<td>2번 후보</td>
			<td><a id="result-value2">${vl.like2}</a></td>
		</tr>
	</table>

</div>

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
        		 voting_id : $("#voting_id").val(),
            comment_content : comment_content
         };
         var comment_id;
         //ajax 호출
         $
               .ajax({

                  url : '${path}/admin/board/voting/comment/save?${_csrf.parameterName}=${_csrf.token}',
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
               "${path}/admin/board/voting/comment/update-ajax?votingId="
                     + $("#voting_id").val(), function(data) {

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

<div class="next-prev">
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
					<td colspan="3"><a href="${next.id}">${next.title}</a></td>
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
					<td colspan="3"><a href="${prev.id}">${prev.title}</a></td>
				</tr>
			</c:if>
		</tbody>
	</table>
</div>

<div>
	<a href="../advice">목록</a>
</div>

<div>
	<a href="../voting/${v.id}/edit">수정하기</a> <a
		href="../voting/${v.id}/del">삭제하기</a>
</div>

<a id="MOVE_TOP_BTN" href="#">TOP</a> </main>