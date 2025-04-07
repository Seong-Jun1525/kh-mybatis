<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="../common/menubar.jsp" />
	<button type="button" class="btn btn-secondary" onclick="history.back();">뒤로가기</button>
	<button type="button" class="btn btn-secondary" onclick="history.back();">뒤로가기</button>
	<a href="${ pageContext.request.contextPath }/boardDelete.bo/${ board.boardNo}" class="btn btn-secondary">삭제</a>
	<ul>
		<li>${ board.boardNo }</li>
		<li>${ board.boardTitle }</li>
		<li>${ board.boardWriter }</li>
		<li>${ board.boardContent }</li>
		<li>${ board.count }</li>
		<li><fmt:formatDate value="${ board.createDate }"/></li>
	</ul>
	
	<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" data-bs-whatever="@fat">댓글등록</button>

	<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="exampleModalLabel">댓글등록</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<form>
					<div class="modal-body">
						<div class="mb-3">
							<label for="message-text" class="col-form-label">댓글</label>
							<textarea class="form-control" id="replyContent" name="replyContent"></textarea>
						</div>
					</div>
				</form>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>
					<button type="button" onclick="replyInsert();" class="btn btn-primary">작성하기</button>
				</div>
			</div>
		</div>
	</div>
	
	<table class="table table-danger">
		<thead>
			<tr>
				<th>내용</th>
				<th>작성자</th>
				<th>작성날짜</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${ not empty rList }">
					<c:forEach var="r" items="${ rList }">
						<tr>
							<td>${ r.replyContent }</td>
							<td>${ r.replyWriter }</td>
							<td>${ r.createDate }</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr><td colspan="3">조회된 댓글이 없습니다.</td></tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	
	<script>
		function replyInsert() {
			$.ajax({
				url: "${ pageContext.request.contextPath }/replyInsert.re",
				method: "POST",
				data: {
					replyContent: $("#replyContent").val(),
					boardNo: "${ board.boardNo }",
					replyWriter: "${ loginUser.userId }"
				},
				
				success: function(rList) {
					Swal.fire({
						title: "댓글",
						icon: "success",
						text: "댓글 등록 성공!",
						confirmButtonText: "확인"
					}).then(() => {
						let node;
						
						for(let r of rList) {
							node += 
								"<tr>" 
									+ "<td>${ r.replyContent }</td>"
									+ "<td>${ r.replyWriter }</td>"
									+ "<td>${ r.createDate }</td>"
								+ "</tr>"
						}
						
						$("tbody").html(node);
						location.reload(true);
					});
				},
				error: function(err) {
					console.log(err);
				}
			});	
		}
	</script>
</body>
</html>