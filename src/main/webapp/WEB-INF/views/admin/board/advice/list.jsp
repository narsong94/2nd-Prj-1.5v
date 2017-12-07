<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<%-- <link rel="stylesheet" type="text/css" href="${ctx}/resource/css/board/advice.css"> --%>
<link rel="stylesheet" type="text/css" href="${ctx}/resource/css/board/advice2.css">
<link rel="stylesheet" type="text/css" href="http://scdn.celpick.com/v2_assets/css/animate.min.css?t=17040613">
<style>
@import url(//fonts.googleapis.com/earlyaccess/nanumbrushscript.css);
</style>
<!------------------------------------ Voting 부분  ------------------------------------------>

<%-- 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script>
var container = 1040;
var display = 3;
var item = container / display;
var count = 10; //$(".vitem").length;
var slidebox = item * count;

console.log("container : " + container);
console.log("display : " + display);
console.log("count : " + count);
console.log("item : " + item);
console.log("slidebox : " + slidebox);

function init() {
	$(".vcontainer").css("width", container);
	$(".slider").css("width", slidebox);
	$(".vitem").css("width", item);
	$(".vimg").css("width", item/2);
	$(".desc").css("width", item);
}

function moveSlider(chk) {
	
	console.log("chk : " + chk);
	
	if(chk == 0){
		$(".slider").animate({
			left: "+=" + item + "px"	
		}, 300 , slideEnd())
	} else if(chk == 1) {
		$(".slider").animate({
			left: "-=" + item + "px"	
		}, 300 , slideEnd())
	}
}

function slideEnd(){
	var nowLeft = $(".slider").position().left;
	console.log("nowLeft : " + nowLeft);
	
	var end = -(slidebox - container);
	console.log(nowLeft);
	console.log(end);
	
	if(nowLeft <= end){
		$(".slider").animate({
			left: end	
		});
	} else if(nowLeft > 0){
		$(".slider").animate({
			left: 0	
		});
	}
}

$(function(){
	init();
	
	$(".slide-next").click(function(){moveSlider(1)});
	$(".slide-prev").click(function(){moveSlider(0)});
})
</script>
<div id="sss">
<h3><img src="${ctx}/resource/img/info/TITLE/픽.png"  /></h3>
<a href="${ctx}/admin/board/voting/reg">추가하기</a>
<div class="vcontainer">
	<div class="slider">
		<c:forEach var="v" items="${vList}">
		<div class="vitem">
			<a href="voting/${v.id}" id="vitem-a" class="btn-example">
			<c:forEach var="pic" items="${v.pics}">
				<img class="vimg" src="${pic}" />
			</c:forEach>	
				<span class="desc">
					<strong>vs</strong>
				</span>
			</a>
		</div>
		</c:forEach>
	</div>
</div>
</div>
<div class="slide-btn">
	<button class="slide-prev" value="0" >이전</button>
	<button class="slide-next" value="1">다음</button>
</div> --%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script>
var container = 1040;
var display = 3;
var item = container / display-40;
var count = 10; //$(".vitem").length;
var slidebox = item * count;

console.log("container : " + container);
console.log("display : " + display);
console.log("count : " + count);
console.log("item : " + item);
console.log("slidebox : " + slidebox);

function init() {
   $(".vcontainer").css("width", container);
   $(".slider").css("width", slidebox);
   $(".vitem").css("width", item);
   $(".vimg").css("width", item/2);
   $(".desc").css("width", item);
}

function moveSlider(chk) {
   
   console.log("chk : " + chk);
   
   if(chk == 0){
      $(".slider").animate({
         left: "+=" + item + "px"   
      }, 300 , slideEnd())
   } else if(chk == 1) {
      $(".slider").animate({
         left: "-=" + item + "px"   
      }, 300 , slideEnd())
   }
}

function slideEnd(){
   var nowLeft = $(".slider").position().left;
   console.log("nowLeft : " + nowLeft);
   
   var end = -(slidebox - container);
   console.log(nowLeft);
   console.log(end);
   
   if(nowLeft <= end){
      $(".slider").animate({
         left: end   
      });
   } else if(nowLeft > 0){
      $(".slider").animate({
         left: 0   
      });
   }
}

$(function(){
   init();
   
   $(".slide-next").click(function(){moveSlider(1)});
   $(".slide-prev").click(function(){moveSlider(0)});
})
</script>
<div class="adviceContainer">
<h3  style="   margin-top: 0px;    padding-left: 20%;padding-top: 10px;"><img src="${ctx}/resource/img/info/TITLE/픽2.png"  />
<a href="${ctx}/admin/board/voting/reg" ><input type="image" src="${ctx}/resource/img/등록.png"style="width:30px;height:30px;" ></a>



</h3>

<input type="image" alt="" class="slide-prev arrow_off"  value="0" src="//image.istarbucks.co.kr/common/img/util/mscard_arrow_l_off.png">
<input type="image" alt="" class="slide-next arrow_off"  value="0" src="//image.istarbucks.co.kr/common/img/util/mscard_arrow_r_off.png">

      

<%-- <img src="${path}/resource/img/스타백.jpg" style="height: 360px; float:left;"/> --%>
<div class="vcontainer">
<p id="prevBtn">
         
   </p>
   <div class="slider">
      <c:forEach var="v" items="${vList}">
      <div class="vitem">
         <a href="voting/${v.id}" id="vitem-a" class="btn-example">
         <c:forEach var="pic" items="${v.pics}">
            <img class="vimg" src="${pic}" />
         </c:forEach>   
            <span class="desc">
               <strong>
               <input type="image" style="width:50px;height:50px;" src="${ctx}/resource/img/versus.png">
               </strong>
            </span>
         </a>
      </div>
      </c:forEach>
   </div>
   
</div>
</div>
<!-- <div class="slide-btn">
   
   <button class="slide-prev" value="0" >이전</button>
   <button class="slide-next" value="1">다음</button>
</div> -->

<!------------------------------------ Advice 부분 ------------------------------------------>



<div id = "content" >
<h2>


	<img src="${ctx}/resource/img/info/TITLE/코디상담40.png"  />
<a href="${ctx}/admin/board/advice/reg">
<img src="${ctx}/resource/img/등록.PNG" style="height: 30px; width:30px; "/></a>
</h2>
	<div class="celpickz_3">
		<div class="ajax-content">
		
		<!-- -----이 부분이 반복될 예정 -->
		
		  	<c:forEach var="advice" items="${list}">
				<div id="" class="card none wow fadeInUp">
					<!-- <a href="/LooksView/24159?t=17041219" onclick="return pageGo(24159);"> -->
						 <a href="advice/${advice.id}">
						<div id="" class="list_title">
							<li class="title_a">
							<span>${advice.title}</span>
								<!-- <span>정소민의</span>
								 폭신한 코트가 귀여워요 -->
							 </li>
							
						 </div>
				 
						 <div id="" class="img_list right_area">
							 <div id="" class="img_celeb">
<!-- 							 <img src="http://scdn.celpick.com/data/imageData/24159/24159-1.jpg?t=1512006379"> -->
							 <img src="${advice.content}"/>
							 
							 </div>
				 
						<!-- 	 <div id="" class="img_product right_area">
							 <img src="http://scdn.celpick.com/data/imageDataThumb/24159/gt_350_244_24159-0.jpg?t=1512006379">
							 </div> -->
						 </div>
				 	</a>
				 	
				 		<div class="likes">
							 <li class="likes_txt">♥ ${likeNum }
							 </li>
						
						 </div>
				<!-- 
						 <div id="" class="hash_tag w100">
							 <li>#정소민코트</li>
							 <li>#코트</li>
							 <li>#여자코트</li>
							 <li>#베이지코트</li>
							 <li>#이번생은처음이라정소민</li>
				 		</div> -->
				 </div>
			 </c:forEach>
			 <!-- -----이 부분이 반복될 예정 여기까지  -->
			 
			 
			 
			 
 </div>
</div>
</div>
		

				

<%-- <form method="get">
	<input type="text" name="q" /> <input type="submit" value="검색" />
</form>

<table id="table" class="table">
	<thead>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="a" items="${list}">
			<tr>
				<td>${a.id}</td>
				<td><a href="${ctx}/admin/board/advice/${a.id}">${a.title}</a></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd" value="${a.date}" />
				</td>
				<td>${a.writerId}</td>
				<td>${a.hit}</td>
				<td></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<ul>
	<c:set var="page" value="${param.p}" />
	<c:set var="startNum" value="${page-((page-1)%5)}" />
	<c:set var="lastNum"
		value="${fn:substringBefore((count%10 == 0 ? count/10 : count/10 +1),'.')}" />

	<c:if test="${startNum != 1}">
		<a href="?p=${startNum != 1}">이전</a>
	</c:if>

	<c:forEach var="i" begin="0" end="4">
		<!-- ${lastNum-1} -->

		<c:set var="strong" value="" />
		<c:if test="${page == startNum+i }">
			<c:set var="strong" value="text-strong" />
		</c:if>

		<c:if test="${startNum+i <= lastNum}">
			<li><a class="${strong}"
				href="${ctx}/admin/board/advice/?p=${startNum+i}">${startNum+i}</a></li>
		</c:if>

		<!-- 	목록이 더이상 없으면 하이퍼링크 지움 -->
		<c:if test="${startNum+i > lastNum}">
			<li>${startNum+i}</li>
		</c:if>

	</c:forEach>

	<c:if test="${lastNum >= startNum+5 }">
		<a href="?p=${startNum+5}">다음</a>
	</c:if>
</ul>

<div>
	<a href="${ctx}/admin/board/advice/reg">추가하기</a>
</div> --%>

</main>