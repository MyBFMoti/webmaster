/**
 * replyService.js
 * 메소드: 목록, 등록, 삭제
 * 
 */
const svc = {
	//1.리스트
	rlist(param = {bno, page}, successFnc, errorFnc) {//댓글 목록 출력, (게시글 번호, 성공할 때 작동하는 함수, 에러함수)
	//Ajax호출
		fetch('replyList.do?bno=' + param.bno + '&page=' + param.page)
			.then(resolve => resolve.json())
			.then(successFnc)
			.catch(errorFnc)
	}
//	,
//	showMsg(msg) {
//		
		
//	}
	//2.삭제
	,
	removeReply(rno= 1, successFnc, errorFnc){
		//Ajax호출
		fetch('removeReply.do?rno=' + rno)
			.then(resolve => resolve.json())
			.then(successFnc)
			.catch(errorFnc)
	}
	
	//3.등록
	,
	addReply(param={bno, reply, replyer}, successFnc, errorFnc){
		//Ajax호출
		fetch('addReply.do?bno=' + param.bno + '&reply=' + param.reply + '&replyer=' + param.replyer)
			.then(resolve => resolve.json())
			.then(successFnc)
			.catch(errorFnc)
	}
	
	//4.댓글 카운트
	,
	getReplyCount(bno =1, successFnc, errorFnc){
		//Ajax호출
		fetch('replyCount.do?bno=' + bno)
			.then(resolve => resolve.json())
			.then(successFnc)
			.catch(errorFnc)
	}

}