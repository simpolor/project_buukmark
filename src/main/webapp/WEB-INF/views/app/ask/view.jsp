<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<tiles:insertDefinition name="user-layout">
    <tiles:putAttribute name="user-body">
		
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ask.css">
		
		<script type="text/javascript">
			$(document).ready(function() { 
				var alertMessage = '${alertMessage}';
				
				if(!isNull(alertMessage)){
					alert(alertMessage);
				}
			});
			
			function f_modify(){
				$("#askViewForm").attr("action", "${pageContext.request.contextPath}/ask/modify");
				$("#askViewForm").attr("method", "get");
				$("#askViewForm").submit();
			}
			
			function f_delete(){
				$("#askViewForm").attr("action", "${pageContext.request.contextPath}/ask/delete");
				$("#askViewForm").attr("method", "post");
				$("#askViewForm").submit();
			}
			
		</script>
		
		<div id="section">
			<div class="ask-nav">
				<div class="ask-nav-title">
					<span>문의사항 상세보기</span>
				</div>
			</div>
			
			<div class="ask-view">
				<form id="askViewForm">
					<input type="hidden" id="ask_seq" name="ask_seq" value="${askVO.ask_seq}" />
					<table>
						<caption>문의사항 상세보기</caption>
						<colgroup>
							<col width="100%" />
						</colgroup>
						<thead>
							<tr>
								<th class="ask-view-title">
									<c:if test="${askVO.secret_yn eq 'Y'}">
										<i class="fa fa-lock" aria-hidden="true"></i>
									</c:if>
									${askVO.ask_title}
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="ask-view-content">${askVO.ask_content}</td>
							</tr>
						</tbody>
					</table>	
				</form>				
			</div>
			
			<div class="ask-btn">
				<div class="ask-btn-list">
					<div class="ask-btn-group">
						<c:if test="${! empty sessionScope.SESSION_MEMBER_ID && askVO.reg_id eq sessionScope.SESSION_MEMBER_ID}">
							<a href="#" onclick="f_modify(); return false;">수정</a>
							<a href="#" onclick="f_delete(); return false;">삭제</a>
						</c:if>
						<a href="${pageContext.request.contextPath}/ask/list">목록</a>
					</div>
				</div>
			</div>
			
		</div><!-- #section -->
		
	</tiles:putAttribute>
</tiles:insertDefinition>