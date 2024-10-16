<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
 <jsp:include page="../../includes/header.jsp"></jsp:include>
	<form action="memberAdd.do">
	 <table class="table">
	 	<tr>
	 		<th>회원ID</th><td align="center"><input type="text" name="mid"></td>
	 	</tr>
	 	<tr>
	 		<th>회원이름</th><td align="center"><input type="text" name="mname"></td>
	 	</tr>
	 	<tr>
	 		<th>비밀번호</th><td align="center"><input type="password" name="passwd"></td>
	 	</tr>
	 	<tr>
	 		<th>연락처</th><td align="center"><input type="text" name="phone"></td>
	 	</tr>
	 	<tr>
	 		<td colspan="2"  align="center"><input type="submit" class="btn btn-primary" valuse="저장"></td>
	 	</tr>
	 </table>
	</form>
	<jsp:include page="../../includes/footer.jsp"></jsp:include>
</body>
</html>