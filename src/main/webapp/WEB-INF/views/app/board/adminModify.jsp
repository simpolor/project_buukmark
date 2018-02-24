<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<tiles:insertDefinition name="admin-layout">
    <tiles:putAttribute name="admin-body">
		
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/board.css">
		
		<script src="${pageContext.request.contextPath}/js/tinymce/tinymce.min.js"></script>
  		<script>
  			tinymce.init({ 
 				selector: '#board_content',
 				menubar: false,
  				width: 888,
  				height: 450
  			});
  		</script>
  		
		<script type="text/javascript">
			$(document).ready(function() { 
				var alertMessage = '${alertMessage}';
				
				if(!isNull(alertMessage)){
					alert(alertMessage);
				}
			});
			
			function f_modify(){
				if(isNull($("#board_title").val())){
					alert('제목를 입력해주세요.');	
					return false;
				}
				
				tinyMCE.triggerSave(); // tinymce editor
				if(isNull($("#board_content").val())){
					alert('내용을 입력해주세요.');	
					return false;
				}
				$("#boardModifyForm").submit();
			}
			
		</script>
	
		<div id="section">
			<div class="board-nav">
				<div class="board-nav-title">
					<span>(관리자) 자유게시판 수정</span>
				</div>
			</div>
			
			<div class="board-write">
				<form:form id="boardModifyForm" action="${pageContext.request.contextPath}/admin/board/modify" method="post" commandName="boardVO">
					<form:hidden path="board_seq" />
					<table>
						<caption>자유게시판 수정</caption>
						<colgroup>
							<col width="100%" />
						</colgroup>
						<thead>
							<tr>
								<th><form:input path="board_title" class="board-write-title" placeholder="제목" /></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><form:textarea path="board_content" class="board-write-content" placeholder="내용" /></td>
							</tr>
						</tbody>
					</table>		
				</form:form>			
			</div>
			
			<div class="board-btn">
				<div class="board-btn-list">
					<div class="board-btn-group">
						<c:if test="${! empty sessionScope.SESSION_MEMBER_ID && boardVO.reg_id eq sessionScope.SESSION_MEMBER_ID}">
							<a href="#" onclick="f_modify(); return false;">완료</a>
						</c:if>
						<a href="#" onclick="history.back(); return false;">취소</a>
					</div>
				</div>
			</div>
			
		</div><!-- #section -->
		
	</tiles:putAttribute>
</tiles:insertDefinition>