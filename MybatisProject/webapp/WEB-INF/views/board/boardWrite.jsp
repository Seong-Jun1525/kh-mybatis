<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글작성</title>
</head>
<body>
	<form action="boardWrite.bo" method="post">
		<input type="hidden" id="boardWriter" name="boardWriter" value="${ loginUser.userId }" />
		<div class="mb-3">
		  <label for="boardTitle" class="form-label">글제목</label>
		  <input type="text" class="form-control" id="boardTitle" placeholder="글제목" name="boardTitle" />
		</div>
		<div class="mb-3">
		  <label for="boardContent" class="form-label">글내용</label>
		  <textarea class="form-control" id="boardContent" rows="3" name="boardContent"></textarea>
		</div>
		<button type="submit" class="btn btn-pirmary">작성</button>
	</form>
	<script>
		console.log("${loginUser.userId}");
	</script>
</body>
</html>