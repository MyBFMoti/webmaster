/**
 * ajax1.js
 * Asynchronous Javascript And Xml(비동기 자바스크립트와 Xml)
 */
//동기방식:한 작업이 끝나야 다음 작업이 시작되는 순차적인 처리 방식
/*
setTimeout(function() {
	console.log("1");
	setTimeout(function() {
	console.log("2");
	setTimeout(function() {
	console.log("3");
	
	
}, 1000);	//1초후 함수 실행
	
}, 1000);	//1초후 함수 실행
	
}, 1000);	//1초후 함수 실행


//비동기 방식:한 작업이 끝나기를 기다리지 않고 다음 작업을 바로 시작할 수 있는 병렬적 처리 방식(동시에 실행하고 먼저 완료되는 것부터 출력)
setTimeout(function() {
	console.log("1");
}, 1000);	//1초후 함수 실행

setTimeout(function() {
	console.log("2");
}, 1000);	//1초후 함수 실행

setTimeout(function() {
	console.log("3");
}, 1000);	//1초후 함수 실행

*/



let xhtp = new XMLHttpRequest();		//대표적인 비동기방식처리
xhtp.open('get', 'memberJson.do');
xhtp.send();							//서버상 resource

let data = [];
xhtp.onload = function() {
	let obj = JSON.parse(xhtp.responseText);
	console.log(obj);
	data = obj;
	console.log('1', data);
	for(let i=0; i<data.length; i++){
		console.log(data[i]);
	}
}

console.log('2', data);					//비동기 방식처리 떄문에 먼저 완료되는 '2'가 '1'보다 먼저 실행
