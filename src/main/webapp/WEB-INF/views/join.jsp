<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<link rel="stylesheet" type="text/css" href="resource/css/login.css">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
$(function(){
	$(".phone-number-check").on('keydown', function(e){
		// 숫자만 입력받기
       	var trans_num = $(this).val().replace(/-/gi,'');
		var k = e.keyCode;
				
		if(trans_num.length >= 11 && ((k >= 48 && k <=126) || (k >= 12592 && k <= 12687 || k==32 || k==229 || (k>=45032 && k<=55203)) ))
		{
  	    	e.preventDefault();
		}
    }).on('blur', function(){ // 포커스를 잃었을때 실행합니다.
        if($(this).val() == '') return;

        // 기존 번호에서 - 를 삭제합니다.
        var trans_num = $(this).val().replace(/-/gi,'');
      
        // 입력값이 있을때만 실행합니다.
        if(trans_num != null && trans_num != '')
        {
            // 총 핸드폰 자리수는 11글자이거나, 10자여야 합니다.
            if(trans_num.length==11 || trans_num.length==10) 
            {   
                // 유효성 체크
                var regExp_ctn = /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})([0-9]{3,4})([0-9]{4})$/;
                if(regExp_ctn.test(trans_num))
                {
                    // 유효성 체크에 성공하면 하이픈을 넣고 값을 바꿔줍니다.
                    trans_num = trans_num.replace(/^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-?([0-9]{3,4})-?([0-9]{4})$/, "$1-$2-$3");                  
                    $(this).val(trans_num);
                   	alert($(".phone-number-check").prop('value'));
                }
                else
                {
                    alert("유효하지 않은 전화번호 입니다.");
                    $(this).val("");
                    $(this).focus();
                }
            }
            else 
            {
                alert("유효하지 않은 전화번호 입니다.");
                $(this).val("");
                $(this).focus();
            }
      }
  });  
});

function doOpenCheck(chk){
    var obj = document.getElementsByName("gender");
    for(var i=0; i<obj.length; i++){
        if(obj[i] != chk){
            obj[i].checked = false;
        }
    }
}
</script>

<main>
<div>
	<form action="${path}/join" method="post">
		<label>아이디 : </label><input type="text" name="id" /><br /> 
		<label>비밀번호 : </label><input type="text" name="pw" /><br />
		<label>이름 : </label><input type="text" name="name" /><br /> 
		<label>성별 : </label><input type="checkbox" name="gender" value="여" onclick="doOpenCheck(this)">여
							<input type="checkbox" name="gender" value="남" onclick="doOpenCheck(this)">남<br />
		<label>나이 : </label><select name="age">
								<option value="10" selected="selected">10대</option>
								<option value="20">20대</option>
								<option value="30">30대</option>
								<option value="40">40대</option>
								<option value="50">50대</option>
								<option value="60">60대</option>
							</select><br />
		<label>전화번호 : </label><input class="phone-number-check" type="text" name="phone"/><br />
		<label>키 : </label><input type="text" name="weight" />cm<br /> 
		<label>무게 : </label><input type="text" name="height" />kg<br />  
		
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
		<input type="submit" value="취소" />
		<input type="submit" value="회원가입" />
	</form>
</div>
</main>