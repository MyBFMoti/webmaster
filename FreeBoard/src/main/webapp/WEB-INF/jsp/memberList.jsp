<%@page import="org.apache.ibatis.reflection.SystemMetaObject"%>
<%@page import="com.yedam.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>회원목록</h3>
	<%
		List<MemberVO> list =(List<MemberVO>) request.getAttribute("memberList");
		//System.out.println(list);
	%>
	<table border='2'>
	<thead><tr>	<td><b>아이디</b></td>
				<td><b>이름</b></td>
				<td><b>연락처</b></td>
				<td><b>비밀번호</b></td>
	</tr></thead>
	<tbody>
	<%
		for(MemberVO mvo : list){
	%>
			<tr><td><%=mvo.getMemberId() %></td>
				<td><%=mvo.getMemberName() %></td>
				<td><%=mvo.getPhone() %></td>
				<td><%=mvo.getPassword() %></td>
			</tr>
	<%} %>
	</tbody>
	</table>
</body>
</html>