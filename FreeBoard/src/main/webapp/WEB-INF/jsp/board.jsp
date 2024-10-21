<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.yedam.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="../includes/header.jsp"></jsp:include>
<h3>상세페이지</h3>
<%
	BoardVO bvo = (BoardVO) request.getAttribute("boardvo");
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String wdate = sdf.format(bvo.getWriteDate());
	
	String pg =(String) request.getAttribute("page");
	String sc =(String) request.getAttribute("searchCondition");
	String kw =(String) request.getAttribute("keyword");
%>
<table class="table">
	<tr>
		<th>글번호</th><td><c:out value="${boardvo.boardNo }"/></td><th>조회수</th><td><c:out value="${boardvo.viewCnt }"/></td>
	</tr>
	<tr>
		<th>제목</th><td>${boardvo.title }</td>
		<th>작성자</th><td>${boardvo.writer }</td>
	</tr>
	<tr>
		<th>내용</th><td colspan="3"><textarea class="form-control" readonly="readonly"><c:out value="${boardvo.content }"/></textarea></td>
	</tr>
	<c:if test="${boardvo.img != null }">
	<tr>
		<th>이미지</th>
		<td colspan="3">
			
				<img src="images/${boardvo.img}" width='150px'>
			
		</td>
	</tr>
	</c:if>
	<tr>
		<th>작성일시</th>
		<td colspan="3">
		<fmt:formatDate value="${boardvo.writeDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
		</td>
	</tr>
	<tr class="d-grid gap-2 d-md-block">
		<td align="center"><input type="submit" class="btn btn-primary btn-lg" value="수정"></td>
		<td align="center"><input type="submit" class="btn btn-danger btn-lg" value="삭제"></td>
	</tr>
	
</table>
<jsp:include page="../includes/footer.jsp"></jsp:include>

<script>
	document.querySelector('input[value="수정"]')
		.addEventListener('click', function(e){
		location.href = 'modifyBoard.do?page=${page}&bno=${boardvo.boardNo}&searchCondition=${searchCondition}&keyword=${keyword}'
	});
	document.querySelector('input[value="삭제"]')
	.addEventListener('click', function(e){
	location.href = 'removeBoard.do?page=${page}&bno=${boardvo.boardNo}&searchCondition=${searchCondition}&keyword=${keyword}'
});
	
</script>
