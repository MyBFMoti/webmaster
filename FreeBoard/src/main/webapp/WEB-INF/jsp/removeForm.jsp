<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.yedam.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="../includes/header.jsp"></jsp:include>
<h3>삭제하시겠습니까?(removeForm.jsp)</h3>
<%
	BoardVO bvo = (BoardVO) request.getAttribute("boardvo");
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String wdate = sdf.format(bvo.getWriteDate());
%>
<form action="removeBoard.do" method="post">
<input type="hidden" name="bno" value="<%=bvo.getBoardNo()%>">
<table class="table">
	<tr>
		<th>글번호</th><td><%=bvo.getBoardNo() %></td><th>조회수</th><td><%=bvo.getViewCnt() %></td>
	</tr>
	<tr>
		<th>제목</th><td colspan="3"><%=bvo.getTitle() %></td>
	</tr>
	<tr>
		<th>내용</th><td colspan="3"><textarea readonly="readonly"><%=bvo.getContent() %></textarea></td>
	</tr>
	<tr>
		<th>작성자</th><td colspan="3"><%=bvo.getWriter() %></td>
	</tr>
	<tr>
		<th>작성일시</th><td colspan="3"><%=wdate %></td>
	</tr>
	<tr class="d-grid gap-2 d-md-block">
		<td align="center"><input type="submit" class="btn btn-danger btn-lg" value="삭제"></td>
		<td align="center"><input type="button" class="btn btn-warning btn-lg" value="취소"></td>
	</tr>
	
</table>
</form>
<jsp:include page="../includes/footer.jsp"></jsp:include>
<script>

	document.querySelector('input[value="취소"]')
	.addEventListener('click', function(e){
	location.href = 'board.do?bno=<%=bvo.getBoardNo() %>'
});
	
</script>

