<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
<h3>로그인화면(loginForm.jsp)</h3>

<%
	String msg = (String) request.getAttribute("msg");
%>
<%if(msg != null){ %>
<p style="color: red;"><%=msg %></p>
<%} %>

<form action="loginForm.do" method="POST">
	<table class="table">
	<tr>
		<th class="col-sm-4">아이디</th><td><input type="text" name= "logId"></td>
	</tr>
	<tr>
		<th class="col-sm-4">비밀번호</th><td><input type="password" name= "logPw"></td>
	</tr>
	<tr>
		<td align="center" colspan="2">
			<button type="submit" class="btn btn-primary">로그인</button>
		</td>
	</tr>
	</table>
</form>



<jsp:include page="../includes/footer.jsp"></jsp:include>