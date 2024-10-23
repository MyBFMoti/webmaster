/**
 * reply.js
 * replyService 생성했던 메소드 활용
 */
let page =1;
//댓글 등록 버튼

document.querySelector('#addReply').addEventListener('click', addReplyHandleFnc);

//댓글 등록 이벤트(등록후 리스트에 댓글 추가)
function addReplyHandleFnc(e) {
	let reply = document.querySelector('#reply').value;
	if(!reply){
		alert("댓글을 입력해주세요");
		return;
	}
	
	svc.addReply({bno, reply, replyer: logId},
		result => {
			console.log(result);							//OK:화면에 한줄 추가 /  FAIL: "에러발생"
			document.querySelector('#reply').value =null;	//댓글 칸 비우기
			if(result.retCode == "OK") {
				//let template = makeLi(result.retVal);
				//document.querySelector(".reply ul li").after(template);
				page = 1;															//페이지를 1로 옮기고 리스트 출력, 댓글 수도 다시 계산
				showList();
				svc.getReplyCount(bno, createPageList, err=>console.log(err));
			}else if(result.retCode == "FAIL"){
				alert("등록 중 에러 발생");
			}
		},
		err =>{
			console.log(err);
		}
	)
}


// .pagination a 클릭 이벤트
function linkMove(){
	document.querySelectorAll('nav ul.pagination a').forEach(function(aTag) {
		aTag.addEventListener('click', function(e){
			e.preventDefault();			//이동차단
			console.log(aTag.innerHTML);
			page= aTag.dataset.page;	//<a data-page="2">2</a>
			showList();					//댓글 목록을 보여주고
			//createPageList();			//페이징 목록을 보여준다
			svc.getReplyCount(bno, createPageList, err =>console.log(err));	//페이징 목록을 보여준다(댓글 총수에 따라 달라지면서)
		})
	})
}

//댓글 페이지 리스트를 출력하는 함수
svc.getReplyCount(bno, createPageList, err =>console.log(err));		//댓글 수 구하기(totalCnt)

//createPageList();
function createPageList(result) {	//result=댓글 수				//page=2 
	console.log(result.totalCount);
	let totalCnt = result.totalCount;
	let startPage, endPage, realEnd;
	let prev, next;
	
	endPage = Math.ceil(page / 5) * 5;		//5 page
	startPage = endPage - 4;				//1 page
	realEnd = Math.ceil(totalCnt / 5);		//7 page
	endPage = endPage > realEnd ? realEnd : endPage;		//endPage가 늘어날 수 있기 때문에 추가
	
	
	prev = startPage >1;					//false
	next = endPage < realEnd;				//true
	
	//페이지 리스트 출력
	let list = '';
	/*
	<li class="page-item">
	      <a class="page-link" href="#" aria-label="Previous">
	        <span aria-hidden="true">&laquo;</span>
	      </a>
	    </li>
	 */
	list += '<li class="page-item">';
	if(prev)
		list += '<a class="page-link" href="#" aria-label="Previous" data-page="'+(startPage-1)+'">&laquo;</a>';
	else
		list += '<span class="page-link disabled" aria-label="Previous">&laquo</span>';
	
	list += '    </li>';
	
	//<li class="page-item"><a class="page-link" href="#">1</a></li>
	for(let p = startPage; p<=endPage; p++){
		list +='<li class="page-item"><a class="page-link" href="#" data-page="'+p+'">'+ p +'</a></li>';
	}
	/*
	  <li class="page-item">
	      <a class="page-link" href="#" aria-label="Next">
	        <span aria-hidden="true">&raquo;</span>
	      </a>
	    </li> 
	 */
	list += '<li class="page-item">';
	if(next)
		list += '<a class="page-link" href="#" aria-label="Next" data-page="'+(endPage+1)+'">&raquo;</a>';
	else
		list += '<span class="page-link disabled"  aria-label="Next">&raquo</span>';
	list += '    </li>';

	
	document.querySelector('nav ul.pagination').innerHTML=list;
	
	linkMove();
}


showList();
//댓글 리스트 출력하는 함수
function showList() {
	//출력목록을 화면에서 지운 다음
	document.querySelectorAll('div.reply div.content li').forEach((li, idx) =>{
		if(idx >0) li.remove();
	})
		
	//댓글 리스트 출력
	svc.rlist({bno, page} //bno
		,//successFnc
		function(result){
			//console.log(result)

			for(let i=0; i<result.length; i++){
				let template = makeLi(result[i]);
				document.querySelector(".reply ul").appendChild(template);
			}
			
	
		}
		,//errorFnc
		function(err) {
			console.log('요기', err);
		}
	)
}


//댓글 출력함수
function makeLi(result={}) {
	
			let template = document.querySelector(".reply ul li").cloneNode(true);
			template.setAttribute('data-id', result.replyNo);
			template.querySelector('span').innerText =result['replyNo'];
			template.querySelector('span:nth-of-type(2)').innerText =result['reply'];
			template.querySelector('span:nth-of-type(3)').innerText =result['replyer'];
			let delBtn= template.querySelector('span:nth-of-type(4)');
			delBtn.innerHTML ="<button class=\"btn btn-danger\">삭제</button>";
			delBtn.addEventListener('click', deleteRow);
			console.log(template);
			return template;
}

//댓글 삭제하는 함수
function deleteRow(e) {
	let rno =e.target.parentElement.parentElement.firstElementChild.innerText;
	console.log(rno);
	//삭제 기능 호출
	svc.removeReply(rno, 		//삭제할 댓글번호
	function(result){
		if(result.retCode == "OK") {
			alert("정상 처리");
			//e.target.parentElement.parentElement.remove();
			showList();
			svc.getReplyCount(bno, createPageList, err =>console.log(err));	//페이징 목록을 보여준다(댓글 총수에 따라 달라지면서)
			
		}else if(result.retCode == "FAIL"){
			alert('처리중 예외');
		}else {
			alert('알 수 없는 코드');
		}
		
	},			//정상처리 실행함수
	
	//function(err){
	//	console.log(err);
	//}
	
	// or
	
	err => console.log(err)			//예외발생 실행함수
	)
}









/*
function makeList(result) {
		//console.log(result);
		//let fields = ['replyNo','reply','replyer','boardNo','replyDate'];
		for(let i=0; i< result.length; i++) {
			console.log(result[i]);
			let tr = makeRow(result[i]);
			document.querySelector('#replyList tbody').appendChild(tr);
		}	
}	
*/

/*
function makeRow(obj = {}){
	let fields= ['replyNo','reply','replyer'];
	let tr =document.createElement("tr");
	for(let j=0; j<fields.length; j++){
				let td =document.createElement("td");
				td.innerHTML = obj[fields[j]];
				tr.appendChild(td);
	}
	tr.setAttribute('data-id', obj.replyNo);
	return tr;
}
*/