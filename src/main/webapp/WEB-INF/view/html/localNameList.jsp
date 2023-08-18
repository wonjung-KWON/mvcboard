de<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
 <script>
	$(document).ready(function(){
		
		const x = [];
		const y = [];
		
		//동기호출로 x,y값을 셋팅
		$.ajax({
			async : false, // true(비동기), false(동기) , 디폴트값은 트루
			url : '/rest/localNameList',
			type : 'get',
			success : function(model){
				//원래는 Backend Model 를 Frontend Model로변경
				// model  -> {'model':[{localName:'부산'},{cnt : 10}]}
				model.forEach(function(item, index) { 
					$('#target').append('<tr>');
					$('#target').append('<td>'+item.localName+'</td>');
					$('#target').append('<td>'+item.cnt+'</td>');
					$('#target').append('</tr>');
					
					//chart에 x축 배열 반복문으로 값 넣어주기
					x.push(item.localName);
					y.push(item.cnt);
				});
			}
		});
		// 차트부분
		new Chart("target2", {
		  type: "bar",
		  data: {
		    labels: x,
		    datasets: [{
		      //backgroundColor: barColors,
		      data: y
		    }]
		  },
		//  options: {...}
		});
		
	});
</script>
</head>
<body>
 	<h1>AJax API사용으로 localNameList 가져오기</h1>
 	
 	<table>
 		<thead>
 		 	<tr>
 		 		<th>지역이름</th>
 		 		<th>게시글 수</th>
 		 	</tr>
 		</thead>
 		
 		<tbody id="target">
 			
 		</tbody>
 	</table>
 	
 	<canvas id="target2" style="width:100%;max-width:700px"></canvas>
</body>
</html>