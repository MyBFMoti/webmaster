/**
 * 
 * exe1.js
 * 
 * 
 */
function deleteBtn(){
	let inputName = document.querySelector("#userValue").value;
	let allSpan = document.querySelectorAll("div .container span");
	//if(inputName.replace(/\s/g, "") != ""){
		//span들
		//console.log(document.querySelectorAll("div .container span")[0]);
		//console.log(spanS[0].innerHTML);
		//span 찾기
		for(let i of allSpan){
		//for(let i=0; allSpan.length; i++){
			let spanS = i.innerHTML;
			if((inputName)==(spanS)){
				console.log("제거");
				
				i.remove();
				break;
				//allSpan[i].remove();
				//allSpan[i].style = "display:none";
			}
			
		}
	//}else{
	//	alert("공백 입력 감지");
	//}
	
	
}