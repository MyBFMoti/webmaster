/**
 * reply.js
 * replyService 생성했던 메소드 활용
 */
svc.showMsg('Hello');
svc.rlist(93	//bno
	, function(result) {
		//console.log(result);
		
		let fields = ['replyNo','reply','replyer'];
		//let fields = ['replyNo','reply','replyer','boardNo','replyDate'];
		for(let i=0; i< result.length; i++) {
			let obj = result[i];
			console.log(obj);
			let tr =document.createElement("tr");
			for(let j=0; j<fields.length; j++){
				let td =document.createElement("td");
				td.innerHTML = result[i][fields[j]];
				tr.appendChild(td);
				
			}
			document.querySelector("#replyList tbody").appendChild(tr);
		}
		
		
	}	//successFnc
	, function(err) {
		console.log('요기', err);
	}	//errorFnc
);