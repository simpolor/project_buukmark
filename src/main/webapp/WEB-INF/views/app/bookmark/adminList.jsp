<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

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
			
			function f_del( bookmarkSeq ){
				if(confirm("즐겨찾기를 삭제하시겠습니까?")){
					$("#bookmark_seq").val(bookmarkSeq);
					$("#bookmarkForm").attr("action", "${pageContext.request.contextPath}/admin/bookmark/del");
					$("#bookmarkForm").attr("method", "post");
					$("#bookmarkForm").submit();
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
							<a href="#bookmark-add" rel="modal:open" class="pure-button bookmark-myAdd-btn" onclick="ex1();">
								<i class="fa fa-plus" aria-hidden="true" style="vertical-align: middle;"></i>
							</a>
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
				<form id="bookmarkForm" >
					<input type="hidden" id="bookmark_seq" name="bookmark_seq" />
					<table>
						<caption>북마크 목록</caption>
						<colgroup>
							<col width="10%">
							<col width="15%">
							<col width="70%">
							<col width="5%">
						</colgroup>
						<thead>
							<tr>
								<th>유형</th>
								<th>분류</th>
								<th colspan="2">설명 및 링크</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${! empty bookmarkList}">
									<c:forEach var="bookmarkList" items="${bookmarkList}">
										<tr id="bookmark_list_group_${bookmarkList.bookmark_seq}">
								 			<td align="center">${bookmarkList.bookmark_type}</td>
								 			<td align="center">${bookmarkList.bookmark_category}</td>
								 			<td align="left">
								 				<span>${bookmarkList.bookmark_description}</span><br />
								 				<a class="font-12 color-royal-blue" href="${bookmarkList.bookmark_url}" target="_blink">
								 					${bookmarkList.bookmark_url}
								 				</a>
								 			</td>
								 			<td align="center">
												<c:if test="${! empty sessionScope.SESSION_MEMBER_ID}">
						 							<i class="fa fa-trash color-black corsor" aria-hidden="true" onclick="f_del('${bookmarkList.bookmark_seq}'); return false;"></i>
									 			</c:if>
											</td>
								 		</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<tr>
							 			<td colspan="4" align="center">데이터가 존재하지 않습니다.</td>
							 		</tr>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>		
				</form>			
			</div><!-- bookmark-list -->
			
			<div class="bookmark-pagination">
				<a href="${pageContext.request.contextPath}/admin/bookmark/list?page=${paging.startPage}"><i class="fa fa-angle-double-left"></i></a>
				<a href="${pageContext.request.contextPath}/admin/bookmark/list?page=${paging.prevPage}"><i class="fa fa-angle-left"></i></a>
				
				<c:forEach var="i" begin="${paging.startPage}" end="${paging.endPage}">
					<c:choose>
						<c:when test="${i == paging.pageNo}">
							<strong>${i}</strong>
						</c:when>
						<c:otherwise>
							<a href="${pageContext.request.contextPath}/bookmark/myList?page=${i}">${i}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<a href="${pageContext.request.contextPath}/admin/bookmark/list?page=${paging.nextPage}"><i class="fa fa-angle-right"></i></a>
				<a href="${pageContext.request.contextPath}/admin/bookmark/list?page=${paging.endPage}"><i class="fa fa-angle-double-right"></i></a>
			</div><!-- bookmark-pagination -->
			
		</div><!-- #section -->
		
	</tiles:putAttribute>
</tiles:insertDefinition>