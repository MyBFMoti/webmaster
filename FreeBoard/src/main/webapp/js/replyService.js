/**
 * replyService.js
 * 메소드: 목록, 등록, 삭제
 * 
 */
const svc = {
	rlist(bno = 93, successFnc, errorFnc) {//댓글 목록 출력, (게시글 번호, 성공할 때 작동하는 함수, 에러함수)
		fetch('replyList.do?bno=' + bno)
			.then(resolve => resolve.json())
			.then(successFnc)
			.catch(errorFnc)
	},
	showMsg(msg) {
		console.log(msg);
		
	}
}