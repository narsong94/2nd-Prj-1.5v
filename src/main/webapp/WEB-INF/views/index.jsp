<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<link rel="stylesheet" type="text/css" href="resource/css/index.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
$(function() {
	var btn = $(".scroll");
	
	btn.click(function(e) {
		$('html, body').animate({
			scrollTop : $("#main-menu " + $(e.target).attr("id")).offset().top - 71}
		, 400);
	});
});
</script>

<div id="menu" class="fixed">
	<ul>
		<security:authorize access="!hasRole('ROLE_ADMIN') and !hasRole('ROLE_MEMBER')">
			<li><a class="menu-selector" href="${path}/login">Login</a></li>
		</security:authorize>
		<security:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_MEMBER')">
			<li><form action="${path}/logout" method="post">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					<input id="logoutBtn" class="menu-selector" type="submit" value="Logout"/>
				</form>
			</li>
			<security:authorize access="hasRole('ROLE_ADMIN')">
				<li><a class="menu-selector" href="${path}/admin/mypage/index">Mypage</a></li>
			</security:authorize>
			<%-- <security:authorize access="hasRole('ROLE_MEMBER')">
				<li><a class="menu-selector" href="${path}/member/mypage/index">Mypage</a></li>
			</security:authorize> --%>
		</security:authorize>
		<security:authorize access="hasRole('ROLE_ADMIN')">
			<li><a class="menu-selector scroll" id="#info">Info</a></li>
			<li><a class="menu-selector scroll" id="#advice">Advice</a> </li>
		</security:authorize>
		<security:authorize access="hasRole('ROLE_MEMBER')">
			<li><a class="menu-selector scroll" id="#info">Info</a></li>
			<li><a class="menu-selector scroll" id="#advice">Advice</a> </li>
		</security:authorize>
		<security:authorize access="!hasRole('ROLE_ADMIN') and !hasRole('ROLE_MEMBER')">
			<li><a class="menu-selector scroll" id="#info">Info</a></li>
			<li><a class="menu-selector scroll" id="#advice">Advice</a> </li>
		</security:authorize>
		<!-- <li><a class="menu-selector" href="#about">About</a></li>
		<li class="dropdown"><a class="menu-selector" class="dropbtn">Board</a>
			<div class="dropdown-content">
				<a class="scroll" id="#advice">Advice</a> 
				<a class="scroll" id="#info">Info</a> 
			</div></li>  -->
		<li><a class="menu-selector active scroll"
			id="#home">Home</a></li>
	</ul>
</div>

<div id="main-menu">
	<div id="home">
		<h1 style="font-size: 70px; margin: 0; vertical-align: middle;">Which's Clothes</h1>
	</div>
	<div id="advice">
		<!-- <a>패션 고민 해결</a> -->
		<security:authorize access="hasRole('ROLE_ADMIN')">
		<ul>
			<li><a href="${path}/admin/board/advice">advice</a></li>
		</ul>
		</security:authorize>
		<security:authorize access="hasRole('ROLE_MEMBER')">
		<ul>
			<li><a href="${path}/member/board/advice">advice</a></li>
		</ul>
		</security:authorize>
		<security:authorize access="!hasAnyRole('ROLE_MEMBER', 'ROLE_ADMIN')">
		<ul>
			<li><a href="${path}/login">advice</a></li>
		</ul>
		</security:authorize>
	</div>
	<div id="info">
		<!-- <a>요즘 잘나가는 패션 아이템</a> -->
		<security:authorize access="hasRole('ROLE_ADMIN')">
		<ul>
			<li><a href="${path}/admin/board/info">info</a></li>
		</ul>
		</security:authorize>
		<security:authorize access="hasRole('ROLE_MEMBER')">
		<ul>
			<li><a href="${path}/member/board/info">info</a></li>
		</ul>
		</security:authorize>
		<security:authorize access="!hasAnyRole('ROLE_MEMBER', 'ROLE_ADMIN')">
		<ul>
			<li><a href="${path}/login">info</a></li>
		</ul>
		</security:authorize>
	</div>
</div>


<script>
jQuery.fn.letterDrop = function() {
	  var obj = this;
	  
	  var drop = {
	    arr : obj.text().split( '' ),
	    
	    range : {
	      min : 1,
	      max : 9
	    },
	    
	    styles : function() {
	      var dropDelays = '\n', addCSS;
	      
	       for ( i = this.range.min; i <= this.range.max; i++ ) {
	         dropDelays += '.ld' + i + ' { animation-delay: 1.' + i + 's; }\n';  
	       }
	      
	        addCSS = $( '<style>' + dropDelays + '</style>' );
	        $( 'head' ).append( addCSS );
	    },
	    
	    main : function() {
	      var dp = 0;
	      obj.text( '' );
	      
	      $.each( this.arr, function( index, value ) {

	        dp = dp.randomInt( drop.range.min, drop.range.max );
	        
	        if ( value === ' ' )
	          value = '&nbsp';
	        
	          obj.append( '<span class="letterDrop ld' + dp + '">' + value + '</span>' );
	        
	      });
	          
	    }
	  };
	   
	  Number.prototype.randomInt = function ( min, max ) {
	    return Math.floor( Math.random() * ( max - min + 1 ) + min );
	  };
	  
	  
	  // Create styles
	  drop.styles();

	  // Initiate
	  drop.main();
	   
	};




	$( 'h1' ).letterDrop();


</script>