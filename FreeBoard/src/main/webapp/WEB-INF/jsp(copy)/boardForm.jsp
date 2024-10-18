<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
<h3>등록화면(boardForm.jsp)</h3>

<%
	String msg = (String) request.getAttribute("msg");
	String logId = (String) session.getAttribute("logId");
%>
<%if(msg != null){ %>
<p style="color: red;"><%=msg %></p>
<%} %>
<form action="addBoard.do" method="get">
	<input class="form-control" type="hidden" name="writer" value="<%=logId %>">
	<table class="table">
		<tr>
			<th>제목</th><td><input class="form-control" type="text" name="title"></td>
		</tr>
		<tr>
			<th>내용</th><td><textarea class="form-control" name="content" rows="3" cols="30"></textarea></td>
		</tr>
		<tr>
			<th>작성자</th><td><%=logId %></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" class="btn btn-success" value="저장">
				<input type="submit" class="btn btn-warning" value="취소">
			</td>
		</tr>
	</table>
</form>
<jsp:include page="../includes/footer.jsp"></jsp:include>