<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.7.1.js"></script>
<script>
	document.addEventListener('DOMContentLoaded', function(e) {
		//jquery 객체 생성
		$("<ul />")							//document.createElement('ul')을 jquery식으로 생성
		  .append($('<li>사과</li>')			//<ul><li>사과</li></ul>
		  		 ,$('<li />').html('바나나')	//innerHTML = '바나나'
		  		 ,$('<li />').text('복숭아')	//innerTEXT = '복숭아'
		  ).appendTo($('body'));
	})
</script>
</head>
<body>
<!-- webapp/sample.jsp -->

</body>
</html>