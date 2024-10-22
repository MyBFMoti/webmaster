/**
 * 
 * members.js
 * 
 * jsp => req.getDispatch...("borad/boardList.tiles").forward(req,resp])
 * json =>json데이터 활용 페이지 그리기
 * 
 * 
 * 
 * 
 * 
 * 
 */


//json형태의 회원목록을 출력하는 데이터
//"등록" 버튼에 이벤트 추가
document.querySelector("#addBtn").addEventListener('click', function(e) {
	let id = document.querySelector('#mid').value;
	let name = document.querySelector('#mname').value;
	//let password = document.querySelector('#pw').value;
	let phone = document.querySelector('#mphone').value;

	fetch('addMemberJson.do?id='+id+'&name='+name+'&phone='+phone)
	.then(resolve => resolve.json())
	.then(result => {
		console.log(result);
		if(result.retCode == 'OK'){
			let tr = makeRow({memberId: id, memberName: name, phone: phone});
			document.querySelector("#show tbody").appendChild(tr);
		}else if(result.retCode == 'FAIL') {
			alert("처리중 오류 발생");
		}
	})
	.catch(err => {console.log(err)})
})




//1. 목록 출력
 fetch("memberJson.do")
 	.then(function(resolve) {
		 return resolve.json();
	 })
	 .then(function(result) {
		 console.log(result);	//{result: 'OK'}
		 makeList(result);
	 })
	 .catch(function(err) {
		 console.log(err);
	 })
	 
	 
	 
	 
	makeList();
	function makeList(obj = []){
		//작성
		for(let i=0; i<obj.length; i++){
			let tr = makeRow(obj[i]);
			document.querySelector('#show tbody').appendChild(tr);
		}
	}
	
	function makeRow(obj = {}) {
		let fields = ['memberId', 'memberName', 'password', 'phone', 'responsibility'];
		let tr = document.createElement('tr');
		tr.setAttribute('data-id', obj.memberId);
		tr.addEventListener('mouseover', function(e){
			//tr.style.backgroundColor='skyblue';
			tr.className='table-info';
		})
		tr.addEventListener('mouseout', function(e){
			//tr.style.backgroundColor= null;
			tr.className= null;
		})
		//td*5생성
		for(let j=0; j<fields.length; j++){
			let td = document.createElement('td');
			td.innerText = obj[fields[j]];		
			tr.appendChild(td);
		}
		let td = document.createElement('td');
		let delButton = document.createElement('button');
		td.appendChild(delButton);
		tr.appendChild(td);
		delButton.innerText = '삭제';
		//delButton.style.backgroundColor = '#FF5675';
		//delButton.style.color = 'white';
		delButton.className = "btn btn-danger"
		delButton.addEventListener('click', deleteRowFnc)
		
		return tr;
	}
	function deleteRowFnc(e) {
		//console.dir(e.target.parentElement.parentElement.firstElementChild.innerText);
		console.dir(e.target.parentElement.parentElement.dataset.id);
		//삭제할 id
		let id = e.target.parentElement.parentElement.dataset.id;
		fetch('removeMemberJson.do?id=' + id)
			.then(resolve => resolve.json())
			.then(result => {
				if(result.retCode == 'OK'){
					alert("삭제 완료(성공)")
					e.target.parentElement.parentElement.remove();
				}else if(result.retCode == 'FAIL'){
					alert("삭제 처리중 에러 발생");
				}
			})
			.catch(err => console.log(err));
		
	}
