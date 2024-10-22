<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h3>javascript</h3>

<table class="table">
	<tr><th>회원아이디</th><td><input type="text" id="mid"></td></tr>
	<tr><th>회원이름</th><td><input type="text" id="mname"></td></tr>
	<!--  <tr><th>비밀번호</th><td><input type="password" id="pw"></td></tr>-->
	<tr><th>연락처</th><td><input type="text" id="mphone"></td></tr>
	<tr>
		<td align="center" colspan="2"><button id="addBtn" class="btn btn-primary">등록</button></td>
	</tr>
</table>

<div id="show">
	
	<!-- 회원 목록 출력 -->
	<table class="table">
		<thead>
			<tr><th>회원아이디</th><th>회원이름</th><th>비밀번호</th><th>연락처</th><th>권한</th></tr>
		</thead>
		<tbody></tbody>
	</table>
	
</div>
<!--<script src="js/data.js"></script>  -->
<!--<script src="js/members.js"></script>-->
<script src="js/ajax1.js"></script>

<!--<script src="js/json.js"></script>-->
<!--<script src="js/basic.js"></script>  -->