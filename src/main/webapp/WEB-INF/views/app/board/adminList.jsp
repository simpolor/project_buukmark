<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<tiles:insertDefinition name="admin-layout">
    <tiles:putAttribute name="admin-body">
		
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/board.css">
		
		<script type="text/javascript">
			$(document).ready(function() { 
				var alertMessage = '${alertMessage}';
				
				if(!isNull(alertMessage)){
					alert(alertMessage);
				}
			});
			
			function f_search(){
				if(isNull($('#search_condition').val())){
					alert('검색조건을 선택해주세요.');	
					return false;
				}
				if(isNull($('#search_word').val())){
					alert('검색어를 입력해주세요.');	
					return false;
				}
				$('#boardSearchForm').submit();
			}
			
		</script>
		
		<div id="section">
				
			<div class="board-nav">
				<div class="board-nav-title">
					<span>(관리자) 자유게시판 목록</span>
				</div>
			</div>
			
			<div class="board-list">
				<table>
					<caption>자유게시판 목록</caption>
					<colgroup>
						<col width="8%">
						<col width="67%">
						<col width="10%">
						<col width="15%">
					</colgroup>
					
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>등록자</th>
							<th>등록일</th>
						</tr>
					</thead>
					
					<tbody>
						<c:choose>
							<c:when test="${! empty boardList}">
								<c:forEach var="boardList" items="${boardList}">
									<tr>
							 			<td align="center">${boardList.board_seq}</td>
							 			<td align="left"><a href="${pageContext.request.contextPath}/admin/board/view?board_seq=${boardList.board_seq}">${boardList.board_title}</a></td>
							 			<td align="center">${boardList.reg_name}</td>
							 			<td align="center">${boardList.reg_date}</td>
							 		</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
						 			<td colspan="4" align="center">No data</td>
						 		</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>					
			</div>
			
			<div class="board-pagination">
				<%-- <a href="./list?page=1"><i class="fa fa-chevron-left"></i></a> --%>
				<a href="${pageContext.request.contextPath}/admin/board/list?page=${paging.startPage}"><i class="fa fa-chevron-left"></i></a>
				<a href="${pageContext.request.contextPath}/admin/board/list?page=${paging.prevPage}"><i class="fa fa-angle-left"></i></a>
				
				<c:forEach var="i" begin="${paging.startPage}" end="${paging.endPage}">
					<c:choose>
						<c:when test="${i == paging.pageNo}">
							<strong>${i}</strong>
						</c:when>
						<c:otherwise>
							<a href="${pageContext.request.contextPath}/admin/board/list?page=${i}">${i}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<a href="${pageContext.request.contextPath}/admin/board/list?page=${paging.nextPage}"><i class="fa fa-angle-right"></i></a>
				<a href="${pageContext.request.contextPath}/admin/board/list?page=${paging.endPage}"><i class="fa fa-chevron-right"></i></a>
				<%-- <a href="./list?page=${paging.totalPage}"><i class="fa fa-chevron-right"></i></a> --%>
			</div><!-- board-pagination -->
			
			<div class="board-btn">
				<div class="board-btn-list">
					<div class="board-btn-group">
						<a href="${pageContext.request.contextPath}/admin/board/write">글쓰기</a>
					</div>
				</div>
			</div><!-- board-btn -->
			
			<div class="board-search">
				<form:form id="boardSearchForm" action="${pageContext.request.contextPath}/admin/board/list" method="get" commandName="boardVO" style="margin: 0;" >
					<fieldset>
						<legend>게시판 검색</legend>
						<p>
							<form:select path="search_condition" class="board-search-condition">
								<form:option value="">검색조건 선택</form:option>
								<form:option value="board_title">제목</form:option>
								<form:option value="board_content">내용</form:option>
								<form:option value="reg_name">작성자</form:option>
							</form:select>
							<form:input path="search_word" class="board-search-word" placeholder="검색내용" />
							<button class="board-search-btn" onclick="f_search(); return false;">검색</button>
						</p>
					</fieldset>
				</form:form>
			</div><!-- board-search -->
			
		</div><!-- #section -->
		
	</tiles:putAttribute>
</tiles:insertDefinition>