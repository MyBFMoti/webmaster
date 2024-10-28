<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.7.1.js"></script>
<script>
	// document.addEventListener('DOMContentLoaded', function(e){})
	
	$(function() {
	    $("body p").on("click", function(e) {        // <p>요소를 모두 선택함
	    //$("body p").on("click", function() {        // <p>요소를 모두 선택함
	    	console.log(e.target);
	    	$(e.target).css("fontSize", "28px");	//누른 p의 span만 선택
	    	$(e.target).css("color", "red");
	    	//$(e.target).$("p span").css("fontSize", "28px");	//누른 p의 span만 선택
	    	
	       // $("span").css("fontSize", "28px");  // <span>요소를 모두 선택함.
	       // $("span").css("color", "red"); 
	    });
	});
	
	//jquery를 안쓰고 만든다면...
	
	//document.addEventListener('DOMContentLoaded', function(e){
	//	document.querySelectorAll('p').forEach(item => {			// <p>요소를 모두 선택함
	//		item.addEventListener('click', function(e) {
	//			//화면상의 <span></span>
	//			//document.querySelectorAll('span').forEach(span => {	// <span>요소를 모두 선택함.
	//			//	span.style.fontSize = '28px';
	//			//})
	//			this.querySelector('span').style.fontSize = '28px';		// 누른 p의 span만 선택
	//		})
	//	})
	//})
	



</script>
</head>
<body>

	<h1>HTML 태그 선택자</h1>

	<p>이제부터 <span>제이쿼리</span>를 다 같이 공부해보죠!!<br>
	마우스로 글씨를 <span>클릭</span>해보세요!!</p>
	
	<p>이제부터 <span>제이쿼리</span>를 다 같이 공부해보죠!!<br>
	마우스로 글씨를 <span>클릭</span>해보세요!!</p>
	
	<p>이제부터 <span>제이쿼리</span>를 다 같이 공부해보죠!!<br>
	마우스로 글씨를 <span>클릭</span>해보세요!!</p>
	
</body>
</html>