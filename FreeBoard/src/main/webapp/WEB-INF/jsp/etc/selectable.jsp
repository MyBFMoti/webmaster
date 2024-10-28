<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<script src='./dist/index.global.js'></script>


<script>



  let modalArg = null;
  let calendar = null;
  document.addEventListener('DOMContentLoaded', async function() {
	  
    var calendarEl = document.getElementById('calendar');
    //Ajax 호출
    //new Promise()(function(){}, function(){})
    //프라미스 객체가 반환될 때 await 수행 코드 -> 그다음 코드 실행
    
    var eventData = [];
    
   
    
    let resolve = await fetch('event.do?job=list');								//fectch('event.do')
    let result = await resolve.json();									//	.then(resolve => resolve.json())
    eventData = result;													//	.then(result => {
  																		//	eventData = result;
  																		//	console.log(eventData);
  																		//})							//end of .then
  	
    
  																		//.catch(err => console.log(err));

 	 calendar = new FullCalendar.Calendar(calendarEl, {
     headerToolbar: {
       left: 'prev,next today',
       center: 'title',
       right: 'dayGridMonth,timeGridWeek,timeGridDay'
     },
     initialDate: '2024-10-24',
     navLinks: true, // can click day/week names to navigate views
     selectable: true,
     selectMirror: true,
     
     //등록
     select: function(arg) {
       //var title = prompt('Event Title:');
       //if (title) {
       modalShow(arg);
       console.log(arg);
       //  console.log(arg);		//start, end
       //  calendar.addEvent({
       //      title: title,
       //      start: arg.start,
       //      end: arg.end,
       //      allDay: arg.allDay
       //    })
           

       //}
       calendar.unselect();
       
     },
     //삭제
     eventClick: function(arg) {
    
       if (confirm('Are you sure you want to delete this event?')) {
    	   //console.log(arg);
           console.log(arg.event._def.title);
           arg.event.remove();
           
    	   let title = arg.event._def.title;
           fetch("removeEvent.do?job=remove&title=" + title)
           	.then(resolve => resolve.json())
           	.then(result => {
           		if(result.retCode == 'OK') {
	    	   		arg.event.remove()
           		} else if(result.retCode == 'FAIL') {
     				alert('등록 에러');
     			}
           	})
           	.catch(err => console.log(err));
         
       }
      
     },
     editable: true,
     dayMaxEvents: true, // allow "more" link when too many events
     events: eventData
     
 	  });
 		
 	  calendar.render();
  });
  
  
  

 
</script>


<style>

  body {
    margin: 40px 10px;
    padding: 0;
    font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
    font-size: 14px;
  }

  #calendar {
    max-width: 1100px;
    margin: 0 auto;
  }

</style>
</head>
<body>

  <div id='calendar'></div>
  
  <!-- 모달창 열기 -->
  <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
    Launch demo modal
  </button>
   <!-- Modal -->
  <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" onclick="modalClose()"></button>
        </div>
        <div class="modal-body">
      	  <!-- title, startStr, endStr -->
      	  타이틀: <input type="text" id="title"><br>
      	  시작일시: <input type="date" onchange="startChange(event)" id="start"><br>
      	  종료일시: <input type="date" onchange="endChange(event)" id="end"><br>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" onclick="modalClose()">Close</button>
          <button type="button" class="btn btn-primary" onclick="modalSave()">Save changes</button>
        </div>
      </div>
    </div>
  </div>

</body>
<script src="js/calendarModal.js"></script>
</html>