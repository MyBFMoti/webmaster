<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <script>
    document.addEventListener('DOMContentLoaded', function (e) {
      createCalender(document.querySelector('#list'));
    })

    // 달력생성함수.
    function createCalender(target) {
      let tr = document.createElement('tr');
      let space = 2;
      let lastDate = 31;
      for (let d = 0; d < 2; d++) {
    	  let td = document.createElement('td');
          //td.innerHTML = d;
          tr.appendChild(td);
      }
      for (let d = 1; d <= lastDate; d++) {
        let td = document.createElement('td');
        td.innerHTML = d;
        if ((d + space) % 7 == 1) { // 일요일.
            td.style.color = 'red';
        }
        if ((d + space) % 7 == 0) { // 토요일.
          td.style.color = 'blue';
        }
        tr.appendChild(td);
        if (d % 7 == 5) {
          document.querySelector('#list').appendChild(tr);
          tr = document.createElement('tr'); // 토요일(7번 td를 생성하면)이 되면 tr을 새롭게 생성.
        }
      }
      target.appendChild(tr);
    }
  </script>
</head>

<body>

  <div id="show">
    <table border="1">
      <thead>
        <tr>
          <th>Sun</th>
          <th>Mon</th>
          <th>Tue</th>
          <th>Wed</th>
          <th>Thr</th>
          <th>Fri</th>
          <th>Sat</th>
        </tr>
      </thead>
      <tbody id="list"></tbody>
    </table>

  </div>

</body>
</html>