<%@page import="com.yedam.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
<h3>수정화면(modifyForm.jsp)</h3>

<%
	String msg = (String) request.getAttribute("msg");
	String pg = (String) request.getAttribute("page");
	BoardVO board = (BoardVO) request.getAttribute("boardvo");
	String sc = (String) request.getAttribute("searchCondition");
	String kw = (String) request.getAttribute("keyword");
	
	//세션 정보
	String logId = (String) session.getAttribute("logId");
%>
<%if(msg != null){ %>
<p style="color: red;"><%=msg %></p>
<%} %>
<form action="modifyBoard.do" method="post">
	<input type="hidden" name="bno" value="<%=board.getBoardNo()%>">
	<input type="hidden" name="page" value="<%=pg%>">
	<input type="hidden" name="searchCondition" value="<%=sc%>">
	<input type="hidden" name="keyword" value="<%=kw%>">
	<table class="table">
		<tr>
			<th>글번호</th><td><%=board.getBoardNo() %></td>
			<th>조회수</th><td><%=board.getViewCnt() %></td>
		</tr>
		<tr>
			<th>제목</th><td><input class="form-control" type="text" name="title" value="<%=board.getTitle() %>"></td>
		</tr>
		<tr>
			<th>내용</th><td><textarea class="form-control" name="content" rows="3" cols="30"><%=board.getContent() %></textarea></td>
		</tr>
		<tr>
			<th>작성자</th><td><%=board.getWriter() %></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" class="btn btn-success" value="저장" <%=logId !=null && logId.equals(board.getWriter()) ? "": "disabled" %>>
				<input type="button" class="btn btn-warning" value="취소" >
			</td>
		</tr>
	</table>
</form>
<script>
document.querySelector('input[value="취소"]')
	.addEventListener('click', function(e){
		location.href = 'board.do?page=<%=pg %>&bno=<%=board.getBoardNo() %>&searchCondition=<%=sc==null ? "" : sc %>&keyword=<%=kw==null ? "" : kw %>'
});
</script>

<jsp:include page="../includes/footer.jsp"></jsp:include>