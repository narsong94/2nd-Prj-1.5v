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
         $(".pic").prop('src', '${path}/resource/img/투표완료.PNG');
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
                        $(".pic").prop('src', '${path}/resource/img/투표완료.PNG');
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

<!-- 디테일 인포꺼ㅓㅇ러얼어ㅓ어어어러 -->
<main>
<input type="hidden" id="voting_id" name="voting_id" value="${v.id}" />
<div id="leftWrapper">
         <div class="left-round"></div>
         <div id="calendarWrapper" style="background:#ffffff;width:753.99px;height:0px;">
            <!-- 달력 모듈 -->
            <div id="calendar" class="floatWrapper"> 
            </div>
         </div><!-- calendarWrapper close -->
         <div id="contentContainer">
            <div id="content">
                  <!-- 포스트의 본문이 시작되는 곳입니다. -->
                  <div class="entry">
                     <div class="titleWrap floatWrapper">

                        <!-- 자바스크립트 날짜 이미지 -->
                        <div class="dateImg"><span class="imgdate">${v.date}</span></div>
                        <h2>
                           <a href="/221">${v.title}</a>
                           <span class="category"><a href="/category/I.lib%28%29/I.lib%28JSTL%29">${v.writerId}</a></span><span class="date">${v.date }      조회수 : ${v.hit}</span></h2>
                     </div><!-- titleWrap close -->
                     <div class="writerInfo">
                           <c:set var="w" value="${writer}"/>
                           <table class="w-table">
								<thead>
    								<tr>
    							    <th colspan="2" scope="cols">작성자 정보</th>
    								</tr>
    							</thead>
    							<tbody>
    								<tr>
        							<th scope="row">나이</th>
        							<td>${w.age}</td>
    								</tr>
    								<tr>
        							<th scope="row">키</th>
        							<td>${w.height}</td>
    								</tr>
    								<tr>
        							<th scope="row">몸무게</th>
        							<td>${w.weight}</td>
    								</tr>
    							</tbody>
						</table>
                     </div>

                     <div class="article">
                     <!-- 포스팅 본문이 들어가는 부분입니다. -->
            <c:forEach var="file" items="${files}"
               varStatus="s">
               <img src="upload/${file.src}" />
            </c:forEach> ${v.content}
            </div>
      
      <div class="votingSomenail">   
      <label>투표 진행중</label><p/>
         <c:set var="pic" value="${pics}" />
         <div id="gallery-box">
            <div class="gallery">
               <a target="_blank"> <img src='${pic.get(0)}' alt="Forest"
                  width="200" height="200">
               </a>
               <div class="desc">
                  <input type="image" class="pic" name="1" value="이거 사" src="${path}/resource/img/투표중.PNG" style="height: 22px; width:22px; "/> <a
                     id="vote1"></a>
               </div>
            </div>
            <div class="gallery">
               <a target="_blank"> <img src='${pic.get(1)}' alt="Forest"
                  width="200" height="200">
               </a>
               <div class="desc">
                  <input type="image" class="pic" name="2" value="이거 사" src="${path}/resource/img/투표중.PNG" style="height: 22px; width:22px; "/> <a
                     id="vote2"></a>
               </div>
            </div>
         </div>
         
         <div id="result" style="display: none;">
            <table border="1" id="result-table">
               <tr>
                  <td><a id="result-value1">${vl.like1}</a>명이 투표<img src="${path}/resource/img/투표.PNG" style="height: 18px; width:20px; "/></td>
               
                  <td><a id="result-value2">${vl.like2}</a>명이 투표<img src="${path}/resource/img/투표.PNG" style="height: 18px; width:20px; "/></td>
               </tr>
            </table>
         </div>      
      </div>
      <div class="autosourcing-stub">
<p style="FONT-SIZE: 12px; FONT-FAMILY: dotum; PADDING-BOTTOM: 0px; PADDING-TOP: 0px; PADDING-LEFT: 0px; MARGIN: 20px 0px 30px; PADDING-RIGHT: 0px">



 <div style="width:100%;margin-top:30px;clear:both;height:30px">   

 
 
    
 <div class="entry-ccl" style="float:right;margin-top:0;height:0">
