<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<%
	String memberId = (String)session.getAttribute("SESSION_MEMBER_ID");
	System.out.println("memberId : "+memberId);
%>

<tiles:insertDefinition name="admin-layout">
    <tiles:putAttribute name="admin-body">
		
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bookmark.css">
		
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
				$('#bookmarkSearchForm').submit();
			}
			
			function f_add(){
				if(isNull($("#bookmark_type").val())){
					alert('유형을 입력해주세요.');	
					return false;
				}
				if(isNull($("#bookmark_category").val())){
					alert('분류를 입력해주세요.');	
					return false;
				}
				if(isNull($("#bookmark_description").val())){
					alert('설명을 입력해주세요.');	
					return false;
				}
				if(isNull($("#bookmark_url").val())){
					alert('URL을 입력해주세요.');	
					return false;
				}
				$("#bookmarkAddForm").submit();
			}
			
			function f_del(bookmark_seq){
				if(isNull(bookmark_seq)){
					alert('삭제할 북마크를 선택해주세요.');	
					return false;
				}
				
				if(confirm('정말 삭제하시겠습니까?')){
					$("#bookmark_seq").val(bookmark_seq);
					$("#bookmarkDelForm").submit();	
				}
			}
			
		</script>
		
		<!-- jQuery Modal -->
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
		<script type="text/javascript">
			/*function ex1(){ // 수정시 사용
				$('.bookmark-add-category').val('test');
			}*/
		</script>
		
		<div id="section">
			<div class="bookmark-mySearch">
				<form:form id="bookmarkSearchForm" action="${pageContext.request.contextPath}/admin/bookmark/list" method="get" commandName="bookmarkVO" style="margin: 0;" >
					<fieldset>
						<legend>북마크 검색</legend>
						<p>
							<form:select path="search_condition" class="bookmark-mySearch-condition">
								<form:option value="">검색조건 선택</form:option>
								<form:option value="bookmark_description">설명</form:option>
								<form:option value="bookmark_url">주소</form:option>
								<form:option value="bookmark_category">분류</form:option>
								<form:option value="bookmark_type">유형</form:option>
							</form:select>
							<form:input path="search_word" class="bookmark-mySearch-word" placeholder="검색내용" />
							<button class="pure-button bookmark-mySearch-btn" onclick="f_search(); return false;">검색</button>
							<a href="#bookmark-add" rel="modal:open" class="pure-button bookmark-myAdd-btn" onclick="ex1();">+</a>
						</p>
					</fieldset>
				</form:form>
			</div>
			
			<div id="bookmark-add" class="bookmark-add" style="display: none;">
				<form:form id="bookmarkAddForm" action="${pageContext.request.contextPath}/admin/bookmark/add" method="post" commandName="bookmarkVO" style="margin: 0;" >
					<fieldset>
						<legend>북마크 검색</legend>
						<p>
							<form:select path="bookmark_type" class="bookmark-add-type">
								<form:option value="">유형 선택</form:option>
								<form:option value="site">site</form:option>
								<form:option value="blog">blog</form:option>
							</form:select>
							<form:input path="bookmark_category" placeholder="카테고리" class="bookmark-add-category" />
							<form:input path="bookmark_description" placeholder="설명" class="bookmark-add-description" />
							<a href="#" class="pure-button bookmark-add-btn" onclick="f_add(); return false;">등록</a>
						</p>
						<p>
							<form:input path="bookmark_url" placeholder="URL" class="bookmark-add-url" />
						</p>
					</fieldset>
				</form:form>
			</div>
			
			<div class="bookmark-list">
				<table>
					<caption>북마크 목록</caption>
					<colgroup>
						<col width="10%">
						<col width="15%">
						<col width="35%">
						<col width="35%">
						<col width="5%">
					</colgroup>
					<thead>
						<tr>
							<th>유형</th>
							<th>분류</th>
							<th>설명</th>
							<th>주소</th>
							<th>&nbsp;</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${! empty bookmarkList}">
								<c:forEach var="bookmarkList" items="${bookmarkList}">
									<tr>
							 			<td align="center">${bookmarkList.bookmark_type}</td>
							 			<td align="center">${bookmarkList.bookmark_category}</td>
							 			<td>${bookmarkList.bookmark_description}</td>
							 			<td><a href="${bookmarkList.bookmark_url}" target="_blink">${bookmarkList.bookmark_url}</a></td>
							 			<td class="pure-ta-center">
							 				<c:choose>	
							 					<c:when test="${sessionScope.SESSION_MEMBER_ID == bookmarkList.reg_id}">
							 						<a href="#" onclick="f_del('${bookmarkList.bookmark_seq}'); return false;">X</a>
							 					</c:when>
							 					<c:when test="${! empty bookmarkList.reg_id}">
							 						<a href="#" onclick="f_myDel('${bookmarkList.my_seq}'); return false;">★</a>
							 					</c:when>
							 					<c:otherwise>
							 						<a href="#" onclick="f_myAdd('${bookmarkList.bookmark_seq}'); return false;">☆</a>
							 					</c:otherwise>
							 				</c:choose>
							 			</td>
							 		</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
						 			<td colspan="5" align="center">No data</td>
						 		</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>					
			</div><!-- bookmark-list -->
			
			<div class="bookmark-pagination">
				<a href="${pageContext.request.contextPath}/admin/bookmark/list?page=${paging.prevPage}"><i class="fa fa-angle-left"></i></a>
				
				<c:forEach var="i" begin="${paging.startPage}" end="${paging.endPage}">
					<c:choose>
						<c:when test="${i == paging.pageNo}">
							<strong>${i}</strong>
						</c:when>
						<c:otherwise>
							<a href="${pageContext.request.contextPath}/admin/bookmark/list?page=${i}">${i}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<a href="${pageContext.request.contextPath}/admin/bookmark/list?page=${paging.nextPage}"><i class="fa fa-angle-right"></i></a>
			</div><!-- bookmark-pagination -->
			
		</div><!-- #section -->
		
		<form:form id="bookmarkDelForm" action="${pageContext.request.contextPath}/admin/bookmark/del" method="post" commandName="bookmarkVO" style="display:none;" >
			<form:hidden path="bookmark_seq"/>
			<form:hidden path="return_type" value="myList" />
			<form:hidden path="page"/>
		</form:form>
		
	</tiles:putAttribute>
</tiles:insertDefinition>