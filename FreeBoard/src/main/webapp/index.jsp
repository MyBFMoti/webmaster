
<%@page import="com.yedam.service.MemberService" %>
<%@page import="com.yedam.service.MemberServiceImpl" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- MVC 디자인: View(JSP페이지) , Module(DB처리), 컨트롤 -->
	<!--jsp에서 자바코드 사용(보통은 구분)  -->
	<% 
		String myName = "이신화";
		MemberService svc =  new MemberServiceImpl();
		if(svc.retireMember("user11")){	
	%>
			<p>삭제되었습니다</p>
	
	<%	} else{%>
			<p>회원정보가 없습니다.</p>
	<%	} %>
	
	<!--jsp에서 자바에서 만든 변수 불러오기 -->
	<h3>내 이름은<%=myName %>입니다.</h3>
	
	
	
</body>
</html>