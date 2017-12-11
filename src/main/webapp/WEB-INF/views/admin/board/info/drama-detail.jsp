<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="path" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css" href="${path}/resource/css/board/info/info.css">
<script>
</script>

<main class="main clearfix">
<div id="leftWrapper" class="clearfix">
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
                     <div class="titleWrap2 floatWrapper">

                        <!-- 자바스크립트 날짜 이미지 -->
                        <div class="dateImg"><span class="imgdate"></span></div> 
                        <h2>
                           <a href="/221">${d.title}</a>
                           <span class="category"><a href="/category/I.lib%28%29/I.lib%28JSTL%29">${d.writerId}</a></span></h2>
                     </div><!-- titleWrap close -->
                     

                     <div class="article">
                     <!-- 포스팅 본문이 들어가는 부분입니다. -->
            <c:forEach var="file" items="${files}"
               varStatus="s">
               <img src="upload/${file.src}" />
            </c:forEach> ${d.content}
<div class="space"></div>
<!-- -----------------------------------2.좋아요 start-------------------------------------------------- -->
<%-- <input type="hidden" id="info_id" name="info_id" value="${img.id}" /> 
	<div style="margin: auto; text-align: center;">
<button id="like_save" name="like_save">♥</button>
</div>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	//db좋아요올리기
	//댓글을 다는 이벤트
	$("#like_save").click(
			function() {
				//console.log("info_id" + $("#info_id").val());
				//값 셋팅
				var objParams = {
					info_id : $("#info_id").val()
				};
				//ajax 호출

				$.get("${path}/admin/board/info/like/save?infoId="
						+ $("#info_id").val(), function(data) {
					var json = JSON.parse(data);
					var idCheck = json['idCheck'];
					//var idCheck = data.idCheck;//안대~
					var likeCount = json['likeCount'];
					if (idCheck > 0){
						alert("이미 좋아요를 누르셨습니다.");
						$("#like_save").text(likeCount + "명이 좋아합니다");						
					}
					else if (idCheck == -10)
						alert("죄송합니다. 오류가 생겼습니다. 빠른시일내로 복구하겠습니다.");
					$("#like_save").text(likeCount + "명이 좋아합니다");
					$('#like_save').blur();
				});

			});
	//좋아요 --%>
</script> <!-- -----------------------------------2.좋아요 end-------------------------------------------------- -->


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
         <a href="../info/${i.id}/edit"><img src="${path}/resource/img/설정.PNG" style="height: 20px; width:20px; "/></a>
         <a href="../info/${i.id}/del"><img src="${path}/resource/img/삭제.png" style="height: 20px; width:20px; "/></a>
         </div>
         
         </div>
         
         
         <div class="another_category another_category_color_gray">
</div></div>

      </div>
      
<%--       <div class="trailWrapper floatWrapper">
         <div class="actionTrail">
            <a href="#rp" onclick="toggleLayerForEntry('221', 'comment'); return false"><span id="commentCount221">Comment (<span class="cnt">2</span>)</span></a>
         </div>
         <div class="author">
            <span class="text">Posted by </span>${i.date }
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
               
         
   <ol> --%>

<!--DB에서 가져온 댓글테이블  -->
<%-- <table id="comment_area">
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

                  </div><!-- writeWrapper close -->
               </div><!-- commentWrite close -->
            </form>
         </div><!-- comment close -->
     <!--  </div><script type="text/javascript">loadedComments[221]=true;highlight()</script> -->
   </div><!-- entry close --> --%>


<!---------------------------- 댓글 -------------------------------->

<!-- Bootstrap --> <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요한) --> <!-- <script
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
        		 info_id : $("#info_id").val(),
            comment_content : comment_content
         };
         var comment_id;
         //ajax 호출
         $
               .ajax({

                  url : '${path}/admin/board/info/comment/save?${_csrf.parameterName}=${_csrf.token}',
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
               "${path}/admin/board/info/comment/update-ajax?infoId="
                     + $("#info_id").val(), function(data) {

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

 -->
<!---------------------------------------------------------------->
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
      <a class="move-list" href="../info">목록으로 이동</a>   
      </div> -->
      
      <div class="button_base b03_skewed_slide_in">
        <div><a href="../../info">목록으로 이동</a></div>
        <div></div>
        <div><a href="../../info">목록으로 이동</a></div>
    </div>
      
            </div><!-- content close -->
         </div><!-- contentContainer close -->
      </div>

<div>
   <!-- <a href="../info">목록</a> -->
</div>


<a id="MOVE_TOP_BTN" href="#">TOP</a>

</main>