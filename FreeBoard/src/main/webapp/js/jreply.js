/**
 * jreply.js
 * 
 */
console.log('start');
//jquert 방식 Ajax호출

$.ajax('replyList.do?bno=' + bno + '&page=1')
.done(function(result) {
	console.log(result);	//[{}...{}]
	//출력 이벤트
	result.forEach((item) => {
		$('<li />').append(
			$('<span />').addClass('col-sm-2').text(item.replyNo),	//글번호
			$('<span />').addClass('col-sm-5').text(item.reply),	//댓글
			$('<span />').addClass('col-sm-2').text(item.replyer),	//작성자
			$('<span />').addClass('col-sm-2').append($('<button>삭제</button>').addClass('btn btn-danger'))	//작성자
		)
		  .appendTo($('div.content ul'));
	});
	
})
.fail(function(err){
	console.log(err);
})

//삭제 이벤트
$('div.content ul').on('click', 'button', function(e){
	console.log($(e.target).parent().parent().find('span:eq(0)').text());
	let rno = $(e.target).parent().parent().find('span:eq(0)').text();		//댓글번호(버튼>span>li의 span 중 첫번째 값(글번호))
	$.ajax({
		url:'removeReply.do',
		data: {rno: rno},
		method:'get',
		dataType: 'json'			//(문자열 => 자바스크립트 객체)
	})
		.done(function (result){
			if(result.retCode == 'OK'){
				//$(e.target).parent().parent().remove();
				$(e.target).closest('li').remove();//화면상 삭제
			}
		})
		.fail(function(err) {
			console.log(err);
		})
		
});			//삭제

$("div.head").on('click',' button', function(e){
	console.log("추가 버튼");
	let bno = $("div.container-fluid table tbody tr:eq(0) td:eq(0)").text();
	let reply = $(e.target).parent().find('input').val();
	if(!reply | !logId){
		alert("값을 입력해주세요");
		return;
	}
	console.log(reply);
	console.log(bno);
	console.log(logId);
	
	$.ajax({
		url:'addReply.do',
		data:{bno: bno, reply: reply, replyer:logId},
		method:'post',
		dataType: 'json'
	})
	.done(function (result){
			if(result.retCode == 'OK'){			//db로 보내는 것을 성공 했다면
				let item = result.retVal;
				$('<li />').append(
					$('<span />').addClass('col-sm-2').text(item.replyNo),	//글번호
					$('<span />').addClass('col-sm-5').text(item.reply),	//댓글
					$('<span />').addClass('col-sm-2').text(item.replyer),	//작성자
					$('<span />').addClass('col-sm-2').append($('<button>삭제</button>').addClass('btn btn-danger'))
				)
			.insertAfter($('div.content ul li:eq(0)'));
			}
				
		})
		.fail(function(err) {
			console.log(err);
		})
	
	
})
	
	
