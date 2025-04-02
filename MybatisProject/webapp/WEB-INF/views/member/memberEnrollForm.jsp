<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="sweetalert2.min.js"></script>
<link rel="stylesheet" href="sweetalert2.min.css">
<style>
	#mem-enroll-area table {
		margin: auto;
	}
</style>

</head>
<body>
	<jsp:include page="../common/menubar.jsp" />
	<div class="outer">
		<h2 align="center">회원가입</h2>

		<form id="mem-enroll-area" action="insert.me" method="post">
			<table>
				<tr>
					<td>* 아이디</td>
					<td>
						<input type="text" class="form-control" id="userId" name="userId" maxlength="30" required />
					</td>
					<td>
						<input type="button" value="중복체크" class="btn btn-sm btn-outline-light" onclick="idCheck();" />
					</td>
				</tr>
				<tr>
					<td>* 비밀번호</td>
					<td>
						<input type="password" class="form-control" id="userPwd" name="userPwd" required />
					</td>
				</tr>
				<tr>
					<td>* 비밀번호 확인</td>
					<td>
						<input type="password" class="form-control" id="userPwdCheck" required />
					</td>
				</tr>
				<tr>
					<td>* 이름</td>
					<td>
						<input type="text" class="form-control" name="userName" required />
					</td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;이메일</td>
					<td>
						<input type="email" class="form-control" name="userEmail" />
					</td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;성별</td>
					<td>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="gender" value="M" id="genderM" checked>
							<label class="form-check-label" for="genderM">
								남자
							</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="gender" value="F" id="genderF">
							<label class="form-check-label" for="genderF">
								여자
							</label>
						</div>
					</td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;생년월일</td>
					<td>
						<input type="date" class="form-control" id="birthDay" onchange="changeBirthDay(this);" />
						<input type="hidden" class="form-control" id="formatBirthDay" name="birthDay" />
					</td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;연락처</td>
					<td>
						<input 
							type="tel" 
							class="form-control" 
							name="phone" 
							id="txtPhone" 
							value=""
							maxlength="13" 
							placeholder="000-0000-0000" 
							pattern="[0-9]{2,3}-[0-9]{3,4}-[0-9]{3,4}" />
					</td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;주소</td>
					<td>
						<input type="text" class="form-control" name="address"/>
					</td>
				</tr>
			</table>
			<br><br>
			<div align="center">
				<button class="btn btn-primary" type="submit" id="registerBtn" onclick="return pwCheck();" disabled>회원가입</button>
				<button class="btn btn-outline-danger" type="submit">초기화</button>
			</div>
		</form>
	</div>
	
	<script>
		function changeBirthDay(target) {
			document.querySelector("#mem-enroll-area #formatBirthDay").value = target.value.slice(2).split("-").join("");
			
			// Date 객체 사용하기
			/*
			const birthDay2 = new Date(birthDay);
			let yy = birthDay2.getFullYear() % 100;
			let mm = "0" + (birthDay2.getMonth() + 1).slice(1);
			let dd = "0" + (birthDay2.getDate()).slice(1);
			
			const formatBirthDay = document.querySelector("#formatBirthDay");
			formatBirthDay.value = yy+mm+dd;
			*/
		}
		
		// 입력된 비밀번호 값과 비밀번호 확인 값이 같을 경우 true 다를 경우 false를 반환
		function pwCheck() {
			// 입력 값
			const pw = document.querySelector("#userPwd").value;
			
			// 확인 값
			const pwc = document.querySelector("#userPwdCheck").value;
			
			if(pw !== pwc) alert("비밀번호와 비밀번호 확인 값이 다릅니다."); // window.alert()
			
			return pw === pwc;
		}
		
		// 연락처 형식
		document.addEventListener("DOMContentLoaded", function() {
	        document.getElementById("txtPhone").addEventListener("keyup", function(event) {
	            inputPhoneNumber(event.target);
	        });
	    });
		/*
		
		*MDN 참조*
		DOMContentLoaded 이벤트 : HTML 문서가 완전히 구문 분석되고 모든 지연된 스크립트가 다운로드되고 실행될 때 발생함
		
		DOMContentLoaded는 스타일시트의 로드를 기다리지 않습니다.
		하지만 지연된 스크립트는 스타일시트를 기다리며 DOMContentLoaded 이벤트는 지연된 스크립트 이후에 대기열로 추가됩니다.
		또한, 지연되지 않거나 비동기가 아닌 스크립트(예: <script>)는 이미 구문 분석된 스타일시트가 로드될 때까지 기다립니다.

		이 이벤트의 원래 대상은 로드된 문서입니다.
		Window 인터페이스에서 이 이벤트를 수신하여 캡처 혹은 버블링 단계에서 다룰 수 있습니다.
		이 이벤트에 대한 자세한 설명은 DOMContentLoaded 페이지를 참조하시길 바랍니다.
		
		*/
	    
	    function inputPhoneNumber( phone ) {
	        if( event.keyCode != 8 ) {
	            const regExp = new RegExp( /^[0-9]{2,3}-^[0-9]{3,4}-^[0-9]{4}/g );
	            if( phone.value.replace( regExp, "").length != 0 ) {                
	                if( checkPhoneNumber( phone.value ) == true ) {
	                    let number = phone.value.replace( /[^0-9]/g, "" );
	                    let tel = "";
	                    let seoul = 0;
	                    if( number.substring( 0, 2 ).indexOf( "02" ) == 0 ) {
	                        seoul = 1;
	                        phone.setAttribute("maxlength", "12");
	                        console.log( phone );
	                    } else {
	                        phone.setAttribute("maxlength", "13");
	                    }
	                    if( number.length < ( 4 - seoul) ) {
	                        return number;
	                    } else if( number.length < ( 7 - seoul ) ) {
	                        tel += number.substr( 0, (3 - seoul ) );
	                        tel += "-";
	                        tel += number.substr( 3 - seoul );
	                    } else if(number.length < ( 11 - seoul ) ) {
	                        tel += number.substr( 0, ( 3 - seoul ) );
	                        tel += "-";
	                        tel += number.substr( ( 3 - seoul ), 3 );
	                        tel += "-";
	                        tel += number.substr( 6 - seoul );
	                    } else {
	                        tel += number.substr( 0, ( 3 - seoul ) );
	                        tel += "-";
	                        tel += number.substr( ( 3 - seoul), 4 );
	                        tel += "-";
	                        tel += number.substr( 7 - seoul );
	                    }
	                    phone.value = tel;
	                } else {
	                    const regExp = new RegExp( /[^0-9|^-]*$/ );
	                    phone.value = phone.value.replace(regExp, "");
	                }
	            }
	        }
	    }
	
	    function checkPhoneNumber( number ) {
	        const regExp = new RegExp( /^[0-9|-]*$/ ); 
	        if( regExp.test( number ) == true ) { return true; }
	        else { return false; }
	    }
	    
	    // 아이디 중복 체크 (비동기통신, ajax)
	    function idCheck() {
	    	// 중복 체크 버튼 클릭 시 사용자가 입력한 아이디 값을 전달하여 조회
	    	// => 존재 여부에 대해 확인 후 응답 데이터로 받을 것임
	    	
	    	// 입력된 아이디 값 조회(jQuery 사용)
	    	const $userId = $("#mem-enroll-area #userId");
	    	// console.log($userId, $userId.val());
	    	console.log("**** ajax 요청 전 ****");
	    	
	    	// $.ajax(요청정보)
	    	// 요청정보 : 객체(json) 형태로 key/value 형태로 작성
	    	/*
	    		- url : 요청주소
	    		- type | method : 요청방식
	    		- data : 전달데이터(json) {키:값, 키:값, ...}
	    		- success : 요청(통신) 성공 시 처리할 내용 (함수),
	    		- error : 요청(통신) 실패 시 처리
	    		
	    		-- 참고 --
	    		- async : 서버와의 비동기 통신 처리 여부(기본값 : true)
	    		- contentType : 요청 시 데이터의 인코딩 방식
	    		- dataType : 서버에서 응답 시 전달되는 데이터 형식 설정(설정하지 않을 경우 자동으로 판단함)
						+ XML  : 트리 형태
						+ JSON : 객체(Map) 형태
						+ HTML : HTML 형식(태그)
						+ TEXT : 문자열 데이터
	    	*/
	    	$.ajax({
	    		url: "idCheck",
	    		data: {
	    			userId: $userId.val()
	    		},
	    		type: 'GET', // GET 방식은 생략 가능
	    		success: function(result) {
	    			// 요청 성공 시
	    			// console.log(result);
	    			if(result === 'YYY') {
		    			Swal.fire({
							  title: '아이디체크',
							  text: '사용가능한 아이디입니다.',
							  icon: 'success',
							  confirmButtonText: '확인',
							  <%--
							  showCancelButton: true,
							  cancelButtonText: '취소'
							  --%>
							});
		    			
		    			// document.querySelector("#registerBtn").disabled = false;
		    			$("#registerBtn").removeAttr("disabled");
		    			
		    			$("#userId").attr("readonly", true);
	    			} else {
	    				Swal.fire({
							  title: '아이디체크',
							  text: '이미 사용중인 아이디입니다.',
							  icon: 'warning',
							  confirmButtonText: '확인',
							  <%--
							  showCancelButton: true,
							  cancelButtonText: '취소'
							  --%>
							});
	    			}
	    		},
	    		error: function(err) {
	    			// 요청 실패 시
	    			Swal.fire({
						  title: '아이디체크',
						  text: '오류발생',
						  icon: 'error',
						  confirmButtonText: '확인'
						});
	    		}
	    	});
	    }
	</script>
</body>
</html>