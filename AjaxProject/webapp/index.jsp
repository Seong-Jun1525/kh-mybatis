<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajax Project</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<body>
	<h1>Ajax Project</h1>
	<p>
		<i>Asynchronous JavaScript And XML</i>의 약자 <br>
		서버로부터 데이터를 가져와 전체 페이지를 바꾸지 않고 일부만 로드하여 변경하도록 하는 기법<br>
		
		기존에 a태그를 사용하거나, form 태그의 submit 요청 방식은 <b>동기식</b> 요청<br>
		=> 응답 페이지가 전달되어야 확인 가능
	</p>
	
	<h3>* 동기식 vs 비동기식</h3>
	- 동기식
	<ul>
		<li>요청 처리 후 해당 응답 페이지가 전달된 후에 다음 작업 가능</li>
		<li>서버에 요청한 결과에 따라 응답이 지연되면 무작정 기다려야 함<small>(지연 시 화면이 하얗게 표시됨)</small></li>
		<li>전체 페이지를 응답하기 때문에 화면에 깜빡거림 현상이 있음</li>
	</ul>
	
	- 비동기식(ajax)
	<h4>장점</h4>
	<ol>
		<li>현재 페이지를 그대로 유지하면서 추가적인 작업(요청)이 있는 경우 사용</li>
		<li>요청 시 다른 페이지로 변경되지 않음 => 페이지의 깜빡거림 없음</li>
		<li><strong>요청을 하고 응답(데이터)가 돌아올 때 까지 기다리지 않고 다른 작업을 수행할 수 있음</strong></li>
		<li>ex) 검색어 자동 완성 기능, 아이디 중복체크, 찜, 추천, 댓글</li>
	</ol>
	
	<h3>단점</h3>
	<ol>
		<li>현재 페이지에서 리소스가 지속적으로 쌓임 => 페이지가 느려질 수 있음</li>
		<li>오류 발생 시 디버깅이 어려움</li>
		<li>요청 처리 후 응답된 데이터를 가지고 새로운 요소(태그)를 만들어서 화면에 표시해야 함 => DOM 요소를 만드는 구문을 잘 익혀둬야 함</li>
	</ol>
	
	<hr>
	
	<h3>AJAX 구현 방식</h3>
	<ul>
		<li>순수 자바스크립트 방식(브라우저마다 방식을 다르게 해야할 수 있음)</li>
		<li>jQuery 방식(코드가 간결하고 사용하기 쉬움)</li>
	</ul>
	
	<pre>
		* jQuery 방식으로 AJAX 통신
		
		$.ajax({
			속성명: 속성값,
			속성명: 속성값,
			...
		});
		
		- 주요 속성
			+ url: 요청 주소
			+ type | method: 요청 전송 방식(get/post/...)
			+ data: 요청 시 전달할 값
			
			+ success: 통신 성공 시 실행할 함수 정의
			+ error: 통신 실패 시 실행할 함수 정의
			+ complete: 통신 성공/실패 상관없이 무조건 실행할 함수 정의
	</pre>
	
	<hr>
	
	<h3>jQuery 방식을 이용한 AJAX 테스트</h3>
	<h4>1. get 방식으로 서버에 요청 및 응답받기</h4>
	<input type="text" id="data1" />
	<button id="btn1">전송</button>
	<br>
	응답 결과 : <span id="result1">응답없음</span>
	
	<script>
		// 전송이 클릭되었을 때 /jqAjax1.do 입력된 값을 전송
		$("#btn1").click(function() {
			// 기존 방식 (동기식)
			// location.href = "jqAjax1.do?data=" + $("#btn1").val();
			
			// 비동기 방식
			$.ajax({
				url: "jqAjax1.do",
				method: "GET",
				data: {
					data: $("#data1").val()
				},
				success: function(result) {
					console.log("통신 성공");
					console.log(result);
					
					$("#result1").text(result);
				},
				error: function(error) {
					console.log("통신 실패");
					console.log(error);
				},
				complete: function() {
					console.log("통신 완료");
				}
			});
		});
		
	</script>
	
	<hr>
	<h4>2. post 방식으로 서버에 요청 및 응답받기</h4>
	이름 : <input type="text" id="name" />
	나이 : <input type="number" id="age" value="20" /><br>
	<button id="btn2">전송</button>
	<br>
	응답결과 : <div id="result2">응답 없음</div>
	<script>
		// 전송 클릭되었을 때 입력된 값들을
		// jqAjax2.do로 전송(post)
		$("#btn2").click(function() {
			$.ajax({
				url: "jqAjax2.do",
				method: "POST",
				data: {
					name: $("#name").val(),
					age: $("#age").val()
				},
				
				success: function(result) {
					console.log("통신 성공");
					console.log(result);
					
					$("#result2").text("");
					
					// 배열 형태일 경우
					/*
					for(let r of result) {
						$("#result2").append(
							"<p>" + r + "</p>"		
						);
					}
					*/
					
					// 일반 객체일 경우
					console.log(result.name);
					console.log(result['age']);
					
					$("#result2").append(
						"<ul>" 
							+ "<li>" + result.name + "</li>"
							+ "<li>" + result.age + "</li>" 
						+ "</ul>"		
					);
				},
				error: function(err) {
					console.log("통신실패");
				},
				complete: function() {
					console.log("통신완료");
				}
			});
		});
	</script>
	
	<hr>
	<h4>3. VO(DTO) 객체로 응답 받기</h4>
	회원번호 : <input type="number" id="userNo" />
	<button onclick="searchUser();">조회</button>
	<div id="result3"></div>
	
	<script>
		// 조회 클릭되었을 때 입력된 회원 번호를 jqAjax3.do로 전송(get)
		function searchUser() {
			console.log("클릭");
			$.ajax({
				url: "jqAjax3.do",
				data: {
					userNo: $("#userNo").val()
				},
				method: "GET",
				success: function(user) {
					console.log("통신성공");
					console.log(user);
					$("#result3").append(
						"<ul>" 
							+ "<li>" + user.userNo + "</li>"
							+ "<li>" + user.userId + "</li>" 
							+ "<li>" + user.userName + "</li>"
							+ "<li>" + user.userAddress + "</li>" 
						+ "</ul>"		
					);
				},
				error: function(err) {
					console.log("통신 실패");
					console.log(err);
				}
			});
		}
	</script>
	
	<hr>
	<h4>4. 객체 배열로 응답받기</h4>
	<button onclick="selectList();">조회</button>
	<div id="result4">
		<table border="1">
			<thead>
				<tr>
					<th>NO</th>
					<th>아이디</th>
					<th>이름</th>
					<th>주소</th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
	</div>
	
	<script>
		function selectList() {
			$.ajax({
				url: 'jqAjax4.do',
				success: function(list) {
					console.log(list);
					
					let node;
					
					for(let l of list) {
						node += 
							"<tr>" 
								+ "<td>" + l.userNo + "</td>"
								+ "<td>" + l.userId + "</td>"
								+ "<td>" + l.userName + "</td>"
								+ "<td>" + l.userAddress + "</td>"
							+ "</tr>"
					}
					
					$("#result4 tbody").html(node);
				},
				error: function(err) {
					console.log(err);
				}
			});
		}
	</script>
</body>
</html>