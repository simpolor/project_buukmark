<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<%
	String memberId = (String)session.getAttribute("SESSION_MEMBER_ID");
%>

<tiles:insertDefinition name="user-layout">
    <tiles:putAttribute name="user-body">
		
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
			
			function f_myAdd( bookmarkSeq ){
				var memberId = '${sessionScope.SESSION_MEMBER_ID}';
				var bookmarkListGroupId = '#bookmark_list_group_'+bookmarkSeq;
				
				var ajaxUrl = '${pageContext.request.contextPath}/bookmark/myAdd';
				var ajaxData = {
					"bookmark_seq": bookmarkSeq, 
				};
				
				$.ajax({
					type : "POST",
					url : ajaxUrl,
					data : ajaxData,
					dataType : "json",
					success : function(data){
						var result = data.result;
						if(result == 'success'){
							var bookmark = data.bookmark;	
							var html = '';
							html += '<td align="center">'+bookmark.bookmark_type+'</td>';
							html += '<td align="center">'+bookmark.bookmark_category+'</td>';
							html += '<td align="left">'+bookmark.bookmark_description+'<br />';
							html += '	<a href="'+bookmark.bookmark_url+'" class="font-12 color-royal-blue" target="_blink">'+bookmark.bookmark_url+'</a>';
							html += '	<a href="#" class="font-8 color-red" onclick="f_report(\''+bookmark.bookmark_seq+'\'); return false;">신고</a>';
							html += '</td>';
							html += '<td align="center">';
						html += '		<i class="fa fa-star color-orange corsor" aria-hidden="true" onclick="f_myDel(\''+bookmark.bookmark_seq+'\'); return false;"></i>';
							html += '</td>';
							
							if(html != ''){
								$(bookmarkListGroupId).html(html);
							}
						}
					}
				});
			}
			
			function f_myDel( bookmarkSeq ){
				var memberId = '${sessionScope.SESSION_MEMBER_ID}';
				var bookmarkListGroupId = '#bookmark_list_group_'+bookmarkSeq;

				var ajaxUrl = '${pageContext.request.contextPath}/bookmark/myDel';
				var ajaxData = {
					"bookmark_seq": bookmarkSeq, 
				};
				
				$.ajax({
					type : "POST",
					url : ajaxUrl,
					data : ajaxData,
					dataType : "json",
					success : function(data){
						var result = data.result;
						if(result == 'success'){
							var bookmark = data.bookmark;							
							var html = '';
							html += '<td align="center">'+bookmark.bookmark_type+'</td>';
							html += '<td align="center">'+bookmark.bookmark_category+'</td>';
							html += '<td align="left">'+bookmark.bookmark_description+'<br />';
							html += '	<a href="'+bookmark.bookmark_url+'" class="font-12 color-royal-blue" target="_blink">'+bookmark.bookmark_url+'</a>';
							html += '	<a href="#" class="font-8 color-red" onclick="f_report(\''+bookmark.bookmark_seq+'\'); return false;">신고</a>';
							html += '</td>';
							html += '<td align="center">';
						html += '		<i class="fa fa-star-o color-orange corsor" aria-hidden="true" onclick="f_myAdd(\''+bookmark.bookmark_seq+'\'); return false;"></i>';
							html += '</td>';
							
							if(html != ''){
								$(bookmarkListGroupId).html(html);
							}
						}
					}
				});
			}
			
			var defaultPage = 2;
			function f_more(){
				var memberId = '${SESSION_MEMBER_ID}';
				
				var ajaxUrl = '${pageContext.request.contextPath}/bookmark/more';
				var ajaxData = {
					"page": defaultPage, 
				};
				
				$.ajax({
					type : "POST",
					url : ajaxUrl,
					data : ajaxData,
					dataType : "json",
					success : function(data){
						var result = data.result;
						if(result == 'success'){
							var html = '';
							var list = data.list;
							for(var i=0; i<list.length; i++){
								html += '<tr id="bookmark_list_group_'+list[i].bookmark_seq+'">';
								html += '	<td align="center">'+list[i].bookmark_type+'</td>';
								html += '	<td align="center">'+list[i].bookmark_category+'</td>';
								html += '	<td align="left">'+list[i].bookmark_category+'';
								html += '		'+list[i].bookmark_description+'<br />';
								html += '		<a href="'+list[i].bookmark_url+'" class="font-12 color-royal-blue" target="_blink">'+list[i].bookmark_url+'</a>';
								html += '		<a href="#" class="font-8 color-red" onclick="f_report(\''+list[i].bookmark_seq+'\'); return false;">신고</a>';
								html += '	</td>';
								html += '	<td align="center">';
								if('' != memberId){
									if(memberId == list[i].reg_id){
										html += '<i class="fa fa-meetup" aria-hidden="true"></i>';	
									}else if(0 == list[i].my_seq){
									html += '	<i class="fa fa-star-o color-orange corsor" aria-hidden="true" onclick="f_myAdd(\''+list[i].bookmark_seq+'\'); return false;"></i>';
									}else{
										html += '<i class="fa fa-star color-orange corsor" aria-hidden="true" onclick="f_myDel(\''+list[i].bookmark_seq+'\'); return false;"></i>';
									}
								}
								html += '	</td>';
								html += '</tr>';
							}
							
							if(html != ''){
								$('.bookmark-list table').append(html);
							}
							
							if(list.length < 10){
								$('.bookmark-more').remove();
							}
							
							
							defaultPage++;
						}else{
							$('.bookmark-more').remove();
						}
					}
				});	
				
			}
		</script>
		
		<div id="section">
			<div class="bookmark-search">
				<form:form id="bookmarkSearchForm" action="${pageContext.request.contextPath}/bookmark/list" method="get" commandName="bookmarkVO" style="margin: 0;" >
					<fieldset>
						<legend>북마크 검색</legend>
						<p>
							<form:select path="search_condition" class="bookmark-search-condition">
								<form:option value="">검색조건 선택</form:option>
								<form:option value="bookmark_description">설명</form:option>
								<form:option value="bookmark_url">주소</form:option>
								<form:option value="bookmark_category">분류</form:option>
								<form:option value="bookmark_type">유형</form:option>
							</form:select>
							
							<form:input path="search_word" placeholder="검색내용" class="bookmark-search-word" />
							<button onclick="f_bookmarkSearch(); return false;" class="pure-button bookmark-search-btn">검색</button>
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
							 				<a href="#" class="font-8 color-red"onclick="f_ban('${bookmarkList.bookmark_seq}'); return false;">신고</a>
							 			</td>
							 			<td align="center">
											<c:if test="${! empty sessionScope.SESSION_MEMBER_ID}">
												<c:choose>	
								 					<c:when test="${sessionScope.SESSION_MEMBER_ID == bookmarkList.reg_id}">
														<i class="fa fa-meetup" aria-hidden="true"></i>
								 					</c:when>
								 					<c:when test="${0 eq bookmarkList.my_seq}">
							 							<i class="fa fa-star-o color-orange corsor" aria-hidden="true" onclick="f_myAdd('${bookmarkList.bookmark_seq}'); return false;"></i>
								 					</c:when>
								 					<c:otherwise>
							 							<i class="fa fa-star color-orange corsor" aria-hidden="true" onclick="f_myDel('${bookmarkList.bookmark_seq}'); return false;"></i>
								 					</c:otherwise>
								 				</c:choose>
								 			</c:if>
										</td>
							 		</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
						 			<td colspan="5" align="center">데이터가 존재하지 않습니다.</td>
						 		</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
			
			<c:if test="${fn:length(bookmarkList) >= 10}"> 
				<div class="bookmark-more">
					<i class="fa fa-caret-down fa-2x color-black corsor" aria-hidden="true" onclick="f_more(); return false;"></i>
				</div>
			</c:if>
		</div><!-- #section -->
		
	</tiles:putAttribute>
</tiles:insertDefinition>