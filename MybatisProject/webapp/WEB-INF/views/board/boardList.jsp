<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.List, com.kh.mybatis.board.model.vo.Board" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>
	<jsp:include page="../common/menubar.jsp" />
	<div class="container mt-5">
		<table class="table table-danger table-striped table-hover">
			<thead>
				<tr>
					<th scope="col" style="text-align:center">번호</th>
					<th scope="col" style="text-align:center">글 제목</th>
					<th scope="col" style="text-align:center">작성자</th>
					<th scope="col" style="text-align:center">작성일</th>
					<th scope="col" style="text-align:center">좋아요 수</th>
				</tr>
			</thead>
			<tbody class="table-group-divider">
				<c:choose>
					<c:when test="${ not empty bList }">
						<c:forEach var="l" items="${bList }">
							<tr style="cursor:pointer;" onclick="location.href='boardDetail.bo/${l.boardNo}'">
								<th scope="row" style="text-align:center">${ l.boardNo }</th>
								<td>${ l.boardTitle }</td>
								<td style="text-align:center">${ l.boardWriter }</td>
								<td style="text-align:center">
									<fmt:formatDate value="${ l.createDate }" pattern="yyyy-MM-dd" />
								</td>
								<td style="text-align:center">${ l.count }</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="5" style="text-align: center;">조회된 데이터가 없습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>

		<a href="boardWritePage.bo" class="btn btn-primary float-end mb-5">글작성</a>
	</div>
</body>
</html>