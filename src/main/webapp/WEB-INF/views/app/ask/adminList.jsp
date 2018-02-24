<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<tiles:insertDefinition name="admin-layout">
    <tiles:putAttribute name="admin-body">
		
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ask.css">
		
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
				$('#askSearchForm').submit();
			}
			
		</script>
		
		<div id="section">
				
			<div class="ask-nav">
				<div class="ask-nav-title">
					<span>(관리자) 문의사항 목록</span>
				</div>
			</div>
			
			<div class="ask-list">
				<table>
					<caption>관리자 문의사항 목록</caption>
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
							<c:when test="${! empty askList}">
								<c:forEach var="askList" items="${askList}">
									<tr>
							 			<td align="center">${askList.ask_seq}</td>
							 			<td align="left">
							 				<c:if test="${askList.secret_yn eq 'Y'}">
						 						<i class="fa fa-lock" aria-hidden="true"></i>
						 					</c:if>
							 				<a href="${pageContext.request.contextPath}/admin/ask/view?ask_seq=${askList.ask_seq}">${askList.ask_title}</a>
							 			</td>
							 			<td align="center">${askList.reg_name}</td>
							 			<td align="center">${askList.reg_date}</td>
							 		</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
						 			<td colspan="4" align="center">등록된 글이 없습니다.</td>
						 		</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>					
			</div>
			
			<div class="ask-pagination">
				<a href="${pageContext.request.contextPath}/admin/ask/list?page=${paging.startPage}"><i class="fa fa-angle-double-left"></i></a>
				<a href="${pageContext.request.contextPath}/admin/ask/list?page=${paging.prevPage}"><i class="fa fa-angle-left"></i></a>
				
				<c:forEach var="i" begin="${paging.startPage}" end="${paging.endPage}">
					<c:choose>
						<c:when test="${i == paging.pageNo}">
							<strong>${i}</strong>
						</c:when>
						<c:otherwise>
							<a href="${pageContext.request.contextPath}/admin/ask/list?page=${i}">${i}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<a href="${pageContext.request.contextPath}/admin/ask/list?page=${paging.nextPage}"><i class="fa fa-angle-right"></i></a>
				<a href="${pageContext.request.contextPath}/admin/ask/list?page=${paging.endPage}"><i class="fa fa-angle-double-right"></i></a>
			</div><!-- ask-pagination -->
			
			<div class="ask-btn">
				<div class="ask-btn-list">
					<div class="ask-btn-group">
						<a href="${pageContext.request.contextPath}/admin/ask/write">문의하기</a>
					</div>
				</div>
			</div><!-- ask-btn -->
			
			<div class="ask-search">
				<form:form id="askSearchForm" action="${pageContext.request.contextPath}/admin/ask/list" method="get" commandName="askVO" style="margin: 0;" >
					<fieldset>
						<legend>문의사항 검색</legend>
						<p>
							<form:select path="search_condition" class="ask-search-condition">
								<form:option value="">검색조건 선택</form:option>
								<form:option value="ask_title">제목</form:option>
								<form:option value="ask_content">내용</form:option>
								<form:option value="reg_name">작성자</form:option>
							</form:select>
							<form:input path="search_word" class="ask-search-word" placeholder="검색내용" />
							<button class="ask-search-btn" onclick="f_search(); return false;">검색</button>
						</p>
					</fieldset>
				</form:form>
			</div><!-- ask-search -->
			
		</div><!-- #section -->
		
	</tiles:putAttribute>
</tiles:insertDefinition>