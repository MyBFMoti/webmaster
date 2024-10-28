<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
	<meta charset="UTF-8">
	<title>jQuery Element Access</title>
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script>
	/*
		$(function() {
			$("button").on("click", function() {
				// id가 "list"인 요소의 자손 요소 중에서 <li>요소를 모두 선택한 후에,
				// 그 중에서 두 번째 요소의 값을 설정함.
				$("#list").find("li").eq(1).html("두 번째 아이템을 선택했어요!!");
			});
		});
	*/
		//javascript로 작성
		$(function() {
			document.querySelector('button').addEventListener('click', function(e){
				let list = document.querySelector('#list');
				list.querySelectorAll('li').forEach((item, idx) => {
					if(idx == 1){
						item.innerHTML = "두 번째 아이템을 선택했어요!!";
					}
				})
			})
		})
	</script>
</head>

<body>

	<h1>메소드 체이닝</h1>
	<ul id="list">
		<li>첫 번째 아이템이에요!</li>
		<li>두 번째 아이템이에요!</li>
		<li>세 번째 아이템이에요!</li>
	</ul>
	<button>텍스트 변경</button>
	
</body>

</html>