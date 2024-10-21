<%@page import="com.yedam.common.PageDTO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.yedam.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<h3>글 목록</h3>
<%
	List<BoardVO> list = (List<BoardVO>) request.getAttribute("boardList");
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	PageDTO paging = (PageDTO) request.getAttribute("page");
	
	String sc = (String) request.getAttribute("searchCondition");
	String kw = (String) request.getAttribute("keyword");
	kw = kw == null ? "" : kw;
%>

<!-- 검색조건 -->
<form action="boardList.do" class="row g-3">
	<div class="col-md-3">
		<select name="searchCondition" class="form-select">
			<option selected value="">선택하세요.</option>
			<option value="T"
				<%=(sc != null && sc.equals("T") ? "selected" : "") %>>제목</option>
			<option value="W"
				<%=(sc != null && sc.equals("W") ? "selected" : "") %>>작성자</option>
			<option value="TW"
				<%=(sc != null && sc.equals("TW") ? "selected" : "") %>>제목&작성자</option>
		</select>
	</div>
	<div class="col-md-4">
		<input type="text" class="form-control" name="keyword"
			value='<%=kw %>'>
	</div>
	<div class="col-md-5">
		<button type="submit" class="btn btn-primary">조 회</button>
	</div>
</form>


<table class="table table-hover">
	<thead class="table-dark">
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일자</th>
			<th>조회수</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="board" items="${boardList }">
			<tr>
				<td><c:out value="${board.boardNo }" /></td>
				<td><a href='board.do?&searchCondition=${searchCondition }&keyword=${keyword }&page=${page.page }&bno=${board.boardNo }' />
				${board.title }</td>
			<td><c:out value="${board.writer }"/></td>
			<td><fmt:formatDate value="${board.writeDate }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
			<td><c:out value="${board.viewCnt }"/></td>
		</tr>
	</c:forEach>
	<!-- 데이터 없으면 no data -->
	<c:if test="${fn:length(boardList)==0 }">
		<tr>
			<td align = "center" colspan="5">-no data -</td>
		</tr>
	</c:if>
	</tbody>
</table>

<!-- paging -->
<!--<%=paging %>-->

<nav aria-label="Page navigation example">
  <ul class="pagination justify-content-center">
  
  	<!-- 이전페이지 여부 -->
  	<c:choose>
    <c:when test="${page.prev}">
        <li class="page-item">
            <a class="page-link" href="boardList.do?searchCondition=${searchCondition}&keyword=${keyword}&page=${page.startPage - 1}">Previous</a>
        </li>
    </c:when>
    <c:otherwise>
        <li class="page-item disabled">
            <a class="page-link">Previous</a>
        </li>
    </c:otherwise>
	</c:choose>
	
   	
   
   	
   	
   	<!-- 페이지 출력 -->
   	
   	<c:forEach var="p" begin="${page.startPage}" end="${page.endPage}">
        <c:choose>
            <c:when test="${page.page == p}">
                <li class="page-item active" aria-current="page">
                    <span class="page-link">${p}</span>
                </li>
            </c:when>
            <c:otherwise>
                <li class="page-item">
                    <a class="page-link" href="boardList.do?searchCondition=${searchCondition}&keyword=${keyword}&page=${p}">${p}</a>
                </li>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    
   	
   	<!--  다음페이지 여부 -->
   	<c:choose>
    <c:when test="${page.next}">
        <li class="page-item">
            <a class="page-link" href="boardList.do?searchCondition=${searchCondition}&keyword=${keyword}&page=${page.endPage+1}">Next</a>
        </li>
    </c:when>
    <c:otherwise>
        <li class="page-item disabled">
            <a class="page-link">Next</a>
        </li>
    </c:otherwise>
	</c:choose>
   
    
  </ul>
</nav>

