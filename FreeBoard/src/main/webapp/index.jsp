
<%@page import="com.yedam.service.MemberService" %>
<%@page import="com.yedam.service.MemberServiceImpl" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- MVC 디자인: View(JSP페이지) , Module(DB처리), 컨트롤 -->
	<!-- Expression Langguage : El -->
	
	
	<!--  <p>${3+5 > 9 ? "OK" : "NG"}</p>-->
	
	<!-- jsp action tag-->
	<!-- jsp Standard Tag Libarary : JSTL -->
	<p>${logId}</p>
	<c:set var="name" value="Hong"></c:set>
	<c:out value="${name} "></c:out>
	
	<c:set var="age" value="20"></c:set>
	<c:if test="{age>=20}">
		<p>청년입니다</p>
	</c:if>
	
	<c:choose>
		<c:when test="${age>=60 }">
			<p>노인</p>
		</c:when>
		<c:when test="${age>=20 }">
			<p>성인</p>
		</c:when>
		<c:otherwise>
			<p>미성년자</p>
		</c:otherwise>
	</c:choose>
	
	

</body>
</html>