/*
 *basic.js
 */
console.log("basic.js");

let name = "홍길동";
let age = 20;
let obj= {name: "홍길동"
		 ,age:20
		 ,showInfo: function() {
			 return this.name + " - " + this.age;
		 }}
		 
console.log(obj.name);
console.log(obj['age']);
console.log(obj.showInfo());

//DOM(Document Object Model, 문서 객체 모델)
//<li>
let li = document.createElement("li");
li.innerText = 'Cherry';
console.log(li);

let ul = document.querySelector('#show ul'); //css의 선택자

ul.appendChild(li);
console.log(ul);		//<ul><li>Apple</li><li>Banana</li><li>Cherry</li></ul>

document.querySelectorAll('#show ul li')	//[li, li, li]
	.forEach(function(fruit) {
		fruit.style.color='red';
		fruit.addEventListener('click', function(e){
			fruit.remove();
		});
		console.log(fruit);
	})


/*
let tr = document.createElement("tr");			//<tr></tr>

let td = document.createElement("th");
td.innerText = '이름 ';
tr.appendChild(td);								//<tr><th>이름 </th></tr>


td = document.createElement("td");
td.innerText = '홍길동';
tr.appendChild(td);								//<tr><th>이름 </th><td>홍길동</td></tr>

console.log(tr);

let tbody = document.querySelector('#show table tbody');	//<tbody><tr><th>이름 </th><td>홍길동</td></tr> </tbody>
tbody.appendChild(tr);			
*/

let data = [obj];
data.push({name: "박민수", age:"22"});
data.push({name: "송민혁", age:"25"});
//DOM
//data 배열에 있는 정보를 출력
console.log(data[0]);
console.log(data[1])
console.log(data[2]);



//tr>th+td를 생성 appendChile()실행
makeList();
function makeList(){
	document.querySelector('#show table:nth-of-type(2)').className = 'table';
	
	for(let i=0; i< data.length; i++){
		//tr 생성
		let tr = document.createElement("tr");		
		//td 생성
		let td = document.createElement("td");
		
		td.innerText = data[i]['name'];
		tr.appendChild(td);	
		
		td = document.createElement("td");
		td.innerText = data[i]['age'];
		tr.appendChild(td);	
		
		document.querySelector('#show table:nth-of-type(2) tbody').appendChild(tr);	
	}
}

				




