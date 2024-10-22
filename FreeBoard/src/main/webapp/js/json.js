/*
 * json.js
 * {name : "홍길동", age:20} => object(객체)
 * {"name": "홍길동", "age": 20} => json object
 * json문자열 => 자바스크립트 객체 = json문자열 
 */

let obj = {name: "홍길동", age: 20};
let json = JSON.stringify(obj);		//object -> string
obj = JSON.parse(json);				//string -> object [타입 변환]] 

//JSP => 페이지 출력
//JSON 데이터 =>  페이지 작성

fetch('js/MOCK_DATA.json')
	.then(function(resolve) {
	console.log(resolve);
	return resolve.json();		//object 변환 반환
	})
	.then(function(result) {
		console.log(result);
		makeList(result);
	})
	

//data.js로 썼을때
//obj = JSON.parse(data);
//console.log(obj);

//obj 배열의 건수 만큼 tr을 생성하고 tbody 하위요소에 추가하는 함수
function makeList(obj = []){
	let fields = ['id', 'first_name', 'last_name', 'email', 'salary'];
	for(let i=0; i<obj.length; i++){
		let tr = document.createElement('tr');
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
			td.innerText = obj[i][fields[j]];		//obj[i].name, obj[i]['name']...
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
		delButton.addEventListener('click', function(e){
			//tr.remove();
			this.parentElement.parentElement.remove();
			
		})
		document.querySelector("#show tbody").appendChild(tr);
	}
	
}

//함수는 호출해야 실행
//makeList();