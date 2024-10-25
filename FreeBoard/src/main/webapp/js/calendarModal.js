/**
 * calendarModal.js
 */

function modalShow(arg){
	
	modalArg = arg;		//여러 함주에서 사용할 용도
	
	//body 태그
	let body = document.querySelector("body");
	//console.log(body);
	body.className = 'modal-open';
	body.style.overflow = 'hidden';
	body.style.paddingRight='15px';
	
	let div = document.createElement('div');
	div.className += 'modal-backdrop fade show';
	body.appendChild(div);
	
	
	//modal 태그
	let modal = document.querySelector("#exampleModal");
	modal.classList.add('show');
	modal.setAttribute('aria-modal', true);
	modal.setAttribute('role', 'dialog');
	modal.removeAttribute('aria-hidden');
	modal.style.display = 'block';
	//body.style.display = 'block';
	
	start.value = modalArg.startStr;
	end.value = modalArg.endStr;

}

//modal 닫기
function modalClose() {
	//body 속성
	let body = document.querySelector("body");
	body.className = '';
	body.style= '';
	//div 속성
	let div = document.querySelector(".modal.fade.show");
	//console.log(div);
	div.style = 'none';
	div.removeAttribute('aria-modal', 'role', 'dialog');
	div.setAttribute('aria-hidden', true);
	
	
	document.getElementById('title').value = '';
	//안해도 클릭할때마다 갱신 됨
	document.getElementById('start').value ='';
	document.getElementById('end').value ='';
	
	//back-drop 속성 [이 3개를 변경(제거)]
	let backdropDiv = document.querySelector(".modal-backdrop");
	backdropDiv.remove();
}

function modalSave() {
	
	
	//title, startStr, endStr
	//let title = document.querySelector("#title").value;
	let title = document.getElementById('title').value;
	let startStr =document.getElementById('start').value;
	let endStr = document.getElementById('end').value;
	
	if(title.replace(/\s/g, "") == ""){
		alert("타이틀 공백");
	}
	//console.log(title, startStr, endStr);
	
	
	else{
	fetch("addEvent.do?job=add&title=" + title + "&start=" + startStr + "&end=" + endStr)
         .then(resolve=>resolve.json())
         .then(result => {
         	console.log(result);
         		
         	if(result.retCode == 'OK') {
         		//화면에 출력
         	        calendar.addEvent({
         	          title: title,
         	          start: modalArg.start,
         	          end: modalArg.end,
         	          allDay: modalArg.allDay
         	        })	//화면출력
         	    
         	} else if(result.retCode == 'FAIL') {
         		alert('등록 에러');
         	} 
         })
         .catch(err => console.log(err));
     }
     modalClose();
    
         
}

//추가 도중 시작일 및 끝나는 일 조정
function startChange(event){
	console.log(event);
	modalArg.start = new Date(event.target.value);			//2024-10-21
}

function endChange(event){
	console.log(event);
	modalArg.end = new Date(event.target.value);
}