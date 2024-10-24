<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      var chartData =[['Writer', 'Count per Writer']];
    
      fetch('countByWriter.do')
      	.then(resolve => resolve.json())
      	.then(result => {
      		console.log(result);
      		result.forEach((row) => {
      			chartData.push([row.member_name, row.count]);
      		});
      		google.charts.load('current', {'packages':['corechart']});
            google.charts.setOnLoadCallback(drawChart);
      	})
      	.catch(err => console.log(err));
      
      

      function drawChart() {
    	console.log(chartData);
        var data = google.visualization.arrayToDataTable(chartData
       /*
        [
          ['Task', 'Hours per Day'],
          ['Work',     1],
          ['Eat',      2],
          ['Commute',  2],
          ['Watch TV', 2],
          ['Sleep',    8],
          ['test',    1]
        ]
        */
        );

        var options = {
          title: '\'사용자별 게시글 현황\''
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
      }
    </script>
  </head>
  <body>
    <div id="piechart" style="width: 900px; height: 500px;"></div>
  </body>
</html>