<a href="http://creativecommons.org/licenses/by-nc-nd/4.0/deed.ko" target="_blank" style="text-decoration: none">
<img id="ccl-icon-221-0" class="entry-ccl-by" src="https://t1.daumcdn.net/tistory_admin/static/admin/editor/ccl_black01.png?_version_=896dfff6a1b64c596188a1c1dc69a8070820800e" onmouseover="tistoryCcl.show(this, 1)" onmouseout="tistoryCcl.hide()" alt="저작자 표시" style="width:15px;height:15px">
<img id="ccl-icon-221-1" class="entry-ccl-nc" src="https://t1.daumcdn.net/tistory_admin/static/admin/editor/ccl_black02.png?_version_=896dfff6a1b64c596188a1c1dc69a8070820800e" onmouseover="tistoryCcl.show(this, 1)" onmouseout="tistoryCcl.hide()" alt="비영리" style="width:15px;height:15px">
<img id="ccl-icon-221-2" class="entry-ccl-nd" src="https://t1.daumcdn.net/tistory_admin/static/admin/editor/ccl_black03.png?_version_=896dfff6a1b64c596188a1c1dc69a8070820800e" onmouseover="tistoryCcl.show(this, 1)" onmouseout="tistoryCcl.hide()" alt="변경 금지" style="width:15px;height:15px">
</a>
      </div>
               <script type="text/javascript">
            if (/MSIE [0-6]\./.test(navigator.userAgent)) {
               for (var i = 0; i <3; i++) {
                  var el = document.getElementById('ccl-icon-221-' + i);
                  el.style.filter = 'progid:DXImageTransform.Microsoft.AlphaImageLoader(src="' + el.src + '",sizingMethod="image")';
                  el.src = 'https://t1.daumcdn.net/tistory_admin/static/admin/form/s.gif?_version_=896dfff6a1b64c596188a1c1dc69a8070820800e';
               }
            }
         </script>
         <div class="post_btn"style="width: 130px;float:left;">
         <a href="/toolbar/popup/abuseReport/?entryId=221" onclick="window.open(this.href, 'tistoryThisBlogPopup', 'width=550, height=510, toolbar=no, menubar=no, status=no, scrollbars=no'); return false;">
         <img style="border:0" src="//t1.daumcdn.net/tistory_admin/static/ico/ico_spam_report.png" alt="신고">
         </a>
         <a href="../voting/${v.id}/edit"><img src="${path}/resource/img/설정.PNG" style="height: 20px; width:20px; "/></a>
         <a href="../voting/${v.id}/del"><img src="${path}/resource/img/삭제.png" style="height: 20px; width:20px; "/></a>
         </div>
         
         </div>
         
         
         <div class="another_category another_category_color_gray">
</div></div>
      <div class="trailWrapper floatWrapper">
         <div class="actionTrail">
            <a href="#rp" onclick="toggleLayerForEntry('221', 'comment'); return false"><span id="commentCount221">Comment (<span class="cnt">2</span>)</span></a>
         </div>
         <div class="author">
            <span class="text">Posted by </span>${v.date }
         </div>
      </div>
      <div id="entry221Trackback" style="display:none">
         <div class="trackback">
            <h3><span>TRACKBACK</span> | </h3>
            
         </div><!-- trackback close -->
      </div>
      <div id="entry221Comment" style="display:block">
         <div class="comment">
            <h3>댓글을 달아 주세요 </h3>
            <div class="commentList">
               
         
   <ol>
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
   </ol>         
            </div><!-- commentList close -->



            <form method="post" action="/comment/add/221" onsubmit="return false" style="margin: 0">
               <div class="commentWrite">
                  <div class="writeWrapper">
                     <p><textarea name="comment_content" cols="50" rows="6" id="comment_content" placeholder="댓글을 입력하세요."></textarea></p>
                     <div class="ripple_btn">
                        <p class="button"><button id="comment_save" name="comment_save"><img src="${path}/resource/img/등록.PNG" style="height: 30px; width:30px; "/></button></p>
                        <p class="button"><button id="comment_update" name="comment_update"><img src="${path}/resource/img/새로고침.PNG" style="height: 30px; width:30px; "/></button></p>
                         </div>

                  <!--    <p class="button"><input type="submit" value="CONFIRM" onclick="addComment(this, 221); return false;" class="submit"></p> -->
                  </div><!-- writeWrapper close -->
               </div><!-- commentWrite close -->
            </form>
         </div><!-- comment close -->
     <!--  </div><script type="text/javascript">loadedComments[221]=true;highlight()</script> -->
   </div><!-- entry close -->


               
<div id="paging">
	<div>
      <c:if test="${empty prev}">
         	이전글 : 이전글이 없습니다.
      </c:if>
      <c:if test="${not empty prev}">
      		이전글 : <a href="${prev.id}">${prev.title}</a>
      </c:if>
	</div>
	<div>
      <c:if test="${empty next}">
         	다음글 : 다음글이 없습니다.
      </c:if>
      <c:if test="${not empty next}">
      		다음글 : <a  href="${next.id}">${next.title}</a>
      </c:if>
	</div>
</div>
      
<!--       <div style="margin: auto; text-align: center; margin-bottom: 60px; margin-top: 50px;">
      <a class="move-list" href="../advice">목록으로 이동</a>   
      </div>
       -->
      
    <div class="button_base b03_skewed_slide_in">
        <div><a href="../advice">목록으로 이동</a></div>
        <div></div>
        <div><a href="../advice">목록으로 이동</a></div>
    </div>
     
            </div><!-- content close -->
         </div><!-- contentContainer close -->
      </div>


<!---------------------------- 댓글 -------------------------------->


<!-- 댓글 삽입하는 테이블 -->
<!-- <table>
   <tr>
      <td><textarea id="comment_content" name="comment_content"
            placeholder="댓글을 입력하세요."></textarea></td>
      <td><button id="comment_save" name="comment_save">댓글 등록</button></td>
      <td><button id="comment_update" name="comment_update">리플
            새로고침</button></td>
   </tr>
</table> -->
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
<a id="MOVE_TOP_BTN" href="#">TOP</a>

</main>


<!-- 원래꺼ㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓ -->