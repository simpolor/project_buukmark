<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<tiles:insertDefinition name="admin-layout">
    <tiles:putAttribute name="admin-body">
		
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/member.css">
		
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
				$('#memberSearchForm').submit();
			}
			
		</script>
		
		<div id="section">
				
			<div class="member-nav">
				<div class="member-nav-title">
					<span>회원 목록</span>
				</div>
			</div>
			
			<div class="member-list">
				<table>
					<caption>회원 목록</caption>
					<colgroup>
						<col width="12.5%">
						<col width="12.5%">
						<col width="12.5%">
						<col width="12.5%">
						<col width="12.5%">
						<col width="12.5%">
					</colgroup>
					
					<thead>
						<tr>
							<th>아이디</th>
							<th>이름</th>
							<th>닉네임</th>
							<th>이메일</th>
							<th>등급</th>
							<th>탈퇴유무</th>
						</tr>
					</thead>
					
					<tbody>
						<c:choose>
							<c:when test="${! empty memberList}">
								<c:forEach var="memberList" items="${memberList}">
									<tr>
							 			<td align="center"><a href="./view?seq=${memberList.member_id}">${memberList.member_id}</a></td>
							 			<td align="center">${memberList.member_name}</td>
							 			<td align="center">${memberList.member_nickname}</td>
							 			<td align="center">${memberList.member_email}</td>
							 			<td align="center">${memberList.level}</td>
							 			<td align="center">${memberList.del_yn}</td>
							 		</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
						 			<td colspan="8" align="center">No data</td>
						 		</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>					
			</div>
			
			<div class="member-pagination">
				<a href="${pageContext.request.contextPath}/admin/member/list?page=${paging.startPage}"><i class="fa fa-angle-double-left"></i></a>
				<a href="${pageContext.request.contextPath}/admin/member/list?page=${paging.prevPage}"><i class="fa fa-angle-left"></i></a>
				
				<c:forEach var="i" begin="${paging.startPage}" end="${paging.endPage}">
					<c:choose>
						<c:when test="${i == paging.pageNo}">
							<strong>${i}</strong>
						</c:when>
						<c:otherwise>
							<a href="${pageContext.request.contextPath}/admin/member/list?page=${i}">${i}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<a href="${pageContext.request.contextPath}/admin/member/list?page=${paging.nextPage}"><i class="fa fa-angle-right"></i></a>
				<a href="${pageContext.request.contextPath}/admin/member/list?page=${paging.endPage}"><i class="fa fa-angle-double-right"></i></a>
			</div><!-- board-pagination -->
			
			<div class="member-search">
				<form:form id="memberSearchForm" action="${pageContext.request.contextPath}/admin/member/list" method="get" commandName="memberVO" style="margin: 0;" >
					<fieldset>
						<legend>게시판 검색</legend>
						<p>
							<form:select path="search_condition" class="member-search-condition">
								<<form:option value="">검색조건 선택</form:option>
								<form:option value="member_id">아이디</form:option>
								<form:option value="member_name">이름</form:option>
								<form:option value="member_nickname">닉네임</form:option>
								<form:option value="member_email">이메일</form:option>
							</form:select>
							<form:input path="search_word" class="member-search-word" placeholder="검색내용" />
							<button class="member-search-btn" onclick="f_search(); return false;">검색</button>
						</p>
					</fieldset>
				</form:form>
			</div><!-- board-search -->
			
		</div><!-- #section -->
		
	</tiles:putAttribute>
</tiles:insertDefinition>