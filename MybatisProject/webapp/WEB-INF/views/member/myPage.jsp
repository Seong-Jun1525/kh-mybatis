<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

<style>
	#mem-update-area table {
		margin: auto;
	}
</style>

</head>
<body>
	<jsp:include page="../common/menubar.jsp" />
	<div class="outer">
		<h2 align="center">마이페이지</h2>

		<form id="mem-update-area" action="update.me" method="post">
			<table>
				<tr>
					<td>* 아이디</td>
					<td>
						<input type="text" class="form-control" name="userId" value="${ loginUser.userId }" readonly />
					</td>
				</tr>
				<tr>
					<td>* 이름</td>
					<td>
						<input type="text" class="form-control" name="userName" value="${ loginUser.userName }" readonly />
					</td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;이메일</td>
					<td>
						<input type="email" class="form-control" name="userEmail" value="${ loginUser.email }" />
					</td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;성별</td>
					<td>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="gender" value="M" id="genderM" checked >
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
						<input type="date" class="form-control" id="birthDay" value="${ loginUser.birthDay }" onchange="changeBirthDay(this);" />
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
							value="${ loginUser.phone }"
							maxlength="13" 
							pattern="[0-9]{2,3}-[0-9]{3,4}-[0-9]{3,4}"
							readonly />
					</td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;주소</td>
					<td>
						<input type="text" class="form-control" value="${ loginUser.address }" name="address"/>
					</td>
				</tr>
			</table>
			<br><br>
			<div align="center">
				<button class="btn btn-sm btn-primary" type="submit">정보수정</button>
				<button type="button" class="btn btn-sm btn-secondary" data-bs-toggle="modal" data-bs-target="#updateMemberPwd">비밀번호 변경</button>
				<button type="button" class="btn btn-sm btn-danger" data-bs-toggle="modal" data-bs-target="#deleteMemberModal">회원탈퇴</button>
			</div>
		</form>
	</div>
	
	<!-- 비밀번호 변경 모달 -->
	<div class="modal fade" id="updateMemberPwd" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="exampleModalLabel">비밀번호 변경</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form action="updatePwd.me" method="post">
						<%-- 회원 아이디 입력받기 X --%>
						<input type="hidden" name="userId" value="${ loginUser.userId }">
						<%-- 현재 비밀번호 --%>
						<div class="mb-3">
							<label for="userPwd" class="col-form-label">현재 비밀번호</label>
							<input type="password" id="userPwd" class="form-control" name="userPwd" required>
						</div>
						<%-- 변경할 비밀번호 --%>
						<div class="mb-3">
							<label for="changeUserPwd" class="col-form-label">변경할 비밀번호</label>
							<input type="password" id="changeUserPwd" class="form-control" name="changeUserPwd" required>
						</div>
						<%-- 변경할 비밀번호 확인 --%>
						<div class="mb-3">
							<label for="checkPwd" class="col-form-label">변경할 비밀번호 확인</label>
							<input type="password" id="checkPwd" class="form-control" required>
						</div>
						<button type="submit" class="btn btn-sm btn-secondary float-end" onclick="return pwCheck();">변경하기</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 회원 탈퇴 모달 -->
	<div class="modal fade" id="deleteMemberModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="exampleModalLabel">회원탈퇴</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form action="delete.me" method="post">
						<%-- 회원 아이디 입력받기 X --%>
						<input type="hidden" name="userId" value="${ loginUser.userId }">
						<p>
							탈퇴 후 <span style="color: red; font-weight: 900;">복구 불가능</span>합니다. 그래도 탈퇴 하시겠습니까?
						</p>
						<%-- 회원 비밀번호 --%>
						<div class="mb-3">
							<label for="userPwd" class="col-form-label">비밀번호</label>
							<input type="password" id="userPwd" class="form-control" name="userPwd" required>
						</div>
						<button type="submit" class="btn btn-sm btn-danger float-end">탈퇴하기</button>
					</form>
				</div>
			</div>
		</div>
	</div>


	<script>
	
		onload = function() {
			const gender = "${ loginUser.gender }";
			// console.log(gender);
			
			// 성별 입력 요소들에 접근
			const genderInputs = document.querySelectorAll("input[name=gender]");
			
			for(let gEle of genderInputs) {
				// console.log(gEle.value);
				if(gEle.value === gender) {
					gEle.checked = true;
					break;
				}
			}
			
			changeBirthDay();
		}

		function changeBirthDay(target) {
			const birth = document.getElementById("birthDay");
			
			document.querySelector("#mem-update-area #formatBirthDay").value = target?.value.slice(2).split("-").join("");
		}
		
		// 입력된 비밀번호 값과 비밀번호 확인 값이 같을 경우 true 다를 경우 false를 반환
		function pwCheck() {
			// 입력 값
			const pw = document.querySelector("#changeUserPwd").value;
			
			// 확인 값
			const pwc = document.querySelector("#checkPwd").value;
			
			if(pw !== pwc) alert("비밀번호와 비밀번호 확인 값이 다릅니다."); // window.alert()
			
			return pw === pwc;
		}
		
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
	        const regExp = new RegExp( /^[0-9|-]*$/ ); // 
	        if( regExp.test( number ) == true ) { return true; }
	        else { return false; }
	    }
	</script>
</body>
</html>